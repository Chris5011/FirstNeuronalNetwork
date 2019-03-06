package at.Chris5011.projects.firstNeuronalNetwork;


import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

public class MNIST_Encoder {

    public static void main(String[] args) throws IOException {

        InputStream is = MNIST_Encoder.class.getResourceAsStream("/MNIST/self/8.png");
        byte[] b = IOUtils.toByteArray(is);


        for (int i = 0; i < 9; i++) {
            for (byte byt: b) {
                System.out.print(byt + " ");
            }
            System.out.println();
        }

    }

}
