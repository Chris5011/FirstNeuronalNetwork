package at.Chris5011.projects.firstNeuronalNetwork.view;

import at.Chris5011.projects.firstNeuronalNetwork.activationFunctions.ActivationFunction;
import at.Chris5011.projects.firstNeuronalNetwork.neurons.InputNeuron;
import at.Chris5011.projects.firstNeuronalNetwork.neurons.WorkingNeuron;
import at.Chris5011.projects.firstNeuronalNetwork.util.NeuralNetwork;
import at.Chris5011.projects.firstNeuronalNetwork.util.NeuralNetworks;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class NeuralViewController {

    @FXML
    private AnchorPane neuronPane;

    @FXML
    private ComboBox<ActivationFunction> comboAlgorithm;

    @FXML
    private Label lblErg;

    private NeuralNetwork nn;
    private List<InputNeuron> inputNeuronList = new ArrayList<>();
    private List<TextField> inputTextFieldList = new ArrayList<>();
    private List<WorkingNeuron> hiddenNeuronList;
    private WorkingNeuron outputNeuron;
    private ObservableList<ActivationFunction> observableList;

    private int yOffset = 100;
    private int xOffset = 260;

    public void initialize() {
        observableList = comboAlgorithm.getItems();
        observableList.add(ActivationFunction.identityActivation);
        observableList.add(ActivationFunction.booleanActivation);
        observableList.add(ActivationFunction.sigmoidActivation);
        observableList.add(ActivationFunction.activationTangensHyperbolicus);
        observableList.add(ActivationFunction.activationReLu);
        comboAlgorithm.getSelectionModel().select(0);

        createNeuralNetwork();
        renderNeuralNetwork();

    }

    private void renderNeuralNetwork() {

        Platform.runLater(() -> {
            int posY = 0;
            int posX = 0;
            List<Circle> inputNeuronCircle = new ArrayList<>();
            List<Circle> hiddenNeuronCircle = new ArrayList<>();
            posY = (int) neuronPane.getHeight() / inputNeuronList.size();
            posX = (int) neuronPane.getWidth() / 6;
            for (InputNeuron in : inputNeuronList) {
                Circle c = new Circle(25);
                TextField tf = new TextField();
                tf.setMaxWidth(30);
                tf.setMinWidth(30);
                tf.setLayoutX(posX - 20);
                tf.setLayoutY(posY - 15);
                inputTextFieldList.add(tf);
                Circle border = new Circle(26);
                c.setCenterX(posX + 50);
                c.setCenterY(posY);
                border.setCenterX(posX + 50);
                border.setCenterY(posY);
                c.setFill(Color.WHITE);
                c.setStyle("-fx-border-color: #000");
                neuronPane.getChildren().add(border);
                neuronPane.getChildren().add(c);
                neuronPane.getChildren().add(tf);
                inputNeuronCircle.add(c);
//                posY += 70;
                posY += yOffset;
            }

            posX += xOffset;
            posY = (int) neuronPane.getHeight() / hiddenNeuronList.size();

            for (WorkingNeuron hidden : hiddenNeuronList) {
                Circle c = new Circle(25);
                Circle border = new Circle(26);
                for (Circle circle : inputNeuronCircle) {
                    neuronPane.getChildren().add(new Line(circle.getCenterX(), circle.getCenterY(), posX, posY));
                }
                c.setCenterX(posX);
                c.setCenterY(posY);
                border.setCenterX(posX);
                border.setCenterY(posY);
                c.setFill(Color.WHITE);
                c.setStyle("-fx-border-color: #000");
                neuronPane.getChildren().add(border);
                neuronPane.getChildren().add(c);
                hiddenNeuronCircle.add(c);
//                posY += 70;
                posY += yOffset;
            }

            posX += xOffset;
            posY = (int) (neuronPane.getHeight() / 2);
            int circleCounter = 0;
            for (Circle circle : hiddenNeuronCircle) {
                Line l = new Line(circle.getCenterX() + 26, circle.getCenterY(), posX + 50, posY);
                neuronPane.getChildren().add(l);
                circleCounter++;
            }

            Circle c = new Circle(25);
            Circle border = new Circle(26);
            c.setCenterX(posX + 50);
            c.setCenterY(posY);
            border.setCenterX(posX + 50);
            border.setCenterY(posY);
            c.setFill(Color.WHITE);
            c.setStyle("-fx-border-color: #000");
            neuronPane.getChildren().add(border);
            neuronPane.getChildren().add(c);
        });

    }

    private void createNeuralNetwork() {

        nn = new NeuralNetwork();

        InputNeuron in1 = nn.createInputNeuron();
        InputNeuron in2 = nn.createInputNeuron();
        InputNeuron in3 = nn.createInputNeuron();
        InputNeuron in4 = nn.createInputNeuron();

        inputNeuronList.add(in1);
        inputNeuronList.add(in2);
        inputNeuronList.add(in3);
        inputNeuronList.add(in4);

        nn.createHiddenNeuron(3);

        hiddenNeuronList = nn.getHiddenNeurons();

        outputNeuron = nn.createWorkingNeuron();

        double connectionArray[] = {
                1, 2, 3,     //Gewichtung IP 1
                4, 5, 6,     //Gewichtung IP 2
                7, 8, 9,     //Gewichtung IP 3
                10, 11, 12,    //Gewichtung IP 4

                1, 1, 1         //Gewichtung Hidden -> Output 1,2,3
        };

//        double connectionArray[] = {
//                10, 0, 0, 1,    //Gewichtung IP 1
//                0, 0, 0,  1,   //Gewichtung IP 2
//                0, 0, 0,  1,   //Gewichtung IP 3
//                0, 0, 0,  1,  //Gewichtung IP 4
//
//                10, 0, 0,1         //Gewichtung Hidden -> Output 1,2,3
//        };

        double randomConnectionArray[] = NeuralNetworks.generateRandomArray(15, 10, true);

        nn.createFullMesh(connectionArray);
//        nn.createFullMesh(randomConnectionArray);

//        in1.setValue(-1);
//        in2.setValue(2);
//        in3.setValue(3);
//        in4.setValue(4);

//        System.out.println("identityActivation: ");
//        nn.changeAllActivationFunctions(ActivationFunction.identityActivation);
////        nn.changeHiddenNeuronActivationFunction(ActivationFunction.sigmoidActivation);
//        System.out.println(wn.getValue());
//        System.out.println("booleanActivation: ");
//        nn.changeAllActivationFunctions(ActivationFunction.booleanActivation);
//        System.out.println(wn.getValue());
//        System.out.println("sigmoidActivation: ");
//        nn.changeAllActivationFunctions(ActivationFunction.sigmoidActivation);
//        System.out.println(wn.getValue());
//        System.out.println("activationTangensHyperbolicus: ");
//        nn.changeAllActivationFunctions(ActivationFunction.activationTangensHyperbolicus);
//        System.out.println(wn.getValue());
//        System.out.println("activationReLu: ");
//        nn.changeAllActivationFunctions(ActivationFunction.activationReLu);
//        System.out.println(wn.getValue());

    }


    public void calculate(ActionEvent actionEvent) {

        setInputNeuronValues();
        lblErg.setText("" + outputNeuron.getValue());
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

    private void setInputNeuronValues() {
        int index = 0;
        boolean exception = false;
        for (TextField tf : inputTextFieldList) {
            try {
                double val = new Double(tf.getText()).doubleValue();
                inputNeuronList.get(index).setValue(val);
            } catch (NumberFormatException ex) {
                if (!exception) {
                    Alert al = new Alert(Alert.AlertType.ERROR, "Fehler bei der Eingabe!\nBitte nur Zahlen eingeben!");
                    al.showAndWait();
                }
                exception = true;
            }
        }

    }

    public NeuralNetwork getNn() {
        return nn;
    }

    public void setNn(NeuralNetwork nn) {
        this.nn = nn;
    }

    public List<InputNeuron> getInputNeuronList() {
        return inputNeuronList;
    }

    public void setInputNeuronList(List<InputNeuron> inputNeuronList) {
        this.inputNeuronList = inputNeuronList;
    }

    public List<WorkingNeuron> getHiddenNeuronList() {
        return hiddenNeuronList;
    }

    public void setHiddenNeuronList(List<WorkingNeuron> hiddenNeuronList) {
        this.hiddenNeuronList = hiddenNeuronList;
    }

    public WorkingNeuron getOutputNeuron() {
        return outputNeuron;
    }

    public void setOutputNeuron(WorkingNeuron outputNeuron) {
        this.outputNeuron = outputNeuron;
    }

    public void changeActivationFunction(ActionEvent event) {
        ActivationFunction af = comboAlgorithm.getSelectionModel().getSelectedItem();
        this.nn.changeAllActivationFunctions(af);
        System.out.println("Changed algorithm to: " + af);
    }
}
