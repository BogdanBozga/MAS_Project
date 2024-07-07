import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;

public class LightingControlAgent extends Agent {
    private String plantType;

    protected void setup() {
        Object[] args = getArguments();
        if (args != null && args.length > 0) {
            plantType = (String) args[0];
        }

        System.out.println("LightingControlAgent for " + plantType + " started.");

        addBehaviour(new TickerBehaviour(this, SystemConfig.BEHAVIOR_INTERVAL) {
            protected void onTick() {
                try {
                    switch (plantType) {
                        case "Tomatoes":
                            System.out.println("  - Lighting for Tomatoes:");
                            System.out.println("    - Light Hours: " + SystemConfig.TOMATOES_LIGHT_HOURS + " hours per day");
                            break;
                        case "Cucumbers":
                            System.out.println("  - Lighting for Cucumbers:");
                            System.out.println("    - Light Hours: " + SystemConfig.CUCUMBERS_LIGHT_HOURS + " hours per day");
                            break;
                        default:
                            System.out.println("  - Adjusting general lighting settings...");
                            break;
                    }
                    Thread.sleep(SystemConfig.LIGHTING_ADJUSTMENT_DELAY);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
