package at.Chris5011.projects.firstNeuronalNetwork.util.MNIST;

import at.Chris5011.projects.firstNeuronalNetwork.neurons.InputNeuron;
import at.Chris5011.projects.firstNeuronalNetwork.neurons.WorkingNeuron;
import at.Chris5011.projects.firstNeuronalNetwork.util.NeuralNetwork;

import java.io.IOException;
import java.util.List;
import java.util.Random;

public class MNIST_Learn {
    public static List<MNIST_Decoder.Digit> digits;
    public static List<MNIST_Decoder.Digit> digitsTest;

    public static NeuralNetwork nn = new NeuralNetwork();
    public static InputNeuron[][] inputNeurons = new InputNeuron[28][28];
    public static WorkingNeuron[] outputNeurons = new WorkingNeuron[10];

    private static Random rd = new Random();

    public static void main(String[] args) {

        try {
            digits = MNIST_Decoder.loadDataSet("src/main/resources/MNIST/train-images.idx3-ubyte",
                    "src/main/resources/MNIST/train-labels.idx1-ubyte");

            digitsTest = MNIST_Decoder.loadDataSet("src/main/resources/MNIST/t10k-images.idx3-ubyte",
                    "src/main/resources/MNIST/t10k-labels.idx1-ubyte");

            System.out.println(digits.size());
            System.out.println(digitsTest.size());


            for (int i = 0; i < inputNeurons.length; i++) {
                for (int j = 0; j < inputNeurons[i].length; j++) {
                    inputNeurons[i][j] = nn.createInputNeuron();
                }
            }
            for (int i = 0; i < outputNeurons.length; i++) {
                outputNeurons[i] = nn.createWorkingNeuron();
            }
            double weights[] = new double[inputNeurons.length * inputNeurons[0].length * outputNeurons.length];
            for (int i = 0; i < weights.length; i++) {
                weights[i] = rd.nextDouble();
            }

            nn.createFullMesh(weights);

            while(true){
//                nn.deltaLearning();
            }

        } catch (IOException ex) {
            System.out.println("Konnte den Angegebenen Pfad nicht finden: " + ex.getMessage());
        }
    }
}
