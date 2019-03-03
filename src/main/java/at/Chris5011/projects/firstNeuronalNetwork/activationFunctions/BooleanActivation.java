package at.Chris5011.projects.firstNeuronalNetwork.activationFunctions;

public class BooleanActivation implements ActivationFunction {
    @Override
    public double activation(double input) {
        return input < 0 ? 0.0 : 1.0;
    }

    @Override
    public String toString() {
        return "BooleanActivation";
    }
}
