package disaster_management;

import java.util.Random;

public class EarthquakeDisaster extends Disaster  {
	
	private final int[] ranges = {10, 30, 0, 5, 0, 25};
	//[injuredMin, injuredMax, deathMin, deathMax, trappedMin, trappedMax]
	
	public EarthquakeDisaster(int severity, Zone zone) {
        super("Earthquake",severity,zone);
    }
	
	public EarthquakeDisaster() {
        super("Earthquake");
    }
	
	@Override
	public int determineSeverity(String weather) {
        Random rand = new Random();
        return rand.nextInt(5) + 1; // 1-5
    }
	
    @Override
    public int[] getBaseRanges() {
        return ranges;
    }
	
}
