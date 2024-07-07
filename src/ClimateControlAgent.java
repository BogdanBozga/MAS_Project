import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ClimateControlAgent extends Agent {
    private String plantType;

    protected void setup() {
        Object[] args = getArguments();
        if (args != null && args.length > 0) {
            plantType = (String) args[0];
        }

        if (SystemConfig.DISPLAY_TERMINAL_OUTPUT) {
            System.out.println("ClimateControlAgent for " + plantType + " started.");
        }

        addBehaviour(new TickerBehaviour(this, SystemConfig.BEHAVIOR_INTERVAL) {
            protected void onTick() {
                try {
                    String climateMessage = "";
                    if (SystemConfig.DISPLAY_TERMINAL_OUTPUT) {
                        switch (plantType) {
                            case "Tomatoes":
                                climateMessage = String.format("Adjusting climate for Tomatoes: Humidity: %d%%, Temperature: %d°C",
                                        SystemConfig.TOMATOES_HUMIDITY, SystemConfig.TOMATOES_TEMPERATURE);
                                break;
                            case "Cucumbers":
                                climateMessage = String.format("Adjusting climate for Cucumbers: Humidity: %d%%, Temperature: %d°C",
                                        SystemConfig.CUCUMBERS_HUMIDITY, SystemConfig.CUCUMBERS_TEMPERATURE);
                                break;
                            case "Lettuce":
                                climateMessage = String.format("Adjusting climate for Lettuce: Humidity: %d%%, Temperature: %d°C",
                                        SystemConfig.LETTUCE_HUMIDITY, SystemConfig.LETTUCE_TEMPERATURE);
                                break;
                            case "Herbs":
                                climateMessage = String.format("Adjusting climate for Herbs: Humidity: %d%%, Temperature: %d°C",
                                        SystemConfig.HERBS_HUMIDITY, SystemConfig.HERBS_TEMPERATURE);
                                break;
                            default:
                                climateMessage = "Monitoring climate for unspecified plants...";
                                break;
                        }
                        System.out.println(climateMessage);
                    }
                    logToFile("ClimateControlAgent_log.txt", climateMessage);
                    SystemConfig.mainFrame.updateStatus(plantType, climateMessage);
                    Thread.sleep(SystemConfig.CLIMATE_ADJUSTMENT_DELAY);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void logToFile(String filename, String message) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write(message);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
