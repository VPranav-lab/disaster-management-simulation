package disaster_management;

import java.util.ArrayList;
import java.util.List;

public class WeatherStation {
	private List<WeatherObserver> observers = new ArrayList<>();
    private String currentWeather = "clear";

    public void register(WeatherObserver obs) {
        observers.add(obs);
    }
    

	public String getCurrentWeather() {
		return currentWeather;
	}

	
	  public void updateWeather(String weather) { 
		  this.currentWeather= weather;
		  for (WeatherObserver obs : observers) { 
			  obs.onWeatherUpdate(currentWeather); 
			  } 
	  }
	 
}
