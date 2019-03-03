package at.Chris5011.projects.firstNeuronalNetwork.activationFunctions;

public class TangensHyperbolicusActivation implements ActivationFunction {
    @Override
    public double activation(double input) {
        double epx = Math.exp(input);
        double enx = Math.exp(-input);
        return (epx - enx) / (epx + enx);
    }

    @Override
    public String toString() {
        return "TangensHyperbolicusActivation";
    }
}
