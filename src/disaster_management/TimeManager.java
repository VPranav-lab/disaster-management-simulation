package disaster_management;

public class TimeManager {
	private int totalHours = 0;      
    public void advanceTime(int hours) {
        totalHours += hours;
    }
    public int getCurrentDay() {
        return (totalHours / 24) + 1;
    }
    public int getCurrentHour() {
        return totalHours % 24;
    }
    public int getTotalHours() {
        return totalHours;
    }
    public String getFormattedTime() {
        return "[Day:" + getCurrentDay() + " - Hour:" + getCurrentHour()+"]";
    }

}
