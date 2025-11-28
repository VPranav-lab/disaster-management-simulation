package disaster_management;

public abstract class Resource {
	private String id;
	private String name;
    private boolean available;
    private Zone currentZone; 
    
    public Resource(String id, String name) {
        this.id = id;
        this.name = name;
        this.available = true;
        this.currentZone = null;
    }

    public String getId() {
        return id;
    }

    public String getName() {
		return name;
	}

	public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Zone getCurrentZone() {
        return currentZone;
    }

    public void assignToZone(Zone zoneName) {
        currentZone = zoneName;
        available = false;
    }

    public void releaseFromZone() {
        currentZone = null;
        available = true;
    }

   public abstract void action(int hour,Event e,EventCalendar calendar,SimulationManager sm,TimeManager tm);

}
