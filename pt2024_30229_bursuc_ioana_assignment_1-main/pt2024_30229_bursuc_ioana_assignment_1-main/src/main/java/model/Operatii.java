package model;

import java.util.Map;


public class Operatii {


    public static String adunare(Polinom poly1, Polinom poly2) {
        Polinom result = new Polinom();
        for (Map.Entry<Integer, Double> term : poly1.getTerms().entrySet()) {
            result.addTerm(term.getKey(),term.getValue());

        }
        for (Map.Entry<Integer, Double> term : poly2.getTerms().entrySet()) {
            double newCoefficient = term.getValue();
            int exponent = term.getKey();
            if (result.getTerms().containsKey(exponent)) {
                newCoefficient += result.getTerms().get(exponent);
            }
            result.addTerm(exponent, newCoefficient);
        }
        return result.toString();
    }

    public static Polinom scadere(Polinom poly1, Polinom poly2) {
        Polinom result = new Polinom();
        for (Map.Entry<Integer, Double> term : poly1.getTerms().entrySet()) {
            result.addTerm(term.getKey(),term.getValue());

        }
        for (Map.Entry<Integer, Double> term : poly2.getTerms().entrySet()) {
            double newCoefficient = -term.getValue();
            int exponent = term.getKey();
            if (result.getTerms().containsKey(exponent)) {
                newCoefficient += result.getTerms().get(exponent);
            }
            result.addTerm(exponent, newCoefficient);
        }
        return result;
    }

    public static Polinom inmultire(Polinom poly1, Polinom poly2) {
        Polinom result = new Polinom();

        for (Map.Entry<Integer, Double> term1 : poly1.getTerms().entrySet()) {
            double coef1 = term1.getValue();
            int exp1 = term1.getKey();

            for (Map.Entry<Integer, Double> term2 : poly2.getTerms().entrySet()) {
                double coef2 = term2.getValue();
                int exp2 = term2.getKey();

                double newCoef = coef1 * coef2; // înmulțim coeficienții
                int newExp = exp1 + exp2; // adunăm exponenții

                // Adăugăm termenul rezultat în polinomul rezultat
                result.addTerm(newExp, newCoef);
            }
        }
        return result;
    }

    public static String impartire(Polinom poly1, Polinom poly2) {
        Polinom result = new Polinom();
        Polinom remainder = new Polinom();
        remainder=poly1;// Initialize remainder with poly1

        // Step 2: Divide the polynomial with the highest degree to the other polynomial having a lower degree
        while (remainder.getHighestDegree() >= poly2.getHighestDegree()) {
            // Step 3: Divide the first monomial of P to the first monomial of Q and obtain the first term of the quotient
            int divExp = remainder.getHighestDegree() - poly2.getHighestDegree();
            double divCoef = remainder.getTerms().get(remainder.getHighestDegree()) /
                    poly2.getTerms().get(poly2.getHighestDegree());
            Polinom quotientTerm = new Polinom();
            quotientTerm.addTerm(divExp, divCoef);
            result.addTerm(divExp, divCoef);

            // Step 4: Multiply the quotient with Q and subtract the result of the multiplication from P obtaining the remainder of the division
            Polinom termProduct = inmultire(poly2, quotientTerm);
            remainder = scadere(remainder, termProduct);
        }

        // Return the result (quotient)
        return result.toString();
    }
    public static String derivare(Polinom poly) {
        Polinom rezultat = new Polinom();

        // Iterăm prin fiecare termen al polinomului
        for (Map.Entry<Integer, Double> term : poly.getTerms().entrySet()) {
            double coeficient = term.getValue();
            int exponent = term.getKey();

            // Derivăm fiecare termen în funcție de regulile de derivare
            if (exponent > 0) {
                double nouCoeficient = coeficient * exponent; // Coeficientul nou
                int nouExponent = exponent - 1; // Exponentul nou

                // Adăugăm termenul derivat în polinomul rezultat
                rezultat.addTerm(nouExponent, nouCoeficient);
            }
        }

        return rezultat.toString();
    }

    public static String integrala(Polinom poly) {
        Polinom rezultat = new Polinom();

        // Iterăm prin fiecare termen al polinomului
        for (Map.Entry<Integer, Double> term : poly.getTerms().entrySet()) {
            double coeficient = term.getValue();
            int exponent = term.getKey();

            // Integrăm fiecare termen în funcție de regulile de integrare
            if (exponent != -1) {
                int nouExponent = exponent + 1; // Exponentul nou
                double nouCoeficient = (double) coeficient / nouExponent; // Coeficientul nou

                // Adăugăm termenul integrat în polinomul rezultat
                rezultat.addTerm(nouExponent,  nouCoeficient);
            } else {
                // Tratarea cazului particular când exponentul este -1 (logaritm natural)
                rezultat.addTerm(0, coeficient); // Coeficientul înmulțit cu variabila de integrare
                rezultat.addTerm(1, 0); // Adăugăm o constantă de integrare
            }
        }

        return rezultat.toString();
    }
}

