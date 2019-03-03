package at.Chris5011.projects.firstNeuronalNetwork;

import at.Chris5011.projects.firstNeuronalNetwork.activationFunctions.ActivationFunction;
import at.Chris5011.projects.firstNeuronalNetwork.neurons.InputNeuron;
import at.Chris5011.projects.firstNeuronalNetwork.neurons.WorkingNeuron;
import at.Chris5011.projects.firstNeuronalNetwork.util.NeuralNetwork;
import at.Chris5011.projects.firstNeuronalNetwork.util.NeuralNetworks;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {

        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("view/NeuralView.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Neural Network view");
        primaryStage.show();

    }
}

class Main_Logical {
    public static void main(String[] args) {


        NeuralNetwork nn = new NeuralNetwork();

        InputNeuron in1 = nn.createInputNeuron();
        InputNeuron in2 = nn.createInputNeuron();
        InputNeuron in3 = nn.createInputNeuron();
        InputNeuron in4 = nn.createInputNeuron();

        nn.createHiddenNeuron(0);

        in1.setValue(10);
        in2.setValue(20);
        in3.setValue(30);
        in4.setValue(40);

        WorkingNeuron outputNeuron = nn.createWorkingNeuron();

        double connectionArray[] = {
                1, 2, 3,     //Gewichtung IP 1
                4, //5, 6,     //Gewichtung IP 2
//                7, 8, 9,     //Gewichtung IP 3
//                10, 11, 12,    //Gewichtung IP 4

//                1, 1, 1         //Gewichtung Hidden -> Output 1,2,3
        };

        double randomConnectionArray[] = NeuralNetworks.generateRandomArray(15, 10, true);

        nn.createFullMesh(connectionArray);
        System.out.println("identityActivation: ");
        nn.changeAllActivationFunctions(ActivationFunction.identityActivation);
//        nn.changeHiddenNeuronActivationFunction(ActivationFunction.sigmoidActivation);
        System.out.println(outputNeuron.getValue() + "\n");

        System.out.println("booleanActivation: ");
        nn.changeAllActivationFunctions(ActivationFunction.booleanActivation);
        System.out.println(outputNeuron.getValue() + "\n");

        System.out.println("sigmoidActivation: ");
        nn.changeAllActivationFunctions(ActivationFunction.sigmoidActivation);
        System.out.println(outputNeuron.getValue() + "\n");

        System.out.println("activationTangensHyperbolicus: ");
        nn.changeAllActivationFunctions(ActivationFunction.activationTangensHyperbolicus);
        System.out.println(outputNeuron.getValue() + "\n");

        System.out.println("activationReLu: ");
        nn.changeAllActivationFunctions(ActivationFunction.activationReLu);
        System.out.println(outputNeuron.getValue());
    }
}
