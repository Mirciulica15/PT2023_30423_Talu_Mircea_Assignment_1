package com.example.pt2023_talumircea_30433_assignment_1.model;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class RealPolynomial {
    private TreeMap<Integer, Double> terms;

    public RealPolynomial() {
        this.terms = new TreeMap<>();
    }

    public RealPolynomial(Map<Integer, Double> polTerms) {
        this.terms = new TreeMap<>(polTerms);
    }

    public void addTermReal(double coefficient, int exponent) {
        if (coefficient == 0) {
            return;
        }
        double currentCoefficient = terms.getOrDefault(exponent, 0.00);
        double newCoefficient = currentCoefficient + coefficient;
        if (newCoefficient == 0) {
            terms.remove(exponent);
        } else {
            terms.put(exponent, newCoefficient);
        }
    }

    public int getDegree() {
        return terms.isEmpty() ? 0 : terms.lastKey();
    }

    public double getLeadingCoefficient() {
        if (terms.isEmpty()) {
            return 0.0;
        }
        return terms.get(terms.lastKey());
    }

    public RealPolynomial add(RealPolynomial other) {
        Map<Integer, Double> addedTerms = new HashMap<>();
        for (Map.Entry<Integer, Double> term : this.terms.entrySet()) {
            int currentDegree = term.getKey();
            double currentCoefficient = term.getValue();
            addedTerms.put(currentDegree, currentCoefficient);
        }
        for (Map.Entry<Integer, Double> term : other.terms.entrySet()) {
            int currentDegree = term.getKey();
            double currentCoefficient = term.getValue();
            double currentSum = addedTerms.getOrDefault(currentDegree, 0.0) + currentCoefficient;
            addedTerms.put(currentDegree, currentSum);
        }
        return new RealPolynomial(addedTerms);
    }

    public RealPolynomial[] divide(RealPolynomial divisor) throws ArithmeticException {
        if (divisor.isZero()) {
            throw new ArithmeticException("Division by zero");
        }

        RealPolynomial quotient = new RealPolynomial();
        RealPolynomial remainder = new RealPolynomial(this.terms);

        int counter = 0;
        while (!remainder.isZero() && remainder.getDegree() >= divisor.getDegree() && counter < 100) {
            double quotientTerm = remainder.getLeadingCoefficient() / divisor.getLeadingCoefficient();
            int quotientDegree = remainder.getDegree() - divisor.getDegree();
            RealPolynomial currentTerm = new RealPolynomial();
            currentTerm.addTermReal(quotientTerm, quotientDegree);
            quotient = quotient.add(currentTerm);
            remainder = remainder.subtract(divisor.multiplyByMonomial(quotientDegree, quotientTerm));
            counter++;
        }

        RealPolynomial[] result = new RealPolynomial[2];
        result[0] = quotient;
        result[1] = remainder;
        return result;
    }

    public boolean isZero() {
        return this.terms.isEmpty();
    }

    public RealPolynomial multiplyByMonomial(int degree, double coefficient) {
        if (coefficient == 0) {
            Map<Integer, Double> mulTerms = new HashMap<>();
            return new RealPolynomial(mulTerms);
        }
        Map<Integer, Double> multipliedTerms = new HashMap<>();
        for (Map.Entry<Integer, Double> term : this.terms.entrySet()) {
            int currentDegree = term.getKey();
            double currentCoefficient = term.getValue();
            multipliedTerms.put(currentDegree + degree, currentCoefficient * coefficient);
        }
        return new RealPolynomial(multipliedTerms);
    }

    public RealPolynomial subtract(RealPolynomial other) {
        Map<Integer, Double> subtractedTerms = new HashMap<>();
        for (Map.Entry<Integer, Double> term : this.terms.entrySet()) {
            int currentDegree = term.getKey();
            double currentCoefficient = term.getValue();
            if (other.terms.containsKey(currentDegree)) {
                double otherCoefficient = other.terms.get(currentDegree);
                double diff = currentCoefficient - otherCoefficient;
                if (Math.abs(diff) < 1e-7) {
                    subtractedTerms.put(currentDegree, 0.0);
                } else {
                    subtractedTerms.put(currentDegree, diff);
                }
            } else {
                subtractedTerms.put(currentDegree, currentCoefficient);
            }
        }
        for (Map.Entry<Integer, Double> term : other.terms.entrySet()) {
            int currentDegree = term.getKey();
            double currentCoefficient = term.getValue();
            if (!this.terms.containsKey(currentDegree)) {
                subtractedTerms.put(currentDegree, -currentCoefficient);
            }
        }
        return new RealPolynomial(subtractedTerms);
    }

    public void integrate() {
        TreeMap<Integer, Double> integratedTerms = new TreeMap<>();
        for (Map.Entry<Integer, Double> term : this.terms.entrySet()) {
            int exp = term.getKey();
            double coeff = term.getValue();
            integratedTerms.put(exp + 1, coeff / (exp + 1));
        }
        this.terms = integratedTerms;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, Double> term : terms.entrySet()) {
            int exp = term.getKey();
            double coeff = term.getValue();
            if (sb.length() > 0 && coeff > 0) {
                sb.append("+");
            }
            if (coeff != 1 || exp == 0) {
                sb.append(coeff);
            }
            if (exp > 0) {
                sb.append("x");
            }
            if (exp > 1) {
                sb.append("^").append(exp);
            }
        }
        return sb.toString();
    }

}
