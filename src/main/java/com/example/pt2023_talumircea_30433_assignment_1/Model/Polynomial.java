package com.example.pt2023_talumircea_30433_assignment_1.Model;

import java.util.*;

public class Polynomial {

    private Map<Integer, Integer> terms;

    public Polynomial(Map<Integer, Integer> p1Map) {
        this.terms = new TreeMap<>(p1Map);
    }

    public void addTerm(int coefficient, int exponent) {
        if (coefficient == 0) {
            return;
        }
        int currentCoefficient = terms.getOrDefault(exponent, 0);
        int newCoefficient = currentCoefficient + coefficient;
        if (newCoefficient == 0) {
            terms.remove(exponent);
        } else {
            terms.put(exponent, newCoefficient);
        }
    }

    public Polynomial addPolynomials(Polynomial other) {
        Map<Integer, Integer> newMap = new HashMap<>();
        for (Map.Entry<Integer, Integer> entry : this.terms.entrySet()) {
            newMap.merge(entry.getKey(), entry.getValue(), Integer::sum);
        }
        for (Map.Entry<Integer, Integer> entry : other.terms.entrySet()) {
            newMap.merge(entry.getKey(), entry.getValue(), Integer::sum);
        }
        return new Polynomial(newMap);
    }

    public Polynomial subtractPolynomials(Polynomial other) {
        Map<Integer, Integer> newMap = new HashMap<>(terms);
        for (Map.Entry<Integer, Integer> entry : other.terms.entrySet()) {
            int exp = entry.getKey();
            int coeff = -entry.getValue(); // negate the coefficient
            newMap.merge(exp, coeff, Integer::sum);
        }
        return new Polynomial(newMap);
    }

    public Polynomial multiplyPolynomials(Polynomial other) {
        Map<Integer, Integer> newMap = new HashMap<>();

        for (Map.Entry<Integer, Integer> term1 : terms.entrySet()) {
            for (Map.Entry<Integer, Integer> term2 : other.terms.entrySet()) {
                int exp = term1.getKey() + term2.getKey();
                int coeff = term1.getValue() * term2.getValue();
                newMap.merge(exp, coeff, Integer::sum);
            }
        }

        return new Polynomial(newMap);
    }

    public void findDerivative() {
        Map<Integer, Integer> result = new TreeMap<>();
        for (Map.Entry<Integer, Integer> term : terms.entrySet()) {
            int exponent = term.getKey();
            int coefficient = term.getValue() * exponent;
            if (exponent > 0) {
                result.put(exponent - 1, coefficient);
            }
        }
        this.terms = result;
    }

    public void checkPolynomial() {
        terms.entrySet().removeIf(entry -> entry.getValue() == 0);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, Integer> term : terms.entrySet()) {
            int exponent = term.getKey();
            int coefficient = term.getValue();
            if (coefficient > 0 && sb.length() > 0) {
                sb.append("+");
            }
            if (exponent == 0) {
                sb.append(coefficient);
            } else if (coefficient == 1) {
                sb.append("x");
                if (exponent > 1) {
                    sb.append("^").append(exponent);
                }
            } else if (exponent == 1) {
                sb.append(coefficient).append("x");
            } else {
                sb.append(coefficient).append("x^").append(exponent);
            }
        }
        if (sb.length() == 0) {
            sb.append("0");
        }
        return sb.toString();
    }
}

