import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class SystemConfig {
    // Interval for all periodic behaviours in milliseconds (10 times slower)
    public static final long BEHAVIOR_INTERVAL = 2500; // Approximately 45 seconds

    // Climate settings
    public static final int TOMATOES_HUMIDITY = 80;
    public static final int TOMATOES_TEMPERATURE = 24;
    public static final int CUCUMBERS_HUMIDITY = 85;
    public static final int CUCUMBERS_TEMPERATURE = 22;
    public static final int LETTUCE_HUMIDITY = 70;
    public static final int LETTUCE_TEMPERATURE = 18;
    public static final int HERBS_HUMIDITY = 75;
    public static final int HERBS_TEMPERATURE = 20;

    // Irrigation settings
    public static final int TOMATOES_WATER_FREQ = 2; // Every 2 days
    public static final int TOMATOES_WATER_AMOUNT = 250; // 250 ml per plant
    public static final int CUCUMBERS_WATER_FREQ = 1; // Every day
    public static final int CUCUMBERS_WATER_AMOUNT = 300; // 300 ml per plant
    public static final int LETTUCE_WATER_FREQ = 3; // Every 3 days
    public static final int LETTUCE_WATER_AMOUNT = 200; // 200 ml per plant
    public static final int HERBS_WATER_FREQ = 2; // Every 2 days
    public static final int HERBS_WATER_AMOUNT = 150; // 150 ml per plant

    // Lighting settings
    public static final int TOMATOES_LIGHT_HOURS = 16;
    public static final int CUCUMBERS_LIGHT_HOURS = 14;
    public static final int LETTUCE_LIGHT_HOURS = 12;
    public static final int HERBS_LIGHT_HOURS = 14;

    // Nutrient settings
    public static final int TOMATOES_NUTRIENT_FREQ = 7; // Every 7 days
    public static final int CUCUMBERS_NUTRIENT_FREQ = 5; // Every 5 days
    public static final int LETTUCE_NUTRIENT_FREQ = 10; // Every 10 days
    public static final int HERBS_NUTRIENT_FREQ = 8; // Every 8 days

    // Delays in milliseconds for simulating operations (10 times slower)
    public static final long CLIMATE_ADJUSTMENT_DELAY = 20000; // 20 seconds
    public static final long IRRIGATION_DELAY = 15000; // 15 seconds
    public static final long LIGHTING_ADJUSTMENT_DELAY = 10000; // 10 seconds
    public static final long NUTRIENT_DISTRIBUTION_DELAY = 18000; // 18 seconds
    public static final long DISTRIBUTION_DELAY = 20000; // 20 seconds for distributing produce
    public static final long REPLANTING_DELAY = 20000; // 20 seconds for replanting

    // Prices per unit for produce
    public static final double TOMATOES_PRICE_PER_UNIT = 1.5; // 1.5 currency units per tomato
    public static final double CUCUMBERS_PRICE_PER_UNIT = 1.0; // 1.0 currency units per cucumber
    public static final double LETTUCE_PRICE_PER_UNIT = 0.8; // 0.8 currency units per lettuce
    public static final double HERBS_PRICE_PER_UNIT = 2.0; // 2.0 currency units per herb bunch

    // Predefined units sold per cycle
    public static final int TOMATOES_UNITS_SOLD = 50;
    public static final int CUCUMBERS_UNITS_SOLD = 60;
    public static final int LETTUCE_UNITS_SOLD = 30;
    public static final int HERBS_UNITS_SOLD = 20;

    // Display configuration
    public static final boolean DISPLAY_TERMINAL_OUTPUT = true;
    public static final String LOG_FILE_PATH = "revenue_log.txt";

    // GUI reference
    public static MainFrame mainFrame;

    // Method to log revenue to file
    public static void logRevenueToFile(String logMessage) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE_PATH, true))) {
            writer.write(logMessage);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Ensure log file exists or create it
    static {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE_PATH, true))) {
            // This block intentionally left blank to just ensure the file is created
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
