package com.example.pt2023_talumircea_30433_assignment_1.View;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.pt2023_talumircea_30433_assignment_1.Model.Polynomial;
import com.example.pt2023_talumircea_30433_assignment_1.Model.RealPolynomial;
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
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Polynomial Calculator");

        buttonAddition = new Button();
        buttonAddition.setText("Addition");

        buttonAddition.setOnAction((event) -> {

            Polynomial P1 = new Polynomial(new TreeMap<>());
            Polynomial P2 = new Polynomial(new TreeMap<>());

            System.out.println(res.toString());

            String polynomialFirst = polynomial1.getText();
            String polynomialSecond = polynomial2.getText();

            String monomial = "([+-]?[\\d\\.]*[a-zA-Z]?\\^?\\d*)";
            String monomialParts = "([+-]?[\\d]*)([a-zA-Z]?)\\^?(\\d*)";

            Pattern pattern = Pattern.compile(monomial);
            Matcher matcher = pattern.matcher(polynomialFirst);

            while(!matcher.hitEnd()) {
                matcher.find();
                Pattern pattern1 = Pattern.compile(monomialParts);
                Matcher matcher1 = pattern1.matcher(matcher.group());

                if(matcher1.find()) {
                    P1.addTerm(Integer.parseInt(matcher1.group(1)), Integer.parseInt(matcher1.group(3)));
                }
            }

            Matcher matcherSecond = pattern.matcher(polynomialSecond);
            while(!matcherSecond.hitEnd()) {
                matcherSecond.find();
                Pattern pattern1 = Pattern.compile(monomialParts);
                Matcher matcher1 = pattern1.matcher(matcherSecond.group());
                if (matcher1.find()) {
                    P2.addTerm(Integer.parseInt(matcher1.group(1)), Integer.parseInt(matcher1.group(3)));
                }
            }
            Polynomial resPol = P1.addPolynomials(P2);
            System.out.println(resPol);
            String resultString = resPol.toString();
            result.setText(resultString);
        });

        buttonSubtraction = new Button();
        buttonSubtraction.setText("Subtraction");

        buttonSubtraction.setOnAction((event) -> {
            Map<Integer, Integer> p1Map = new HashMap<>();
            Polynomial P1 = new Polynomial(p1Map);

            Map<Integer, Integer> p2Map = new HashMap<>();
            Polynomial P2 = new Polynomial(p2Map);

            String polynomialFirst = polynomial1.getText();
            String polynomialSecond = polynomial2.getText();

            String monomial = "([+-]?[\\d\\.]*[a-zA-Z]?\\^?\\d*)";
            String monomialParts = "([+-]?[\\d]*)([a-zA-Z]?)\\^?(\\d*)";

            Pattern pattern = Pattern.compile(monomial);
            Matcher matcher = pattern.matcher(polynomialFirst);

            while(!matcher.hitEnd()) {
                matcher.find();
                Pattern pattern1 = Pattern.compile(monomialParts);
                System.out.print(matcher.group() + " -> ");
                Matcher matcher1 = pattern1.matcher(matcher.group());

                if(matcher1.find()) {
                    P1.addTerm(Integer.parseInt(matcher1.group(1)), Integer.parseInt(matcher1.group(3)));
                }
            }

            Matcher matcherSecond = pattern.matcher(polynomialSecond);
            while(!matcherSecond.hitEnd()) {
                matcherSecond.find();
                Pattern pattern1 = Pattern.compile(monomialParts);
                Matcher matcher1 = pattern1.matcher(matcherSecond.group());
                if(matcher1.find()) {
                    P2.addTerm(Integer.parseInt(matcher1.group(1)), Integer.parseInt(matcher1.group(3)));
                }
            }

            Polynomial resPol = P1.subtractPolynomials(P2);
            resPol.checkPolynomial();
            String resultString = resPol.toString();
            result.setText(resultString);
        });

        buttonMultiplication = new Button();
        buttonMultiplication.setText("Multiplication");

        buttonMultiplication.setOnAction((event) -> {
            Map<Integer, Integer> p1Map = new HashMap<>();
            Polynomial P1 = new Polynomial(p1Map);

            Map<Integer, Integer> p2Map = new HashMap<>();
            Polynomial P2 = new Polynomial(p2Map);

            String polynomialFirst = polynomial1.getText();
            String polynomialSecond = polynomial2.getText();

            String monomial = "([+-]?[\\d\\.]*[a-zA-Z]?\\^?\\d*)";
            String monomialParts = "([+-]?[\\d]*)([a-zA-Z]?)\\^?(\\d*)";

            Pattern pattern = Pattern.compile(monomial);
            Matcher matcher = pattern.matcher(polynomialFirst);

            while(!matcher.hitEnd()) {
                matcher.find();
                Pattern pattern1 = Pattern.compile(monomialParts);
                Matcher matcher1 = pattern1.matcher(matcher.group());

                if(matcher1.find()) {
                    P1.addTerm(Integer.parseInt(matcher1.group(1)), Integer.parseInt(matcher1.group(3)));
                }
            }

            Matcher matcherSecond = pattern.matcher(polynomialSecond);
            while(!matcherSecond.hitEnd()) {
                matcherSecond.find();
                Pattern pattern1 = Pattern.compile(monomialParts);
                Matcher matcher1 = pattern1.matcher(matcherSecond.group());
                if(matcher1.find()) {
                    P2.addTerm(Integer.parseInt(matcher1.group(1)), Integer.parseInt(matcher1.group(3)));
                }
            }

            Polynomial resPol = P1.multiplyPolynomials(P2);
            resPol.checkPolynomial();
            String resultString = resPol.toString();
            result.setText(resultString);
        });

        buttonDivision = new Button();
        buttonDivision.setText("Division");

        buttonDivision.setOnAction((event) -> {
            Map<Integer, Double> p1Map = new HashMap<>();
            RealPolynomial P1 = new RealPolynomial(p1Map);

            Map<Integer, Double> p2Map = new HashMap<>();
            RealPolynomial P2 = new RealPolynomial(p2Map);

            String polynomialFirst = polynomial1.getText();
            String polynomialSecond = polynomial2.getText();

            String monomial = "([+-]?[\\d\\.]*[a-zA-Z]?\\^?\\d*)";
            String monomialParts = "([+-]?[\\d]*)([a-zA-Z]?)\\^?(\\d*)";

            Pattern pattern = Pattern.compile(monomial);
            Matcher matcher = pattern.matcher(polynomialFirst);

            while(!matcher.hitEnd()) {
                matcher.find();
                Pattern pattern1 = Pattern.compile(monomialParts);
                Matcher matcher1 = pattern1.matcher(matcher.group());

                if (matcher1.find()) {
                    P1.addTermReal(Double.parseDouble(matcher1.group(1)), Integer.parseInt(matcher1.group(3)));
                }
            }

            Matcher matcherSecond = pattern.matcher(polynomialSecond);
            while(!matcherSecond.hitEnd()) {
                matcherSecond.find();
                Pattern pattern1 = Pattern.compile(monomialParts);
                Matcher matcher1 = pattern1.matcher(matcherSecond.group());
                if(matcher1.find()) {
                    P2.addTermReal(Double.parseDouble(matcher1.group(1)), Integer.parseInt(matcher1.group(3)));
                }
            }

            RealPolynomial resPol = P1.divide(P2);
            String resultString = resPol.toString();
            result.setText(resultString);
        });

        buttonDerivative = new Button();
        buttonDerivative.setText("Derivative");

        buttonDerivative.setOnAction((event) -> {
            Map<Integer, Integer> p1Map = new HashMap<>();
            Polynomial P1 = new Polynomial(p1Map);

            String polynomialFirst = polynomial1.getText();

            String monomial = "([+-]?[\\d\\.]*[a-zA-Z]?\\^?\\d*)";
            String monomialParts = "([+-]?[\\d]*)([a-zA-Z]?)\\^?(\\d*)";

            Pattern pattern = Pattern.compile(monomial);
            Matcher matcher = pattern.matcher(polynomialFirst);

            while(!matcher.hitEnd()) {
                matcher.find();
                Pattern pattern1 = Pattern.compile(monomialParts);
                Matcher matcher1 = pattern1.matcher(matcher.group());

                if(matcher1.find()) {
                    P1.addTerm(Integer.parseInt(matcher1.group(1)), Integer.parseInt(matcher1.group(3)));
                }
            }

            P1.findDerivative();
            P1.checkPolynomial();
            String resultString = P1.toString();
            result.setText(resultString);
        });

        buttonPrimitive = new Button();
        buttonPrimitive.setText("Primitive");

        buttonPrimitive.setOnAction((event) -> {
            Map<Integer, Double> p1Map = new HashMap<>();
            RealPolynomial P1 = new RealPolynomial(p1Map);

            String polynomialFirst = polynomial1.getText();

            String monomial = "([+-]?[\\d\\.]*[a-zA-Z]?\\^?\\d*)";
            String monomialParts = "([+-]?[\\d]*)([a-zA-Z]?)\\^?(\\d*)";

            Pattern pattern = Pattern.compile(monomial);
            Matcher matcher = pattern.matcher(polynomialFirst);

            while(!matcher.hitEnd()) {
                matcher.find();
                Pattern pattern1 = Pattern.compile(monomialParts);
                Matcher matcher1 = pattern1.matcher(matcher.group());

                if(matcher1.find()) {
                    P1.addTermReal(Double.parseDouble(matcher1.group(1)), Integer.parseInt(matcher1.group(3)));
                }
            }

            RealPolynomial resultPol = P1.integrate();
            String resultString = resultPol.toString();
            result.setText(resultString);
        });

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
