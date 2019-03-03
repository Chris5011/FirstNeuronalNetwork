package at.Chris5011.projects.firstNeuronalNetwork.activationFunctions;

public class ReLuActivationFunction implements ActivationFunction {
    @Override
    public double activation(double input) {
        return input <= 0 ? 0 : input;
    }

    @Override
    public String toString() {
        return "ReLuActivationFunction";
    }
}
