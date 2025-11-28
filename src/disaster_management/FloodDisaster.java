package disaster_management;

import java.util.Random;

public class FloodDisaster extends Disaster {
	private final int[] ranges = {10, 70, 0, 10, 0, 10};
	public FloodDisaster(int severity, Zone zone) {
        super("Flood",severity,zone);
        
    }
	
	public FloodDisaster() {
        super("Flood");
        
    }
	
	@Override
	public int determineSeverity(String weather) {
        Random rand = new Random();
        if (weather.equals("Rain")) return rand.nextInt(2) + 4; // 4–5
        return super.determineSeverity(weather);
    }
	
    @Override
    public int[] getBaseRanges() {
        return ranges;
    }
}
