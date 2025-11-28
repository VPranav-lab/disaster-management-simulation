package disaster_management;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
	public static void main(String[] args) {
	
		SimulationManager sm = new SimulationManager();
		TimeManager tm = new TimeManager();
		WeatherStation ws = new WeatherStation();
		EventCalendar calendar = new EventCalendar();
		City Messina = City.create("Messina");
		
		  System.out.println("\n---- Subtyping and Multityping Demo in Disaster Management System ----"); 
		  Zone demozone = new Zone("demoZone"); 
		  Hospital demoHospital = new Hospital(demozone,sm); 
		  Resource demoRescue = new RescueUnit("Demo Rescue");
		  Resource demoAmbulance = new AmbulanceUnit("Demo Ambulance"); 
		  Resource demoPolice = new PoliceUnit("DemoPolice");
		  
		  // subtyping and multityping through abstract class Event and Interfaces EndOfTheDay and WeatherObserver
		  
		  EndOfTheDayEvent demoevent = new EndOfTheDayEvent(0,null,null); 
		  Event e1 = demoevent; // Access via abstract class 
		  EndOfTheDay IEnd = demoevent; //Access via Interface 
		  WeatherObserver Iweather = demoevent; // Access via Interface
		  Iweather.onWeatherUpdate("Rain");
		  System.out.println("Weather updated to "+ Event.getlatestWeather());
		  
		  // subtyping and multityping through abstract class Disaster and Interface SeverityCalculator
		   FloodDisaster demoDisaster = new FloodDisaster(); 
		   Disaster disaster = demoDisaster; // Access via abstract class 
		   SeverityCalculator severity = demoDisaster; // Access via Interface 
		   //disaster.setZone(demozone);
		   int Severity = (severity.determineSeverity("Rain")); // severity determined through the interface SeverityCalculator
		  
		  disaster.initializeCasualties(Severity,demozone); 
		  Event.addDailyDisasters(1);
		  demozone.setDisasterOngoing(true);
		  demozone.setCurrentDisaster(disaster);
		  System.out.println(disaster);
		  
		  demoRescue.assignToZone(demozone);
		  demoAmbulance.assignToZone(demozone);
		  demoPolice.assignToZone(demozone);
		  
		  demoRescue.action(0, null,null, null, tm);
		  demoAmbulance.action(0,null,null,sm,tm);
		  demoPolice.action(0, null,null, null, tm);
		  
		  demoRescue.releaseFromZone();
		  demoAmbulance.releaseFromZone();
		  demoPolice.releaseFromZone();
		  
		  System.out.println("\nDaily attributes Before End OF The Day"); 
		  System.out.printf("\n======================================== DAILY SUMMARY FOR DEMO DAY ========================================\n"); 
		  System.out.printf("%-10s | %10s | %10s | %10s | %10s | %16s | %20s\n",
		  "Disasters","Births", "Deaths", "Injuries", "Rescues", "Total Population","Food in the city (%)"); 
		  System.out.println("----------------------------------------------------------------------------------------------------------"); 
		  System.out.printf("%-10d | %10d | %10d | %10d | %10d | %16d | %20d%n",
		  Event.getDailyDisasters(), Event.getDailyBirths(), Event.getDailyDeaths(),
		  Event.getDailyInjured(), Event.getDailyrescued(), Event.getTotalPopulation(),
		  Event.getTotalFoodPercentage()); 
		  System.out.println("----------------------------------------------------------------------------------------------------------");
		  
		  IEnd.EndOfTheDayEvent();
		  
		  System.out.println("\nDaily attributes After End OF The Day"); 
		  System.out.printf("\n======================================== DAILY SUMMARY FOR DEMO DAY ========================================\n"); 
		  System.out.printf("%-10s | %10s | %10s | %10s | %10s | %16s | %20s\n",
		  "Disasters","Births", "Deaths", "Injuries", "Rescues", "Total Population",
		  "Food in the city (%)"); 
		  System.out.println("----------------------------------------------------------------------------------------------------------"); 
		  System.out.printf("%-10d | %10d | %10d | %10d | %10d | %16d | %20d%n",
		  Event.getDailyDisasters(), Event.getDailyBirths(), Event.getDailyDeaths(),
		  Event.getDailyInjured(), Event.getDailyrescued(), Event.getTotalPopulation(),
		  Event.getTotalFoodPercentage()); 
		  System.out.println("----------------------------------------------------------------------------------------------------------"); 
		  Event.resetTotalValues(); 
		  sm.removeHospital(demoHospital); 
		  demozone =null; 
		  demoHospital = null; 
		  demoRescue = null; 
		  demoAmbulance = null;
		  demoPolice = null; 
		  demoevent = null; 
		  demoDisaster = null;
		  
		  System.out.println("\n---- Demonstration Ended ----");
		 
		
		Zone Z1 = new Zone("Zone 1",sm);
		Zone Z2 = new Zone("Zone 2",sm);
		Zone Z3 = new Zone("Zone 3",sm);
		Zone Z4 = new Zone("Zone 4",sm);
		
		Hospital H1 = new Hospital("Hospital 1", 100, Z1,sm); 
		Hospital H2 = new Hospital("Hospital 2",100, Z2,sm); 
		Hospital H3 = new Hospital("Hospital 3", 100, Z3,sm); 
		Hospital H4 = new Hospital("Hospital 4", 100, Z4,sm);
		
		
		RescueUnit R1 = new RescueUnit("Rescue Unit 1",sm);
		RescueUnit R2 = new RescueUnit("Rescue Unit 2",sm);
		//RescueUnit R3 = new RescueUnit("Rescue Unit 3",sm);
		
		AmbulanceUnit A1 = new AmbulanceUnit("Ambulance 1",sm);
		//AmbulanceUnit A2 = new AmbulanceUnit("Ambulance 2",sm);
		//AmbulanceUnit A3 = new AmbulanceUnit("Ambulance 3",sm);
		
		FoodSupplyTeam F1 = new FoodSupplyTeam("Food unit 1",sm);
		//FoodSupplyTeam F2 = new FoodSupplyTeam("Food unit 2",sm);
		
		PoliceUnit P1 = new PoliceUnit("Police unit 1",sm);
		PoliceUnit P2 = new PoliceUnit("Police unit 2",sm);
		PoliceUnit P3 = new PoliceUnit("Police unit 3",sm);
		PoliceUnit P4 = new PoliceUnit("Police unit 4",sm);
		sm.addDisaster(new FloodDisaster());
		sm.addDisaster(new EarthquakeDisaster());
		sm.addDisaster(new ThunderstromDisaster());
		sm.addDisaster(new FireAccidentDisaster());
		
		int totalHours = 5*24;
		for (int hour = 0; hour < totalHours; hour++) {
			int hourOfDay = hour % 24;
            if (hour % 6 == 0 && hour !=0)
                calendar.scheduleEvent(new WeatherChangeEvent(hour,ws,tm));
            if ((hourOfDay == 6)) { //||(hourOfDay == 13)
            	calendar.scheduleEvent(new DisasterOccurEvent(hour,sm,calendar,tm));
            }
            
	        if (hour % 10 == 0 && hour !=0) {
	        	calendar.scheduleEvent(new HospitalTreatmentEvent( hour, sm,  tm));
	        }
	        if (hour % 24 == 23) calendar.scheduleEvent(new EndOfTheDayEvent( hour, sm, tm));
	     

        }
		
        while (calendar.hasNext()) {
            Event e = calendar.nextEvent();
            tm.advanceTime(e.getHour() - tm.getTotalHours());
            e.process();
             
            }
        
        System.out.printf("\n======================================= FINAL SUMMARY AFTER DAY %d =======================================\n", tm.getCurrentDay());
        System.out.printf("%-10s | %10s | %10s | %10s | %10s | %16s | %20s\n",
        		"Disasters","Births", "Deaths", "Injuries", "Rescues", "Total Population", "Food in the city (%)");
        System.out.println("----------------------------------------------------------------------------------------------------------");
        System.out.printf("%-10d | %10d | %10d | %10d | %10d | %16d | %20d%n",
                Event.getTotalDisasters(), Event.getTotalBirths(), Event.getTotalDeaths(), Event.getTotalInjured(), 
                Event.getTotalrescued(), Event.getTotalPopulation(), Event.getTotalFoodPercentage());
        System.out.println("----------------------------------------------------------------------------------------------------------");
        
        Messina.displayStatus();
        
        }
		
		
	}


