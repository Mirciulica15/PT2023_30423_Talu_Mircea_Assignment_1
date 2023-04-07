package com.example.pt2023_talumircea_30433_assignment_1.controller;

import com.example.pt2023_talumircea_30433_assignment_1.model.Polynomial;
import com.example.pt2023_talumircea_30433_assignment_1.model.RealPolynomial;

import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller {

    public String addition(String polynomialFirst, String polynomialSecond) {

        Polynomial P1 = parsePolynomial(polynomialFirst);
        Polynomial P2 = parsePolynomial(polynomialSecond);
        Polynomial resPol = P1.addPolynomials(P2);
        return resPol.toString();
    }

    public String subtraction(String polynomialFirst, String polynomialSecond) {

        Polynomial P1 = parsePolynomial(polynomialFirst);
        Polynomial P2 = parsePolynomial(polynomialSecond);
        Polynomial resPol = P1.subtractPolynomials(P2);
        return resPol.toString();
    }

    public String multiplication(String polynomialFirst, String polynomialSecond) {

        Polynomial P1 = parsePolynomial(polynomialFirst);
        Polynomial P2 = parsePolynomial(polynomialSecond);
        Polynomial resPol = P1.multiplyPolynomials(P2);
        return resPol.toString();
    }

    public String division(String polynomialFirst, String polynomialSecond) {

        RealPolynomial P1 = parseRealPolynomial(polynomialFirst);
        RealPolynomial P2 = parseRealPolynomial(polynomialSecond);
        RealPolynomial[] resPol = P1.divide(P2);
        System.out.println("Remainder : " + resPol[1].toString());
        return resPol[0].toString();
    }

    public String derivation(String polynomialFirst) {

        Polynomial P = parsePolynomial(polynomialFirst);
        P.findDerivative();
        P.checkPolynomial();
        return P.toString();
    }

    public String integration(String polynomialFirst) {

        RealPolynomial P = parseRealPolynomial(polynomialFirst);
        P.integrate();
        return P.toString();
    }

    private Polynomial parsePolynomial(String polynomialString) {
        Polynomial P = new Polynomial(new TreeMap<>());
        String monomial = "([+-]?[\\d.]*[a-zA-Z]?\\^?\\d*)";
        String monomialParts = "([+-]?[\\d]*)([a-zA-Z]?)\\^?(\\d*)";

        Pattern pattern = Pattern.compile(monomial);
        Matcher matcher = pattern.matcher(polynomialString);

        while(!matcher.hitEnd()) {
            matcher.find();
            Pattern pattern1 = Pattern.compile(monomialParts);
            Matcher matcher1 = pattern1.matcher(matcher.group());

            if(matcher1.find()) {
                P.addTerm(Integer.parseInt(matcher1.group(1)), Integer.parseInt(matcher1.group(3)));
            }
        }

        return P;
    }


    private RealPolynomial parseRealPolynomial(String polynomialString) {
        RealPolynomial P = new RealPolynomial(new TreeMap<>());
        String monomial = "([+-]?[\\d.]*[a-zA-Z]?\\^?\\d*)";
        String monomialParts = "([+-]?[\\d]*)([a-zA-Z]?)\\^?(\\d*)";

        Pattern pattern = Pattern.compile(monomial);
        Matcher matcher = pattern.matcher(polynomialString);

        while(!matcher.hitEnd()) {
            matcher.find();
            Pattern pattern1 = Pattern.compile(monomialParts);
            Matcher matcher1 = pattern1.matcher(matcher.group());

            if(matcher1.find()) {
                P.addTermReal(Double.parseDouble(matcher1.group(1)), Integer.parseInt(matcher1.group(3)));
            }
        }

        return P;
    }
}
