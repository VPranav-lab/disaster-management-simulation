package disaster_management;

import java.util.List;

public class PoliceArrivalEvent extends Event {
	private Zone zone;
	private EventCalendar calendar;
	private TimeManager tm;
	private SimulationManager sm;
	private List<PoliceUnit> policeUnits;
	
	private PoliceUnit unit = null;

	public PoliceArrivalEvent(int hour,Zone zone,SimulationManager sm,EventCalendar calendar, TimeManager tm) {
		super(hour, 3);
		this.zone = zone;
		this.tm = tm;
		this.calendar = calendar;
		this.sm =sm;
		policeUnits = sm.getPoliceUnits();
	}

	@Override
	public void process() {
		for(PoliceUnit R : policeUnits) {
			if(R.isAvailable() == true) {
				unit = R;
				R.assignToZone(zone);
				break;
			}
		}
		System.out.println(tm.getFormattedTime()+" Police arrived and contolling the situation in " + zone.getName());
		calendar.scheduleEvent(new PoliceOperationEndEvent(getHour()+2,unit,sm,calendar,tm));
		
	}
}
