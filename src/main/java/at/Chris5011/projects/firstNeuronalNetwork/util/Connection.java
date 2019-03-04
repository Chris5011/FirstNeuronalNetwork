package at.Chris5011.projects.firstNeuronalNetwork.util;

import at.Chris5011.projects.firstNeuronalNetwork.neurons.Neuron;

public class Connection {
    private Neuron neuron;
    private double weight;

    public Connection(Neuron neuron, double weight) {
        this.neuron = neuron;
        this.weight = weight;
    }

    public double getValue() {
        return this.neuron.getValue() * weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void addWeight(double bigDelta) {
        this.weight += bigDelta;
    }
}
