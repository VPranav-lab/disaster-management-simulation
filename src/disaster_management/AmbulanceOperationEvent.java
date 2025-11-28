package disaster_management;

public class AmbulanceOperationEvent extends Event {
	private Resource unit;
	private TimeManager tm;
	private SimulationManager sm;

	public AmbulanceOperationEvent(int hour,Resource unit, SimulationManager sm,
			TimeManager tm) {
		super(hour, 6);
		this.unit = unit;
		this.tm = tm;
		this.sm = sm;
	}

	@Override
	public void process() {
		unit.action(0,this, null, sm, tm);
		unit.releaseFromZone();
		if (unit instanceof VolunteerTeam) {
			sm.removeVolunteerTeam((VolunteerTeam) unit);
			unit = null;
		}
	}
	
	@Override
    public void handleBy(VolunteerTeam team, SimulationManager sm, TimeManager tm) {
        team.handleAmbulanceOperation(sm, tm);
    }
}
