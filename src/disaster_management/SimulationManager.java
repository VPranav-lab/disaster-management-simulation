package disaster_management;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class SimulationManager  {
	private final EntityRegistry<Hospital> hospitalRegistry = new EntityRegistry<>();
    private final EntityRegistry<Zone> zoneRegistry = new EntityRegistry<>();
    
    private final ResourceRegistry<RescueUnit> rescueRegistry = new ResourceRegistry<>();
    private final ResourceRegistry<AmbulanceUnit> ambulanceRegistry = new ResourceRegistry<>();
    private final ResourceRegistry<FoodSupplyTeam> foodRegistry = new ResourceRegistry<>();
    private final ResourceRegistry<PoliceUnit> policeRegistry = new ResourceRegistry<>();
    private final ResourceRegistry<VolunteerTeam> volunteerRegistry = new ResourceRegistry<>();
    private List<Disaster> disasters = new ArrayList<>();

   
    // ==============================
    // Add / Remove methods
    // ==============================
    public void addDisaster(Disaster d) {if (d!=null) disasters.add(d);}
    public void addHospital(Hospital h) { if (h != null) hospitalRegistry.add(h); }
    public void addZone(Zone z) { if (z != null) zoneRegistry.add(z); }

    public void addRescueUnit(RescueUnit r) { if (r != null) rescueRegistry.add(r); }
    public void addAmbulanceUnit(AmbulanceUnit a) { if (a != null) ambulanceRegistry.add(a); }
    public void addFoodUnit(FoodSupplyTeam f) { if (f != null) foodRegistry.add(f); }
    public void addPoliceUnit(PoliceUnit p) { if (p != null) policeRegistry.add(p); }
    public void addVolunteerTeam(VolunteerTeam v) { if (v != null) volunteerRegistry.add(v); }

    public void removeVolunteerTeam(VolunteerTeam v) { if (v != null) volunteerRegistry.remove(v); }
    public void removeHospital(Hospital v) { if (v != null) hospitalRegistry.remove(v); }

    // ==============================
    // Getters
    // ==============================
    public List<Disaster> getDisasters() { return disasters ;}
    public List<Hospital> getHospitals() { return hospitalRegistry.getAll(); }
    public List<Zone> getZones() { return zoneRegistry.getAll(); }

    public List<RescueUnit> getRescueUnits() { return rescueRegistry.getAll(); }
    public List<AmbulanceUnit> getAmbulanceUnits() { return ambulanceRegistry.getAll(); }
    public List<FoodSupplyTeam> getFoodUnits() { return foodRegistry.getAll(); }
    public List<PoliceUnit> getPoliceUnits() { return policeRegistry.getAll(); }
    public List<VolunteerTeam> getVolunteerUnits() { return volunteerRegistry.getAll(); }


}
