import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;

public class LogisticsAndDistributionAgent extends Agent {
    private String plantType;
    private double totalRevenue;

    protected void setup() {
        Object[] args = getArguments();
        if (args != null && args.length > 0) {
            plantType = (String) args[0];
        }

        totalRevenue = 0;
        if (SystemConfig.DISPLAY_TERMINAL_OUTPUT) {
            System.out.println("LogisticsAndDistributionAgent for " + plantType + " started.");
        }

        addBehaviour(new TickerBehaviour(this, SystemConfig.BEHAVIOR_INTERVAL) {
            protected void onTick() {
                try {
                    double unitsSold = getUnitsSold(plantType);
                    double revenue = unitsSold * getPricePerUnit(plantType);
                    totalRevenue += revenue;

                    String logMessage = String.format("Sold %.2f units of %s for %.2f currency units. Total revenue: %.2f currency units.",
                            unitsSold, plantType, revenue, totalRevenue);

                    if (SystemConfig.DISPLAY_TERMINAL_OUTPUT) {
                        System.out.println(logMessage);
                    }

                    SystemConfig.logRevenueToFile(logMessage);
                    SystemConfig.mainFrame.updateStatus(plantType, logMessage);
                    SystemConfig.mainFrame.updateRevenue(revenue, logMessage);

                    Thread.sleep(SystemConfig.DISTRIBUTION_DELAY); // Simulate distribution delay

                    if (SystemConfig.DISPLAY_TERMINAL_OUTPUT) {
                        System.out.println("Replanting " + plantType + "...");
                    }

                    logMessage = "Replanting " + plantType + "...";
                    SystemConfig.logRevenueToFile(logMessage);
                    SystemConfig.mainFrame.updateStatus(plantType, logMessage);

                    Thread.sleep(SystemConfig.REPLANTING_DELAY); // Simulate replanting delay

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private double getPricePerUnit(String plantType) {
        switch (plantType) {
            case "Tomatoes":
                return SystemConfig.TOMATOES_PRICE_PER_UNIT;
            case "Cucumbers":
                return SystemConfig.CUCUMBERS_PRICE_PER_UNIT;
            case "Lettuce":
                return SystemConfig.LETTUCE_PRICE_PER_UNIT;
            case "Herbs":
                return SystemConfig.HERBS_PRICE_PER_UNIT;
            default:
                return 0;
        }
    }

    private int getUnitsSold(String plantType) {
        switch (plantType) {
            case "Tomatoes":
                return SystemConfig.TOMATOES_UNITS_SOLD;
            case "Cucumbers":
                return SystemConfig.CUCUMBERS_UNITS_SOLD;
            case "Lettuce":
                return SystemConfig.LETTUCE_UNITS_SOLD;
            case "Herbs":
                return SystemConfig.HERBS_UNITS_SOLD;
            default:
                return 0;
        }
    }
}
