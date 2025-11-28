package disaster_management;

import java.util.List;

public class RescueTeamArrivalEvent extends Event {
	private Zone zone;
	private EventCalendar calendar;
	private TimeManager tm;
	private SimulationManager sm;
	private List<RescueUnit> rescueUnits;
	private List<VolunteerTeam> volunteerUnits;
	private RescueUnit unit = null;
	private VolunteerTeam vUnit = null;

	public RescueTeamArrivalEvent(int hour, Zone zone,SimulationManager sm, EventCalendar calendar,
			TimeManager tm) {
		super(hour, 3);
		this.zone = zone;
		this.tm = tm;
		this.calendar = calendar;
		this.sm = sm;
		rescueUnits = sm.getRescueUnits();
		volunteerUnits = sm.getVolunteerUnits();
	}

	@Override
	public void process() {
		for (RescueUnit R : rescueUnits) {
			if (R.isAvailable() == true) {
				unit = R;
				R.assignToZone(zone);
				System.out.println(tm.getFormattedTime() + "  Rescue Team- " + unit.getId() + " arrived to "
						+ zone.getName() + " and rescuing " + zone.getCurrentDisaster().getTrapped() + " people in " + zone.getName());
				calendar.scheduleEvent(new RescueOperationEndEvent(getHour() + 1, unit, sm, tm));
				break;
			}
		}
		if (unit == null) {
			int v = volunteerUnits.size() + 1;
			vUnit = new VolunteerTeam("Volunteer " + v);
			sm.addVolunteerTeam(vUnit);
			vUnit.assignToZone(zone);
			System.out.println(tm.getFormattedTime() + "  Volunteer Team- " + vUnit.getId() + " arrived to "
					+ zone.getName() + " and rescuing " + zone.getCurrentDisaster().getTrapped() + " people in " + zone.getName());
			calendar.scheduleEvent(new RescueOperationEndEvent(getHour() + 1,vUnit, sm, tm));

		}

	}

}
