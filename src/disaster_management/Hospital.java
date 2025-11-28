package disaster_management;
import java.util.Random;
public class Hospital extends Entity implements EndOfTheDay {
	private Random rand = new Random(); 
	private int capacity;
	private int totalPatientsAdmitted;
    private int patientsRemaining;
    private int patientsDischarged;
    private int totalBirths;
    private int totalDeaths;
    private int dailyBirths;
    private int dailyDeaths;
    private int dailyAdmits;
    private int dailyDischarges;
    private Zone zone;


    public Hospital(String name, int capacity, Zone zone,SimulationManager sm) {
        super(name);
        this.capacity = capacity;
        this.zone = zone;
        this.patientsRemaining = 0;
        this.patientsDischarged = 0;
        this.dailyBirths = 0;
        this.dailyDeaths = 0;
        sm.addHospital(this);
        if (zone != null) {
            zone.setHospital(this);
        }
    }
    
    public Hospital(Zone zone,SimulationManager sm) {
        super("Hospital");
        this.capacity = 100;
        this.zone = zone;
        this.patientsRemaining = 0;
        this.patientsDischarged = 0;
        this.dailyBirths = 0;
        this.dailyDeaths = 0;
        sm.addHospital(this);
        if (zone != null) {
            zone.setHospital(this);
        }
    }
    

    public int getCapacity() {
        return capacity;
    }
    public int getTotalPatientsAdmitted() {
		return totalPatientsAdmitted;
	}
    public Zone getZone() {
		return zone;
	}

	public int getPatientsRemaining() {
        return patientsRemaining;
    }
    public int getPatientsDischarged() {
        return patientsDischarged;
    }
    public int getTotalBirths() {
		return totalBirths;
	}
    public int getTotalDeaths() {
		return totalDeaths;
	}
    public int getDailyBirths() {
        return dailyBirths;
    }
    public int getDailyDeaths() {
        return dailyDeaths;
    }
    public int getDailyAdmits() {
		return dailyAdmits;
	}
    public int getDailyDischarges() {
		return dailyDischarges;
	}
    public int getAvailablePlaces() {
    	return (capacity-patientsRemaining);
    }
    
    public void setDailyDischarges(int dailyDischarges) {
		this.dailyDischarges = dailyDischarges;
	}

	public void setDailyDeaths(int dailyDeaths) {
		this.dailyDeaths = dailyDeaths;
	}

	public void setDailyBirths(int dailyBirths) {
		this.dailyBirths = dailyBirths;
	}
	

	public void setDailyAdmits(int dailyAdmits) {
		this.dailyAdmits = dailyAdmits;
	}

    @Override
	public void EndOfTheDayEvent() {
    	System.out.println("Hospital: " + getName()); 
        System.out.println(" | Capacity: " + capacity); 
        System.out.println(" | Available Capacity: " + getAvailablePlaces()); 
        System.out.println(" | Today Admitted: " + dailyAdmits);
        System.out.println(" | Patients Remaining: " + patientsRemaining);
        System.out.println(" | Today Discharges: " + dailyDischarges);
        System.out.println(" | Today Births: " + dailyBirths);
        System.out.println(" | Today Deaths: " + dailyDeaths);
        System.out.println("---------------------------");
        
        dailyBirths=0;
        dailyDeaths =0;
        dailyDischarges = 0;
        dailyAdmits = 0;
		
	}
    
    
    public void admitPatients(int number) throws SimulationException {
    	if(getAvailablePlaces()<=0) {
        	throw new SimulationException( "Hospital: " + getName()+" at Zone: "+zone.getName()+ " capacity exceeded!");
        }
        patientsRemaining += number;
        totalPatientsAdmitted += number;
        dailyAdmits += number;
    }


	public int dischargePatients() {
    	int discharges = 0;
    	//int maxDischarge = 0;
    	if (patientsRemaining > 0) {
    		discharges = (int) (patientsRemaining * 0.2);
            //discharges = rand.nextInt(maxDischarge+1)+1;
    	}
		
        if(discharges!=0) {
        patientsDischarged += discharges;
        dailyDischarges +=discharges;
        patientsRemaining -= discharges;
        }
        return discharges;
    }

    public int simulateBirths() {
    	int births = rand.nextInt(10) + 1;  // 1 to 10
    	dailyBirths += births;
    	totalBirths += births;
    	return births;
    }

    public int simulateDeaths() {
    	//int maxDeaths = 0;
    	int deaths = 0;
    	if (patientsRemaining > 0) {
    		deaths = (int) (patientsRemaining * 0.3);
            //deaths = rand.nextInt(maxDeaths+1)+1;
    	}
		
    	if(deaths != 0) {
        dailyDeaths += deaths;
        totalDeaths += deaths;
        patientsRemaining -= deaths;
		
    	}
        return deaths;
    }

    @Override
    public void displayStatus() {
        System.out.println("Hospital: " + getName()); 
        System.out.println(" | Total Capacity: " + capacity); 
        System.out.println(" | Available Capacity: " + getAvailablePlaces()); 
        System.out.println(" | Total Admitted: " + totalPatientsAdmitted);
        System.out.println(" | Patients Remaining: " + patientsRemaining);
        System.out.println(" | Toatl Patients Discharged: " + patientsDischarged);
        System.out.println(" | Total Births: " + totalBirths);
        System.out.println(" | Total Deaths: " + totalDeaths);
    }

	
}
