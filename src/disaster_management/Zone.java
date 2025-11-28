package disaster_management;

public class Zone extends Entity{
	private Hospital hospital;
	private boolean disasterOngoing;
	private Disaster currentDisaster;

	public Zone(String name,SimulationManager sm) {
        super(name);
        this.disasterOngoing = false;
        this.currentDisaster = null;
        City.getInstance().addZone(this);
        sm.addZone(this);
    }
	
	public Zone(String name) {
        super(name);
        this.disasterOngoing = false;
        this.currentDisaster = null;
    }

        
	
    public Disaster getCurrentDisaster() {return currentDisaster;}
    public boolean isDisasterOngoing() {return disasterOngoing;}
    public Hospital getHospital() {return hospital;}
    
	public void setCurrentDisaster(Disaster currentDisaster) {
		this.currentDisaster = currentDisaster;
	}
	public void setDisasterOngoing(boolean disasterOngoing) {
		this.disasterOngoing = disasterOngoing;
	}
	public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    @Override
    public void displayStatus() {
        System.out.println("Zone: " + getName() + " have Hospital: " + hospital.getName());
        if (disasterOngoing) {
            System.out.println("⚠ Disaster Ongoing: " + currentDisaster);
        } else {
            System.out.println("No Ongoing disaster currently.");
        }
        hospital.displayStatus();
        System.out.println("---------------------------");
    }
}
