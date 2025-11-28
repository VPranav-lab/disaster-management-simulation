package disaster_management;

public class PoliceUnit extends Resource{
	private FoodSupplyTeam funit = null;
	private VolunteerTeam fvUnit=null;
	public PoliceUnit(String id,SimulationManager sm) {
        super(id, "Police");
        sm.addPoliceUnit(this);
    }
	
	public PoliceUnit(String id) {
        super(id, "Police");
        //sm.addPoliceUnit(this);
    }

	@Override
	public void action(int hour,Event e,EventCalendar calendar, SimulationManager sm, TimeManager tm) {
		Disaster D = getCurrentZone().getCurrentDisaster();
		if(Event.getTotalFoodPercentage()<=60) {
			int num = 0;
			System.out.println(tm.getFormattedTime()+"Food in the city is less than 60%. Calling the Food Supply Team");
			for(FoodSupplyTeam F : sm.getFoodUnits()) {
				if(F.isAvailable() == true) {
					funit = F;
					F.assignToZone(getCurrentZone());
					if (Event.getlatestWeather().equals("Rain")) {
						num=1;
					}
					calendar.scheduleEvent(new FoodSupplyEvent(hour+1+num,funit,sm,tm));
					break;
				}
			}
			if (funit == null) {
	        	int v = sm.getVolunteerUnits().size()+1;
	        	fvUnit = new VolunteerTeam("Volunteer "+v);
	        	sm.addVolunteerTeam(fvUnit);
	        	fvUnit.assignToZone(getCurrentZone());
	            calendar.scheduleEvent(new FoodSupplyEvent( hour+1,fvUnit, sm,  tm));

	        }
        }
		
		System.out.println(tm.getFormattedTime() + " Police unit - " + getId() + " in " + getCurrentZone().getName() +
                " finished all operations.");
		//this.setAvailable(true);
		System.out.println(tm.getFormattedTime() + " "+ D.getName()+" Disaster and rescue casualties Ended in " + getCurrentZone().getName() +
                " and this are the final disaster effects -> "+ D.afterDisaster());
		D=null;
		getCurrentZone().setDisasterOngoing(false); 
		getCurrentZone().setCurrentDisaster(null);
		
	}
}
