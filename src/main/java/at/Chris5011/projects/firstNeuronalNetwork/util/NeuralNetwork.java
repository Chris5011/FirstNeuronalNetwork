package at.Chris5011.projects.firstNeuronalNetwork.util;

import at.Chris5011.projects.firstNeuronalNetwork.activationFunctions.ActivationFunction;
import at.Chris5011.projects.firstNeuronalNetwork.neurons.InputNeuron;
import at.Chris5011.projects.firstNeuronalNetwork.neurons.WorkingNeuron;

import java.util.ArrayList;
import java.util.List;

public class NeuralNetwork {

    private List<InputNeuron> inputNeuronList = new ArrayList<>(0);
    private List<WorkingNeuron> hiddenNeuronList = new ArrayList<>(0);
    private List<WorkingNeuron> outputNeuronsList = new ArrayList<>(0);

    public InputNeuron createInputNeuron() {
        InputNeuron in = new InputNeuron();
        inputNeuronList.add(in);
        return in;
    }

    public WorkingNeuron createWorkingNeuron() {
        WorkingNeuron wn = new WorkingNeuron();
        outputNeuronsList.add(wn);
        return wn;
    }

    public void createHiddenNeuron(int amount) {
        if (amount < 0)
            throw new IllegalArgumentException("Übergebener Wert ist nicht gültig (<0)");

        for (int i = 0; i < amount; i++) {
            hiddenNeuronList.add(new WorkingNeuron());
        }
    }

    public void createFullMesh() {
        if (hiddenNeuronList.size() == 0) { //Falls keine Hidden - Layers gebraucht / erzeugt werden.
            for (WorkingNeuron wn : outputNeuronsList) {
                for (InputNeuron in : inputNeuronList) {
                    wn.makeConnection(new Connection(in, 0));
                }
            }
        } else {    //Verbinden der Output - Schicht mit der Hidden - Schicht
            for (WorkingNeuron wn : outputNeuronsList) {
                for (WorkingNeuron hidden : hiddenNeuronList) {
                    wn.makeConnection(new Connection(hidden, 0));
                }
            }//Verbinden der Hidden - Schicht mit der Output - Schicht
            for (WorkingNeuron hidden : hiddenNeuronList) {
                for (InputNeuron in : inputNeuronList) {
                    hidden.makeConnection(new Connection(in, 0));
                }
            }
        }

    }

    public void createFullMesh(double... weights) {
        if (hiddenNeuronList.size() == 0) {
            if (weights.length != outputNeuronsList.size() * inputNeuronList.size()) {
                throw new IllegalArgumentException("Ungültige Anzahl an gewichtungen übergeben!");
            }
            int index = 0;
            for (WorkingNeuron wn : outputNeuronsList) {
                for (InputNeuron in : inputNeuronList) {
                    wn.makeConnection(new Connection(in, weights[index++]));
                }
            }

        } else {
            if (weights.length != hiddenNeuronList.size() * inputNeuronList.size() + hiddenNeuronList.size() * outputNeuronsList.size()) {
                throw new IllegalArgumentException("Ungültige Anzahl an gewichtungen übergeben!");
            }
            int index = 0;
            for (WorkingNeuron hidden : hiddenNeuronList) {
                for (InputNeuron in : inputNeuronList) {
                    hidden.makeConnection(new Connection(in, weights[index++]));
                }
            }

            for (WorkingNeuron output : outputNeuronsList) {
                for (WorkingNeuron hidden : hiddenNeuronList) {
                    output.makeConnection(new Connection(hidden, weights[index++]));
                }
            }
        }
    }

    public void changeAllActivationFunctions(ActivationFunction activationFunction) {
        for (WorkingNeuron w : outputNeuronsList) {
            w.changeActivationFunction(activationFunction);
        }
        for (WorkingNeuron w : hiddenNeuronList) {
            w.changeActivationFunction(activationFunction);
        }
    }

    public void changeOutputNeuronActivationFunctiom(ActivationFunction activationFunction) {
        for (WorkingNeuron w : outputNeuronsList) {
            w.changeActivationFunction(activationFunction);
        }
    }

    public void changeHiddenNeuronActivationFunction(ActivationFunction activationFunction) {
        for (WorkingNeuron w : hiddenNeuronList) {
            w.changeActivationFunction(activationFunction);
        }
    }


    public List<WorkingNeuron> getHiddenNeurons() {
        return this.hiddenNeuronList;
    }
}
