package disaster_management;


public class RescueOperationEndEvent extends Event{
    private Resource unit;  // can be null if volunteer team handled it
    SimulationManager sm;
    private TimeManager tm;

    public RescueOperationEndEvent(int hour,Resource unit,SimulationManager sm, TimeManager tm) {
        super(hour, 4);
        this.unit = unit;
        this.sm = sm;
        this.tm =tm;
    }

    @Override
    public void process() {
    	unit.action(0,this,null, sm, tm);
		unit.releaseFromZone();
		if (unit instanceof VolunteerTeam) {
			sm.removeVolunteerTeam((VolunteerTeam) unit);
			unit = null;
		}
    }
    
    
    @Override
    public void handleBy(VolunteerTeam team, SimulationManager sm, TimeManager tm) {
        team.handleRescueOperation( sm, tm);
    }
}
