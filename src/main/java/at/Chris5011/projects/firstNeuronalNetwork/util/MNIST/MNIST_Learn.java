package at.Chris5011.projects.firstNeuronalNetwork.util.MNIST;

import at.Chris5011.projects.firstNeuronalNetwork.neurons.InputNeuron;
import at.Chris5011.projects.firstNeuronalNetwork.neurons.WorkingNeuron;
import at.Chris5011.projects.firstNeuronalNetwork.util.NeuralNetwork;

import java.io.IOException;
import java.util.*;

public class MNIST_Learn {
    public static List<MNIST_Decoder.Digit> digits;
    public static List<MNIST_Decoder.Digit> digitsTest;

    public static NeuralNetwork nn = new NeuralNetwork();
    public static InputNeuron[][] inputNeurons = new InputNeuron[28][28];
    public static int arraySize = 28;
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

//            InputNeuron bias = nn.createInputNeuron();
//            bias.setValue(1);

            for (int i = 0; i < inputNeurons.length; i++) {
                for (int j = 0; j < inputNeurons[i].length; j++) {
                    inputNeurons[i][j] = nn.createInputNeuron();
                }
            }
            for (int i = 0; i < outputNeurons.length; i++) {
                outputNeurons[i] = nn.createWorkingNeuron();
            }
//            double weights[] = new double[inputNeurons.length * inputNeurons[0].length * outputNeurons.length + 10];
            double weights[] = new double[inputNeurons.length * inputNeurons[0].length * outputNeurons.length];
            for (int i = 0; i < weights.length; i++) {
                weights[i] = rd.nextDouble();
            }

            nn.createFullMesh(weights);

            double epsilon = 0.009;
            while (true) {
                test();
                for (int i = 0; i < digits.size(); i++) {
                    for (int j = 0; j < arraySize; j++) {
                        for (int k = 0; k < arraySize; k++) {
                            inputNeurons[j][k].setValue(MNIST_Decoder.toUnsignedByte(digits.get(i).data[j][k]) / 255.0);
                        }
                    }
                    double expected[] = new double[10];
                    expected[digits.get(i).label] = 1;
                    nn.deltaLearning(expected, epsilon);
                }
                epsilon *= 0.9;
            }

        } catch (IOException ex) {
            System.out.println("Konnte den Angegebenen Pfad nicht finden: " + ex.getMessage());
        }

    }

    public static void test() {
        int correct = 0;
        int incorrect = 0;

        for (int i = 0; i < digitsTest.size(); i++) {
            for (int x = 0; x < arraySize; x++) {
                for (int y = 0; y < arraySize; y++) {
                    inputNeurons[x][y].setValue(MNIST_Decoder.toUnsignedByte(digitsTest.get(i).data[x][y]) / 255.0);
                }
            }
            ProbabilityDigit[] probs = new ProbabilityDigit[10];
            for (int j = 0; j < probs.length; j++) {
                probs[j] = new ProbabilityDigit(j, outputNeurons[j].getValue());
            }

            Arrays.sort(probs, Collections.reverseOrder());

            boolean wasCorrect = false;
            for (int j = 0; j < 1; j++) {
                if (digitsTest.get(i).label == probs[j].DIGIT) {
                    wasCorrect = true;
                }
            }

//            if (digitsTest.get(i).label == probs[0].DIGIT) {
            if (wasCorrect) {
                correct++;
            } else {
                incorrect++;
            }
        }

        double percentage = (double) correct / (double) (correct + incorrect);
        System.out.println(percentage);

    }

    public static class ProbabilityDigit implements Comparable<ProbabilityDigit> {
        public final int DIGIT;
        public double probability;

        public ProbabilityDigit(int digit, double probability) {
            this.DIGIT = digit;
            this.probability = probability;
        }

        @Override
        public int compareTo(ProbabilityDigit o) {
            return this.probability == o.probability ? 0 : (this.probability > o.probability ? 1 : -1);
        }
    }
}
