package disaster_management;

import java.util.Random;

public class ThunderstromDisaster extends Disaster {
	
	private final int[] ranges = {5, 15, 0, 5, 0, 15};
	public ThunderstromDisaster(int severity, Zone zone) { 
		super("Thunderstrom",severity,zone);
	}
	
	public ThunderstromDisaster() { 
		super("Thunderstrom");
	}
	   
	 
	@Override
    public int determineSeverity(String weather) {
        Random rand = new Random();
        if (weather.equals("Rain")) return rand.nextInt(2) + 4; // 4–5 during rain
        return super.determineSeverity(weather);
    }
    @Override
    public int[] getBaseRanges() {
        return ranges;
    }
}
