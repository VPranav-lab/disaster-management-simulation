package disaster_management;

import java.util.List;

public class AmbulanceArrivalEvent extends Event {
	private Zone zone;
	private EventCalendar calendar;
	private TimeManager tm;
	private SimulationManager sm;
	private List<AmbulanceUnit> ambulanceUnits;
	private List<VolunteerTeam> volunteerUnits;
	private AmbulanceUnit unit = null;
	private VolunteerTeam vUnit=null;
	
	public AmbulanceArrivalEvent(int hour,Zone zone,SimulationManager sm,EventCalendar calendar, TimeManager tm) {
		super(hour, 5);
		this.zone = zone;
		this.tm = tm;
		this.calendar = calendar;
		this.sm =sm;
		ambulanceUnits = sm.getAmbulanceUnits();
		volunteerUnits = sm.getVolunteerUnits();
	}
	
	@Override
	public void process() {
		
		for(AmbulanceUnit R : ambulanceUnits) {
			if(R.isAvailable() == true) {
				unit = R;
				R.assignToZone(zone);
				System.out.println(tm.getFormattedTime()+"  Ambulance Team- "+unit.getId()+" arrived to the "+zone.getName());
	            calendar.scheduleEvent(new AmbulanceOperationEvent( getHour()+2,unit, sm,  tm));
				break;
			}
		}
		 
		if (unit == null) {
            // No Ambulance team available — volunteer team steps in
        	int v = volunteerUnits.size()+1;
        	vUnit = new VolunteerTeam("Volunteer "+v);
        	sm.addVolunteerTeam(vUnit);
        	vUnit.assignToZone(zone);
            System.out.println(tm.getFormattedTime()+"  Volunteer Team- "+vUnit.getId()+" arrived at "+zone.getName()+" to tranfer injured people to the hospital");
            calendar.scheduleEvent(new AmbulanceOperationEvent( getHour()+2, vUnit, sm,  tm));

        }
		
	}

}
