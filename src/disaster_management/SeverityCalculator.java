package disaster_management;

import java.util.Random;

public interface SeverityCalculator {
	default public int determineSeverity(String weather) {
		Random rand = new Random();
        return rand.nextInt(3) + 1; // Default 1–3
	};
}
