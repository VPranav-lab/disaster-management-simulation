package disaster_management;

import java.util.Random;

public class VolunteerTeam extends Resource {
	public VolunteerTeam(String id) {
        super(id, "Volunteer");
    }

	@Override
	public void action(int hour,Event e,EventCalendar calendar,SimulationManager sm, TimeManager tm) {
		try {
			e.handleBy(this, sm, tm);
		} catch (NotAllowedException e1) {
			System.out.println(e1.getMessage());
		}
		
	}
	
	public void handleRescueOperation(SimulationManager sm, TimeManager tm) {
		Random random = new Random();
		Disaster disaster = getCurrentZone().getCurrentDisaster();
		Zone zone =  getCurrentZone();
		int totalTrapped = disaster.getTrapped();

        
        int max = (int) (totalTrapped*0.2);
        int dead = random.nextInt(max+1);
        
        int maxinjured = (int) (totalTrapped*0.6);
        int injured = random.nextInt(maxinjured+1);
        
        
        int minorinjuries = totalTrapped - dead - injured;
        int rescued = injured+minorinjuries;
        
	
        Event.adddailyDeaths(dead);
        Event.addtotaldeaths(dead);
        try {
			Event.minustotalPopulation(dead);
		} catch (SimulationException ex) {
			ex.getMessage();
		}
        
        Event.adddailyInjured(injured);
        Event.addtotalInjured(injured);
        
        Event.adddailyrescued(rescued);
        Event.addtotalrescued(rescued);
        
        
        disaster.setDeaths(disaster.getDeaths()+dead);
        disaster.setInjured(disaster.getInjured()+injured);
        disaster.setRescued(rescued);
        
        int toAdmit = (int) (disaster.getInjured()*0.8);
        //int toAdmit = random.nextInt(maxToAdmit+1)+1;
        disaster.setInjuredToAdmit(toAdmit);
        
        
        System.out.println(tm.getFormattedTime() + " Volunteer team " + getId() + " in " + zone.getName() +
                " finished rescue. Rescued: " + rescued + ", Injured: " + injured + ", Dead: " + dead + ".");
    }

    public void handleAmbulanceOperation(SimulationManager sm, TimeManager tm) {
    	Disaster disaster = getCurrentZone().getCurrentDisaster();
    	int injured = disaster.getInjuredToAdmit();

        System.out.println(tm.getFormattedTime() + 
            " volunteer team started hospital transfer for " + injured + 
            " injured people from " + getCurrentZone().getName() + ".");

        try {
            admitPatients(injured,getCurrentZone(),sm,tm);
        } catch (SimulationException ex) {
            System.out.println(tm.getFormattedTime() + " ⚠️ " + ex.getMessage());
        }
    }

    public void handleFoodSupply(SimulationManager sm, TimeManager tm) {
    	Random random = new Random();
		if(this.getCurrentZone()!=null) {
            System.out.println(tm.getFormattedTime()+"  Food supply Team- "+getId()+" arrived and supplying food in "+getCurrentZone().getName());
			}else {
				System.out.println(tm.getFormattedTime()+"  Food supply Team- "+getId()+" arrived and supplying food in the affected zones ");

			}
		int foodPercent = random.nextInt(4,15)+1;
        Event.addTotalFoodPercentage(foodPercent);
        System.out.println("  --> Food percentage of the whole city is increased by - "+foodPercent+"% ");
}	
	
	private void admitPatients(int injured,Zone zone, SimulationManager sm,TimeManager tm) throws SimulationException {
        Hospital localHospital = zone.getHospital();
        int remaining = injured;

        // Try local hospital first
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

        // Try other hospitals
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
        
        // If still remaining, throw exception
        if (remaining > 0) {
            throw new SimulationException(
                "All hospitals are full! " + remaining + " injured people from " +
                zone.getName() + " could not be admitted."
            );
        }
        System.out.println(tm.getFormattedTime()+" ✅ All severely injured patients from " + zone.getName() + " have been admitted to hospital.");
    }	
	}

