package disaster_management;

import java.util.Random;

public class FireAccidentDisaster extends Disaster{
	
	private final int[] ranges = {5, 25, 0, 15, 0, 25};
	public FireAccidentDisaster(int severity, Zone zone) {
        super("Fire Accident",severity,zone);
    }
	
	public FireAccidentDisaster() {
        super("Fire Accident");
    }
	
	@Override
    public int determineSeverity(String weather) {
        Random rand = new Random();
        if (weather.equals("Wind")) return rand.nextInt(3) + 3; // 3–5 when windy
        if (weather.equals("Rain")) return rand.nextInt(2) + 1; // 1–2 when raining
        return super.determineSeverity(weather);
    }
    @Override
    public int[] getBaseRanges() {
        return ranges;
    }

	
}
