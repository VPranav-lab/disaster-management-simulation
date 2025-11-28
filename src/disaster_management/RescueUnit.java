package disaster_management;

import java.util.Random;

public class RescueUnit extends Resource {
	
	public RescueUnit(String id, SimulationManager sm) {
        super(id, "Rescue");
        sm.addRescueUnit(this);
    }
	public RescueUnit(String id) {
        super(id, "Rescue");
        //sm.addRescueUnit(this);
    }
	
	//@Override
	public void action(int hour,Event event,EventCalendar calendar,SimulationManager sm, TimeManager tm) {
		Random random = new Random();
		Disaster disaster = getCurrentZone().getCurrentDisaster();
		int totalTrapped = disaster.getTrapped();

        // Randomly pick 0–20% for dead and 0-60% injured
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
		} catch (SimulationException e) {
			System.out.println(e.getMessage());
			System.out.println(" Resetting the total population.");
			Event.setTotalPopulation(800);
		}
        
        Event.adddailyInjured(injured);
        Event.addtotalInjured(injured);
        
        Event.adddailyrescued(rescued);
        Event.addtotalrescued(rescued);
        
        // Update disaster stats
        disaster.setDeaths(disaster.getDeaths()+dead);
        disaster.setInjured(disaster.getInjured()+injured);
        disaster.setRescued(rescued);
        
        int maxToAdmit = (int) (disaster.getInjured()*0.8);
        //int toAdmit = random.nextInt(maxToAdmit+1)+1;
        disaster.setInjuredToAdmit(maxToAdmit);
        
        System.out.println(tm.getFormattedTime() + " Rescue operation by " + getId() + " in " + getCurrentZone().getName() +
                " ended. Rescued: " + rescued + "( Injured: " + injured + "), Dead: " + dead + ".");
        
        
	}

}
