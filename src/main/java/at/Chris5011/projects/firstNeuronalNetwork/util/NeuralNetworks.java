package at.Chris5011.projects.firstNeuronalNetwork.util;

import java.util.Random;

public class NeuralNetworks {
    private static Random rd = new Random();

    public static double[] generateRandomArray(int i) {
        double[] d = new double[i];
        for (int j = 0; j < i; j++) {
            d[j] = rd.nextDouble()*rd.nextInt(150);
        }
        return d;
    }
    public static double[] generateRandomArray(int i, int cap, boolean allowNegatives) {
        double[] d = new double[i];
        for (int j = 0; j < i; j++) {
            d[j] = allowNegatives ? rd.nextBoolean()? -(rd.nextDouble()*rd.nextInt(cap)): rd.nextDouble()*rd.nextInt(cap): rd.nextDouble()*rd.nextInt(cap);
        }
        return d;
    }

}
