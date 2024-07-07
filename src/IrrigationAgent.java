import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;

public class IrrigationAgent extends Agent {
    private String plantType;

    protected void setup() {
        Object[] args = getArguments();
        if (args != null && args.length > 0) {
            plantType = (String) args[0];
        }

        System.out.println("IrrigationAgent for " + plantType + " started.");

        addBehaviour(new TickerBehaviour(this, SystemConfig.BEHAVIOR_INTERVAL) {
            protected void onTick() {
                try {
                    switch (plantType) {
                        case "Tomatoes":
                            System.out.println("  - Irrigation for Tomatoes:");
                            System.out.println("    - Water Frequency: Every " + SystemConfig.TOMATOES_WATER_FREQ + " days");
                            System.out.println("    - Water Amount: " + SystemConfig.TOMATOES_WATER_AMOUNT + " ml per plant");
                            break;
                        case "Cucumbers":
                            System.out.println("  - Irrigation for Cucumbers:");
                            System.out.println("    - Water Frequency: Every " + SystemConfig.CUCUMBERS_WATER_FREQ + " day");
                            System.out.println("    - Water Amount: " + SystemConfig.CUCUMBERS_WATER_AMOUNT + " ml per plant");
                            break;
                        default:
                            System.out.println("  - Adjusting general irrigation settings...");
                            break;
                    }
                    Thread.sleep(SystemConfig.IRRIGATION_DELAY);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
