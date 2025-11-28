package disaster_management;

import java.util.ArrayList;
import java.util.List;

public class City extends Entity {
	
	private static City instance;   // Singleton instance

    private List<Zone> zones;  

    private City(String name) {
        super(name);
        this.zones = new ArrayList<>();
    }

    // Public static method to get the single instance
	  public static City create(String name) { 
		  if (instance == null) {
			  instance = new City(name); 
			  } 
		  return instance; }
	 
    // Get existing city instance (after it's created)
    public static City getInstance() {
        if (instance == null) {
            throw new IllegalStateException("City has not been created yet!");
        }
        return instance;
    }

    public void addZone(Zone zone) {
        if (zone != null) {
            zones.add(zone);
        }
    }
    
    public List<Zone> getZones() {
        return zones;
    }

    @Override
    public void displayStatus() {
        System.out.println("=====Summary of the City: " + getName()+"=====");
        System.out.println("Total Population: " + Event.getTotalPopulation() );
        System.out.println("Number of Zones: " + zones.size());
        System.out.println("Zone Details:");
        for (Zone zone : zones) {
            zone.displayStatus();
        }
        System.out.println("======================================================================================================");
    }
	
}
