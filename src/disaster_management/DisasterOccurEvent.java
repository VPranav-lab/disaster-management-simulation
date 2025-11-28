package disaster_management;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class DisasterOccurEvent extends Event{
	private EventCalendar calendar;
	private SimulationManager sm;
	private TimeManager tm;
	private List<Zone> availableZones = new ArrayList<>();
	public DisasterOccurEvent(int hour, SimulationManager sm,EventCalendar calendar,TimeManager tm) {
		super(hour, 1);
		this.sm=sm;
		this.tm=tm;
		this.calendar=calendar;
	}

	@Override
	public void process() {
		Random rand = new Random();
		availableZones.clear();
        for(Zone R : sm.getZones()) {
			if(R.isDisasterOngoing() != true) {
				availableZones.add(R);
			}
		}
        Collections.shuffle(availableZones);
        int numberOfDisasters = rand.nextInt(availableZones.size())+1;
        Event.addDailyDisasters(numberOfDisasters);
        Event.addTotalDisasters(numberOfDisasters);
        int hour = getHour();
        for (int i = 0; i < numberOfDisasters ; i++) {
            Zone zone = availableZones.get(i);
            Disaster disaster = randomDisaster(rand);
            
            calendar.scheduleEvent(new SingleDisasterEvent(hour, disaster,zone,sm, calendar,tm));
            hour+=3;
        }
	}
	
	private Disaster randomDisaster(Random rand) {
        List<Disaster> disasters = sm.getDisasters();
        return disasters.get(rand.nextInt(disasters.size()));
    }
	
}
