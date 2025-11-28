package disaster_management;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
public abstract class Disaster implements SeverityCalculator{
	private String name;
    private int severity;       // 1–5
    private Zone zone;
    private static final int[] severityModifiers = {-5, -3, 0, 3, 5};
    // Results of the disaster
    private int injured;
    private int deaths;
    private int trapped;
    private int rescued;
    private int injuredToAdmit;
    
    public abstract int[] getBaseRanges();

    public String getName() {return name;}
    public int getSeverity() {return severity;}
    public Zone getZone() {return zone;}
    public int getInjured() {return injured;}
    public int getDeaths() {return deaths;}
    public int getTrapped() {return trapped;}
    public int getRescued() {return rescued;}
	public int getInjuredToAdmit() {return injuredToAdmit;}
	
	public void setInjuredToAdmit(int injuredToAdmit) {this.injuredToAdmit = injuredToAdmit;}
	public void setRescued(int rescued) {this.rescued = rescued;}
	public void setInjured(int injured) {this.injured = injured;}
	public void setDeaths(int deaths) {this.deaths = deaths;}
	public void setTrapped(int trapped) {this.trapped = trapped;}
	public void setSeverity(int severity) {this.severity = severity;}
	public void setZone(Zone zone) {this.zone = zone;}

	
	
	public Disaster(String name) {
        this.name = name;
    }
	
	public Disaster(String name,int severity, Zone zone) {
        this.name = name;
		this.severity = severity; 
		this.zone = zone;
		 
    }
   
	
	
    private void generateCasualties() {
    	Random random = new Random();
        int[] ranges = getBaseRanges(); // [injuredMin, injuredMax, deathMin, deathMax, trappedMin, trappedMax]
        // Adjust upper bounds based on severity
        int injuredMax = adjustBySeverity(ranges[1]);
        int deathsMax = adjustBySeverity(ranges[3]);
        int trappedMax = adjustBySeverity(ranges[5]);
        
        injured = random.nextInt(injuredMax - ranges[0] + 1) + ranges[0]; 
        deaths = random.nextInt(deathsMax - ranges[2] + 1) + ranges[2]; 
        trapped = random.nextInt(trappedMax - ranges[4] + 1) + ranges[4];
        
		Event.adddailyDeaths(deaths);
		Event.addtotaldeaths(deaths);
		
		Event.adddailyInjured(injured);
		Event.addtotalInjured(injured);
		
		try { 
			  Event.minustotalPopulation(deaths); 
		  } catch(SimulationException e) { 
			  System.out.println(e.getMessage());
			  Event.setTotalPopulation(800); }
		
		if (trapped<=0) {
			int maxToAdmit = (int) (injured*0.8);
	        //int toAdmit = random.nextInt(maxToAdmit+1)+1;
			injuredToAdmit = maxToAdmit;
		}
		  
		 
    }
    
    public void initializeCasualties(int severity, Zone zone) {
    	this.severity=severity;
    	this.zone=zone;
        generateCasualties();
    }

     // index 0 -> severity 1
    
    private int adjustBySeverity(int originalMax) {
    	int modifier = 0;
    	int s = severity;      
    	if (s > 0) {
    		modifier = severityModifiers[s - 1];
    	}
        int adjusted = originalMax + modifier;
        return adjusted;
    }

    

    @Override
    public String toString() {
        return String.format("  " +name+" Disaster happend in "+ zone.getName()+
            " with Severity:"+severity+" → Injured: %d, Dead: %d, Trapped: %d",
            injured, deaths, trapped
        );
    }
    
    public String afterDisaster() {
    	return String.format(" Injured: %d, Deaths: %d, Rescues: %d",  injured, deaths, rescued);
    }
    
}
