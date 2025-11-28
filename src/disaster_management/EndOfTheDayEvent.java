package disaster_management;


public class EndOfTheDayEvent extends Event{
	private TimeManager tm;
	private SimulationManager sm;
	public EndOfTheDayEvent(int hour,SimulationManager sm,TimeManager tm) {
		super(hour, 8);
		this.tm=tm;
		this.sm = sm;
	}
	public TimeManager gettm() {
		return tm;
	}

	@Override
	public void process() {
		int day = tm.getCurrentDay();
        System.out.printf("\n======================================== DAILY SUMMARY FOR DAY %d ========================================\n", day);
        System.out.printf("%-10s | %10s | %10s | %10s | %10s | %16s | %20s\n",
        		"Disasters","Births", "Deaths", "Injuries", "Rescues", "Total Population", "Food in the city (%)");
        System.out.println("----------------------------------------------------------------------------------------------------------");
        System.out.printf("%-10d | %10d | %10d | %10d | %10d | %16d | %20d%n",
                Event.getDailyDisasters(), Event.getDailyBirths(), Event.getDailyDeaths(), Event.getDailyInjured(), 
                Event.getDailyrescued(), Event.getTotalPopulation(), Event.getTotalFoodPercentage());
        System.out.println("----------------------------------------------------------------------------------------------------------");
       EndOfTheDayEvent();
       for (Hospital h : sm.getHospitals()) {
   		h.EndOfTheDayEvent();
   	}
        
        
		
	}

}
