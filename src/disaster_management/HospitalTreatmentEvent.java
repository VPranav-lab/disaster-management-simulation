package disaster_management;

import java.util.List;

public class HospitalTreatmentEvent extends Event {
	 private List<Hospital> hospitals;
	 private TimeManager tm;
	public HospitalTreatmentEvent(int hour,SimulationManager sm, TimeManager tm) {
		super(hour, 4);
		this.hospitals = sm.getHospitals();
		this.tm =tm;
		
	}

	@Override
	public void process() {
		for (Hospital h : hospitals) {
			int births = h.simulateBirths();
			Event.adddailyBirths(births); 
			Event.addtotalBirths(births);
			Event.addtotalPopulation(births);
			if (births !=0) {
				System.out.println(tm.getFormattedTime()+" "+births+" babies born in the "+h.getName());
			}
			
			int deaths= h.simulateDeaths();
			Event.adddailyDeaths(deaths);
			Event.addtotaldeaths(deaths);
			try {
				Event.minustotalPopulation(deaths);
			} catch (SimulationException e) {
				System.out.println(e.getMessage());
				System.out.println(" Resetting the total population.");
				Event.setTotalPopulation(800);
			}
			if (deaths !=0) {
				System.out.println(tm.getFormattedTime()+" "+deaths+" people dead in the "+h.getName());
			}
			
			int discharge = h.dischargePatients();
			
			if (discharge !=0) {
				System.out.println(tm.getFormattedTime()+" "+discharge+" people discharged from the "+h.getName());
			}
			
		}
		
	}
	
	
}
