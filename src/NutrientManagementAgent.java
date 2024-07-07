import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;

public class NutrientManagementAgent extends Agent {
    private String plantType;

    protected void setup() {
        Object[] args = getArguments();
        if (args != null && args.length > 0) {
            plantType = (String) args[0];
        }

        System.out.println("NutrientManagementAgent for " + plantType + " started.");

        addBehaviour(new TickerBehaviour(this, SystemConfig.BEHAVIOR_INTERVAL) {
            protected void onTick() {
                try {
                    switch (plantType) {
                        case "Tomatoes":
                            System.out.println("  - Nutrients for Tomatoes:");
                            System.out.println("    - Nutrient Frequency: Every " + SystemConfig.TOMATOES_NUTRIENT_FREQ + " days");
                            break;
                        case "Cucumbers":
                            System.out.println("  - Nutrients for Cucumbers:");
                            System.out.println("    - Nutrient Frequency: Every " + SystemConfig.CUCUMBERS_NUTRIENT_FREQ + " days");
                            break;
                        default:
                            System.out.println("  - Distributing general nutrients...");
                            break;
                    }
                    Thread.sleep(SystemConfig.NUTRIENT_DISTRIBUTION_DELAY);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
