package at.Chris5011.projects.firstNeuronalNetwork.neurons;

import at.Chris5011.projects.firstNeuronalNetwork.activationFunctions.ActivationFunction;
import at.Chris5011.projects.firstNeuronalNetwork.util.Connection;

import java.util.ArrayList;
import java.util.List;

public class WorkingNeuron implements Neuron {

    private double value;
    private List<Connection> connections = new ArrayList<>();
    private ActivationFunction activationFunction = ActivationFunction.identityActivation;

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public double getValue() {
        double sum = 0;
        for (Connection c : connections) {
            sum += c.getValue();
        }
        return activationFunction.activation(sum);
    }

    public void deltaLearning(double epsilon, double smallDelta) {
        for (int i = 0; i < connections.size(); i++) {
            double bigDelta = epsilon * smallDelta * connections.get(i).getValue();
            connections.get(i).addWeight(bigDelta);
        }
    }

    public void makeConnection(Connection con) {
        this.connections.add(con);
    }

    public void changeActivationFunction(ActivationFunction activationFunction) {
        this.activationFunction = activationFunction;
    }

}
