package disaster_management;

public abstract class Event implements Comparable<Event>, WeatherObserver,EndOfTheDay {
	
    private int hour;
    private int priority;
    
    private static int totalPopulation = 800;
    private static int totalFoodPercentage = 100;
    private static int totalBirths=0;
    private static int totalDeaths=0;
    private static int totalInjured=0;
    private static int totalrescued=0;
    private static int totalDisasters=0;
    
    private static int dailyBirths=0;
    private static int dailyDeaths=0;
    private static int dailyInjured=0;
    private static int dailyrescued=0;
    private static int dailyDisasters=0;
    private static String latestWeather = "clear";
    

    public Event(int hour, int priority) {
        this.hour = hour;
        this.priority = priority;
        
    }

    public abstract void process();
    
    public void handleBy(VolunteerTeam team, SimulationManager sm, TimeManager tm) throws NotAllowedException {
        throw new NotAllowedException("Method Not Allowed");
    }
    
    @Override
    public int compareTo(Event other) {
        if (this.hour != other.hour) {return this.hour - other.hour;}
        return this.priority - other.priority;
    }
    
    
    
    public static int getTotalPopulation() {return totalPopulation;}
    public static int getTotalBirths() {return totalBirths;}
    public static int getTotalDeaths() {return totalDeaths;}
    public static int getTotalInjured() {return totalInjured;}
    public static int getTotalrescued() {return totalrescued;}
    public static int getDailyBirths() {return dailyBirths;}
    public static int getDailyDeaths() {return dailyDeaths;}
    public static int getDailyInjured() {return dailyInjured;}
    public static int getDailyrescued() {return dailyrescued;}
    public static String getlatestWeather() {return latestWeather;}
    public static int getTotalFoodPercentage() {return totalFoodPercentage;}
    public static int getTotalDisasters() {return totalDisasters;}
    public static int getDailyDisasters() {return dailyDisasters;}
    public int getHour() { return hour; }
	
	public void setHour(int hour) {
		this.hour = hour;
	}
    
	public static void setTotalDisasters(int totalDisasters) {
		Event.totalDisasters = totalDisasters;
	}

	public static void setDailyDisasters(int dailyDisasters) {
		Event.dailyDisasters = dailyDisasters;
	}

	public static void setTotalFoodPercentage(int totalFoodPercentage) {Event.totalFoodPercentage = totalFoodPercentage;}
	public static void setTotalPopulation(int totalPopulation) {Event.totalPopulation = totalPopulation;}
    public static void setTotalBirths(int totalBirths) {Event.totalBirths = totalBirths;}
    public static void setTotalDeaths(int totalDeaths) {Event.totalDeaths = totalDeaths;}
    public static void setTotalInjured(int totalInjured) {Event.totalInjured = totalInjured;}
    public static void setTotalrescued(int totalrescued) {Event.totalrescued = totalrescued;}
    public static void setDailyBirths(int dailyBirths) {Event.dailyBirths = dailyBirths;}
    public static void setDailyDeaths(int dailyDeaths) {Event.dailyDeaths = dailyDeaths;}
    public static void setDailyInjured(int dailyInjured) {Event.dailyInjured = dailyInjured;}
    public static void setDailyrescued(int dailyrescued) {Event.dailyrescued = dailyrescued;}
    
    public static void resetTotalValues() {
    	totalPopulation = 800;
        totalFoodPercentage = 100;
        totalBirths=0;
        totalDeaths=0;
        totalInjured=0;
        totalrescued=0;
        totalDisasters=0;
        latestWeather = "clear";
    }
    public static void minustotalPopulation(int num) throws SimulationException {
    	totalPopulation -= num;
    	if (totalPopulation <= 0 ) {
			throw new SimulationException(
			"Total population became 0" );
        }
    	
    }
    
	public static void addtotalPopulation(int num) {totalPopulation += num;}
	
	public static void minusTotalFoodPercentage(int num) throws FoodShortageException {
		totalFoodPercentage -= num;
		if (totalFoodPercentage < 0) {
	        totalFoodPercentage = 0;
	        throw new FoodShortageException("⚠️ Food supply became 0%! Emergency situation detected.");
	    }
	}
	public static void addTotalFoodPercentage(int num) {totalFoodPercentage += num;}
    public static void addtotalBirths(int num) {totalBirths += num;}
    public static void addtotaldeaths(int num) {totalDeaths += num;}
    public static void addtotalInjured(int num) {totalInjured += num;}
    public static void addtotalrescued(int num) {totalrescued += num;}
    public static void adddailyBirths(int num) {dailyBirths += num;}
    public static void adddailyDeaths(int num) {dailyDeaths += num;}
    public static void adddailyInjured(int num) {dailyInjured += num;}
    public static void adddailyrescued(int num) {dailyrescued += num;}
    public static void addDailyDisasters(int num) {dailyDisasters += num;}
    public static void addTotalDisasters(int num) {totalDisasters += num;}
	
    @Override
	public void EndOfTheDayEvent() {
    	
    	dailyBirths = 0;
    	dailyDeaths = 0;
    	dailyInjured = 0;
    	dailyrescued = 0;
    	dailyDisasters = 0;
		
	}
	public static  void setlatestWeather(String latestWeather) {
		Event.latestWeather = latestWeather;
	}
	
	@Override
	public void onWeatherUpdate(String weather) {
		setlatestWeather(weather);
		
	}
	

	
	

	
}
