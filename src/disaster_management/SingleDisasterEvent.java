package disaster_management;

import java.util.Random;

public class SingleDisasterEvent extends Event  {
	private Zone zone;
	private Disaster disaster;
	private EventCalendar calendar;
	private TimeManager tm;
	private SimulationManager sm;
	private String type;
	private FoodSupplyTeam funit = null;
	private VolunteerTeam fvUnit=null;
	public SingleDisasterEvent(int hour,Disaster d,Zone zone,SimulationManager sm,EventCalendar calendar,TimeManager tm) {
		super(hour, 2);
		this.calendar=calendar;
		this.sm=sm;
		this.tm=tm;
		this.zone=zone;
		//this.severity=severity;
		this.disaster=d;
	}

	@Override
	public void process(){
		int latency = 0;
		type = disaster.getName();
		Random rand = new Random();
		int Severity = disaster.determineSeverity(Event.getlatestWeather());
		 disaster.initializeCasualties(Severity,zone);
		 zone.setDisasterOngoing(true); 
		 zone.setCurrentDisaster(disaster);
	     System.out.println(tm.getFormattedTime()+disaster); 
	        
	     	if (type == "Flood" || type == "Earthquake") {
	     		int num = rand.nextInt(20)+1;
	     		
	     		try {
	     	        Event.minusTotalFoodPercentage(num);
	     	        System.out.println(tm.getFormattedTime()+" Total Food in the city reduced by " + 
	     	        num + "%. Current: " + Event.getTotalFoodPercentage() + "%");
	     	    } catch (FoodShortageException e) {
	     	    	System.out.println(e.getMessage());
	     	    	System.out.println("Emergency food aid dispatched");
	     	    	for(FoodSupplyTeam F : sm.getFoodUnits()) {
	    				if(F.isAvailable() == true) {
	    					funit = F;
	    					F.setAvailable(false);
	    					calendar.scheduleEvent(new FoodSupplyEvent(getHour()+1,funit,sm,tm));
	    					break;
	    				}
	    			}
	    			if (funit == null) {
	    	        	int v = sm.getVolunteerUnits().size()+1;
	    	        	fvUnit = new VolunteerTeam("Volunteer "+v);
	    	        	sm.addVolunteerTeam(fvUnit);
	    	        	fvUnit.setAvailable(false);
	    	            calendar.scheduleEvent(new FoodSupplyEvent( getHour()+1,fvUnit, sm,  tm));

	    	        }
	     	        
	     	    }
	     	}
	     	if (Event.getlatestWeather().equals("Rain")) {
				
				  Random random = new Random(); 
				  latency =random.nextInt(2);
				 
			}
	        // Schedule related events
	     	if (disaster.getTrapped()>0) {
	     		calendar.scheduleEvent(new RescueTeamArrivalEvent(getHour()+ 1+latency, zone,sm, calendar,tm));
	     	}
	        
	        calendar.scheduleEvent(new AmbulanceArrivalEvent(getHour()+ 2+latency, zone,sm, calendar,tm));
	        
	        calendar.scheduleEvent(new PoliceArrivalEvent(getHour()+ 2+latency, zone,sm, calendar,tm));
		
	}
	
}
