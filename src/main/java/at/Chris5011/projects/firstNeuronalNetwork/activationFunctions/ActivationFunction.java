package at.Chris5011.projects.firstNeuronalNetwork.activationFunctions;

public interface ActivationFunction {
    BooleanActivation booleanActivation = new BooleanActivation();
    IdentityActivation identityActivation = new IdentityActivation();
    SigmoidActivation sigmoidActivation = new SigmoidActivation();
    TangensHyperbolicusActivation activationTangensHyperbolicus = new TangensHyperbolicusActivation();
    ReLuActivationFunction activationReLu = new ReLuActivationFunction();

    double activation(double input);
}
