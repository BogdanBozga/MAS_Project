import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;

public class GrowthMonitoringAgent extends Agent {
    protected void setup() {
        System.out.println("Agent " + getLocalName() + " started.");
        addBehaviour(new CyclicBehaviour(this) {
            public void action() {
                // Implementation of growth monitoring logic
                System.out.println("Monitoring plant growth...");
            }
        });
    }
}
