package com.example.pt2023_talumircea_30433_assignment_1.Model;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class RealPolynomial {
    private final TreeMap<Integer, Double> terms;

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


    public RealPolynomial multiplyByConstant(double c) {
        TreeMap<Integer, Double> newTerms = new TreeMap<>();
        for (Map.Entry<Integer, Double> term : terms.entrySet()) {
            newTerms.put(term.getKey(), term.getValue() * c);
        }
        return new RealPolynomial(newTerms);
    }

    public RealPolynomial divide(RealPolynomial divisor) {
        if (this.isZero() || divisor.isZero()) {
            // If one polynomial is zero, return zero polynomial.
            return new RealPolynomial();
        }
        System.out.println(this);
        System.out.println(divisor);
        Map<Integer, Double> quotientTerms = new HashMap<>();
        RealPolynomial quotient = new RealPolynomial(quotientTerms);
        RealPolynomial remainder = this;

        int divisorDegree = divisor.getDegree();

        if (divisorDegree > remainder.getDegree()) {
            // The divisor is of a higher degree than the partial remainder,
            // so the division cannot continue. Return the current quotient and remainder.
            return quotient;
        }

        double divisorLeadingCoefficient = divisor.getLeadingCoefficient();

        while (remainder.getDegree() >= divisorDegree && !remainder.isZero()) {
            int degreeDifference = remainder.getDegree() - divisorDegree;
            double quotientCoefficient;
            if (divisorLeadingCoefficient != 0.0) {
                quotientCoefficient = remainder.getLeadingCoefficient() / divisorLeadingCoefficient;
            }
            else {
                // handle division by 0 case
                quotientCoefficient = 0.0;
            }
            quotientTerms.put(degreeDifference, quotientCoefficient);
            RealPolynomial term = divisor.multiplyByConstant(quotientCoefficient).multiplyByMonomial(degreeDifference, quotientCoefficient);
            remainder = remainder.subtract(term);
        }

        return quotient;
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


    public RealPolynomial integrate() {
        TreeMap<Integer, Double> integratedTerms = new TreeMap<>();
        for (Map.Entry<Integer, Double> term : this.terms.entrySet()) {
            int exp = term.getKey();
            double coeff = term.getValue();
            integratedTerms.put(exp + 1, coeff / (exp + 1));
        }
        return new RealPolynomial(integratedTerms);
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
