package disaster_management;

public class AmbulanceUnit extends Resource {
	
	public AmbulanceUnit(String id,SimulationManager sm) {
        super(id, "Ambulance");
        sm.addAmbulanceUnit(this);
    }
	
	public AmbulanceUnit(String id) {
        super(id, "Ambulance");
    }

	@Override
	public void action(int hour,Event event, EventCalendar calendar, SimulationManager sm, TimeManager tm) {
		int injured = getCurrentZone().getCurrentDisaster().getInjuredToAdmit();

        System.out.println(tm.getFormattedTime() + 
            " 🚑 Ambulance started hospital transfer for " + injured + 
            " injured people from " + getCurrentZone().getName() + ".");

        try {
            admitPatients(injured,sm,tm);
        } catch (SimulationException e) {
            System.out.println(tm.getFormattedTime() + " ⚠️ " + e.getMessage());
        }
        
    }
		
	
	private void admitPatients(int injured, SimulationManager sm,TimeManager tm) throws SimulationException {
        Hospital localHospital = getCurrentZone().getHospital();
        int remaining = injured;

        if (localHospital != null && localHospital.getAvailablePlaces()>0) {
            int available = localHospital.getAvailablePlaces();
            int toAdmit = Math.min(available, remaining);
            localHospital.admitPatients(toAdmit);
            remaining -= toAdmit;
            if (remaining <= 0) {
            	setAvailable(true);
            }
            System.out.println(tm.getFormattedTime()+" → " + toAdmit + " people admitted to " + localHospital.getName());
        }

        for (Hospital h : sm.getHospitals()) {
            if (remaining <= 0) break;
            if (h == localHospital) continue;

            if (h.getAvailablePlaces()>0) {
                int available = h.getAvailablePlaces();
                int toAdmit = Math.min(available, remaining);
                h.admitPatients(toAdmit);
                remaining -= toAdmit;
                System.out.println(tm.getFormattedTime()+" → " + toAdmit + " admitted to " + h.getName());
            }
        }
        
        if (remaining > 0) {
            throw new SimulationException(
                "All hospitals are full! " + remaining + " injured people from " +
                getCurrentZone().getName() + " could not be admitted."
            );
        }

        System.out.println(tm.getFormattedTime()+" ✅ All severely injured patients from " + getCurrentZone().getName() + " have been admitted to hospital.");
    }	
	
}
