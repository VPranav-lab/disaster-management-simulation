package disaster_management;
import java.util.PriorityQueue;
public class EventCalendar {
	    private PriorityQueue<Event> Queue;
	    public EventCalendar() {
	        this.Queue = new PriorityQueue<>();
	    }

	    public void scheduleEvent(Event event) {
	        Queue.add(event);
	    }

	    public Event nextEvent() {
	        return Queue.poll();
	    }

	    public boolean hasNext() {
	        return !Queue.isEmpty();
	    }

}
