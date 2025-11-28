package disaster_management;

public class FoodSupplyEvent extends Event{
	private TimeManager tm;
	private SimulationManager sm;
	private Resource unit = null;

	public FoodSupplyEvent(int hour,Resource unit,SimulationManager sm,TimeManager tm) {
		super(hour, 8);
		this.tm = tm;
		this.sm=sm;
		this.unit = unit;
	}
	
	
	@Override
	public void process() {
		unit.action(0, this, null, sm, tm);
		unit.releaseFromZone();
		if (unit instanceof VolunteerTeam) {
			sm.removeVolunteerTeam((VolunteerTeam) unit);
			unit = null;
		}
	}
	
	@Override
    public void handleBy(VolunteerTeam team, SimulationManager sm, TimeManager tm) {
        team.handleFoodSupply(sm, tm);
    }

}
