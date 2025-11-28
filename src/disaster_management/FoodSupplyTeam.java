package disaster_management;

import java.util.Random;

public class FoodSupplyTeam extends Resource {
	public FoodSupplyTeam(String id,SimulationManager sm) {
        super(id, "FoodSupply");
        sm.addFoodUnit(this);
    }

	@Override
	public void action(int hour,Event e, EventCalendar calendar, SimulationManager sm,
			TimeManager tm) {
		Random random = new Random();
		if(getCurrentZone()!=null) {
            System.out.println(tm.getFormattedTime()+"  Food supply Team- "+getId()+" arrived and supplying food in "+getCurrentZone().getName());
			}else {
				System.out.println(tm.getFormattedTime()+"  Food supply Team- "+getId()+" arrived and supplying food in the affected zones ");

			}
		int foodPercent = random.nextInt(4,15)+1;
        Event.addTotalFoodPercentage(foodPercent);
        System.out.println("  --> Food percentage of the whole city is increased by - "+foodPercent+"%. Current Food percentage :"+Event.getTotalFoodPercentage());
	}
}
