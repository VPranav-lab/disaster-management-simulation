package disaster_management;

import java.util.Random;

public class WeatherChangeEvent extends Event {
	private WeatherStation ws;
	private TimeManager tm;
	private static boolean registered = false;
	public WeatherChangeEvent(int hour, WeatherStation ws,TimeManager tm) {
		super(hour, 0);
		this.ws =ws;
		this.tm =tm;
		if (!registered) {
            ws.register(this);
            registered = true;
            //System.out.println("------> Registered to Weather Station");
        }
		
	}
	
	public WeatherChangeEvent() {
		super(0, 0);
		//this.ws =ws;
		//this.tm =tm;
		
	}

	@Override
	public void process() {
		String[] conditions ={"Clear","Wind", "Rain", "Thunderstorm"}; 
		String weather = conditions[new Random().nextInt(conditions.length)]; 
		ws.updateWeather(weather);
		System.out.println("=================================================");
        System.out.printf(tm.getFormattedTime()+" Weather updated to "+ Event.getlatestWeather()+"\n"); 
        System.out.println("=================================================");
		
	}

}
