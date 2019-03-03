package at.Chris5011.projects.firstNeuronalNetwork.activationFunctions;

public class IdentityActivation implements ActivationFunction {
    @Override
    public double activation(double input) {
        return input;
    }

    @Override
    public String toString() {
        return "IdentityActivation";
    }
}
