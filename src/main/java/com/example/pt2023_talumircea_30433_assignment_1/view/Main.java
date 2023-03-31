package com.example.pt2023_talumircea_30433_assignment_1.view;

import com.example.pt2023_talumircea_30433_assignment_1.controller.Controller;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application{

    Button buttonAddition;
    Button buttonSubtraction;
    Button buttonMultiplication;
    Button buttonDivision;
    Button buttonDerivative;
    Button buttonPrimitive;
    TextField p1;
    TextField p2;
    TextField res;
    TextField polynomial1;
    TextField polynomial2;
    TextField result;

    public static void main(String[] args) {

        launch(args);

    }

    @Override
    public void start(Stage primaryStage) {

        Controller controller = new Controller();
        primaryStage.setTitle("Polynomial Calculator");

        buttonAddition = new Button();
        buttonAddition.setText("Addition");

        buttonAddition.setOnAction((event) -> result.setText(controller.addition(polynomial1.getText(), polynomial2.getText())));

        buttonSubtraction = new Button();
        buttonSubtraction.setText("Subtraction");

        buttonSubtraction.setOnAction((event) -> result.setText(controller.subtraction(polynomial1.getText(), polynomial2.getText())));

        buttonMultiplication = new Button();
        buttonMultiplication.setText("Multiplication");

        buttonMultiplication.setOnAction((event) -> result.setText(controller.multiplication(polynomial1.getText(), polynomial2.getText())));

        buttonDivision = new Button();
        buttonDivision.setText("Division");

        buttonDivision.setOnAction((event) -> result.setText(controller.division(polynomial1.getText(), polynomial2.getText())));

        buttonDerivative = new Button();
        buttonDerivative.setText("Derivative");

        buttonDerivative.setOnAction((event) -> result.setText(controller.derivation(polynomial1.getText())));

        buttonPrimitive = new Button();
        buttonPrimitive.setText("Primitive");

        buttonPrimitive.setOnAction((event) -> result.setText(controller.integration(polynomial1.getText())));

        p1 = new TextField();
        p1.setText("Polynomial 1:");
        p1.setEditable(false);

        p2 = new TextField();
        p2.setText("Polynomial 2:");
        p2.setEditable(false);

        res = new TextField();
        res.setText("Result:");
        res.setEditable(false);

        polynomial1 = new TextField();
        polynomial1.setMinSize(300, 10);

        polynomial2 = new TextField();
        polynomial2.setMinSize(300, 10);

        result = new TextField();
        result.setMinSize(300, 10);

        GridPane layout = new GridPane();
        layout.setHgap(10);
        layout.setVgap(10);
        layout.setPadding(new Insets(0, 10, 0, 10));
        layout.add(buttonAddition,2,0);
        layout.add(buttonSubtraction, 2, 1);
        layout.add(buttonMultiplication, 3, 0);
        layout.add(buttonDivision, 3, 1);
        layout.add(buttonDerivative, 4, 0);
        layout.add(buttonPrimitive, 4, 1);
        layout.add(p1, 0, 0);
        layout.add(p2, 0, 1);
        layout.add(res, 0, 2);
        layout.add(polynomial1, 1, 0);
        layout.add(polynomial2, 1, 1);
        layout.add(result, 1, 2);

        Scene scene = new Scene(layout, 900, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
