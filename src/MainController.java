import jade.core.Runtime;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class MainController {
    public static void main(String[] args) {
        Runtime rt = Runtime.instance();
        Profile p = new ProfileImpl();
        AgentContainer mainContainer = rt.createMainContainer(p);

        // Create and display the GUI
        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame();
            SystemConfig.mainFrame = mainFrame;
            mainFrame.setVisible(true);
        });

        try {
            String[] agentNames = {"ClimateControlAgent", "GrowthMonitoringAgent", "HarvestingAgent",
                    "IrrigationAgent", "LightingControlAgent", "LogisticsAndDistributionAgent",
                    "NutrientManagementAgent"};
            String[] plantTypes = {"Tomatoes", "Cucumbers", "Lettuce", "Herbs"};
            for (String plant : plantTypes) {
                for (String agentName : agentNames) {
                    AgentController ac = mainContainer.createNewAgent(agentName + plant, agentName, new Object[]{plant});
                    ac.start();
                }
            }
        } catch (StaleProxyException e) {
            e.printStackTrace();
        }
    }
}

class MainFrame extends JFrame {
    private final Map<String, JTextArea> plantStatusAreas;
    private final JTextArea revenueTextArea;
    private double totalRevenue = 0;

    public MainFrame() {
        setTitle("Indoor Farming System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        plantStatusAreas = new HashMap<>();

        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel plantsPanel = new JPanel(new GridLayout(2, 2));
        addPlantPanel(plantsPanel, "Tomatoes");
        addPlantPanel(plantsPanel, "Cucumbers");
        addPlantPanel(plantsPanel, "Lettuce");
        addPlantPanel(plantsPanel, "Herbs");

        tabbedPane.addTab("Plants Status", plantsPanel);

        revenueTextArea = new JTextArea();
        revenueTextArea.setEditable(false);
        JScrollPane revenueScrollPane = new JScrollPane(revenueTextArea);
        tabbedPane.addTab("Revenue", revenueScrollPane);

        add(tabbedPane, BorderLayout.CENTER);
    }

    private void addPlantPanel(JPanel parentPanel, String plantType) {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder(plantType));
        panel.setLayout(new BorderLayout());

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        plantStatusAreas.put(plantType, textArea);
        parentPanel.add(panel);
    }

    public void updateStatus(String plantType, String message) {
        JTextArea textArea = plantStatusAreas.get(plantType);
        if (textArea != null) {
            SwingUtilities.invokeLater(() -> {
                textArea.append(message + "\n");
                textArea.setCaretPosition(textArea.getDocument().getLength());
            });
        }
    }

    public void updateRevenue(double amount, String message) {
        totalRevenue += amount;
        String revenueMessage = String.format("%s\nTotal Revenue: %.2f\n", message, totalRevenue);
        SwingUtilities.invokeLater(() -> {
            revenueTextArea.append(revenueMessage);
            revenueTextArea.setCaretPosition(revenueTextArea.getDocument().getLength());
        });
    }
}
