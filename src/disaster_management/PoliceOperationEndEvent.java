package disaster_management;

public class PoliceOperationEndEvent extends Event{
	private TimeManager tm;
	private PoliceUnit unit;
	private EventCalendar calendar;
	private SimulationManager sm;
	public PoliceOperationEndEvent(int hour,PoliceUnit unit,SimulationManager sm,EventCalendar calendar, TimeManager tm) {
		super(hour, 7);
		this.tm = tm;
		this.unit = unit;
		this.calendar=calendar;
		this.sm = sm;
	}

	@Override
	public void process() {
		unit.action(this.getHour(),null, calendar, sm, tm);
		unit.releaseFromZone();
	}
	
}
