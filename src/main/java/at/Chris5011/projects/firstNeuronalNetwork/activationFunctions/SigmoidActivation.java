package at.Chris5011.projects.firstNeuronalNetwork.activationFunctions;

public class SigmoidActivation implements ActivationFunction {
    @Override
    public double activation(double input) {
        return 1.0 / (1.0 + Math.exp(-input));
    }

    @Override
    public String toString() {
        return "SigmoidActivation";
    }
}
