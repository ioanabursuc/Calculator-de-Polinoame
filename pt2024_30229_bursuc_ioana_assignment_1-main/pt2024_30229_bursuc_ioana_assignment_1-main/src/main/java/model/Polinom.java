package model;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Polinom {
    private Map<Integer, Double> terms;

    public Polinom Parse(String p) {
        Polinom result = new Polinom();
        Pattern pattern = Pattern.compile("([+-]?[^-+]+)");
        Matcher matcher = pattern.matcher(p);
        while (matcher.find()) {
            if (matcher.group(1).contains("-x^")) {
                Pattern pattern4 = Pattern.compile("\\d");
                Matcher matcher4 = pattern4.matcher(matcher.group(1));
                while (matcher4.find())
                    result.addTerm(Integer.parseInt(matcher4.group()), -1);
            } else if (matcher.group(1).contains("x^")) {
                Pattern pattern2 = Pattern.compile("(-)?\\d");
                Matcher matcher2 = pattern2.matcher(matcher.group(1));
                int ok = 0;
                int coef = 1, expo = 0;
                while (matcher2.find()) {
                    if (ok == 0) {
                        if (matcher2.group().equals("-"))
                            coef = -1;
                        else
                            coef = Integer.parseInt(matcher2.group());
                        ok = 1;
                    } else
                        expo = Integer.parseInt(matcher2.group());
                }
                if (expo == 0)
                    result.addTerm(coef, 1);
                else
                    result.addTerm(expo, coef);
            } else if (matcher.group(1).contains("x")) {
                Pattern pattern3 = Pattern.compile("[+-]?[\\d+]?");
                Matcher matcher3 = pattern3.matcher(matcher.group(1));
                if (matcher3.find()) {
                    if (matcher3.group().equals("-"))
                        result.addTerm(1, -1);
                    else if (matcher3.group().equals("+"))
                        result.addTerm(1, 1);
                    else if(matcher3.group().equals(""))
                        result.addTerm(1,1);
                    else
                        result.addTerm(1, Integer.parseInt(matcher3.group()));
                }
                else result.addTerm(1,1);
            } else
                result.addTerm(0, Integer.parseInt(matcher.group()));
        }
        return result;
    }

    public Polinom() {
        terms = new HashMap<>();
    }

    public void addTerm(int exponent, double coefficient) {
        terms.put(exponent, coefficient);
    }

    public Map<Integer, Double> getTerms() {
        return terms;
    }

    public int getHighestDegree() {
        int highestDegree = Integer.MIN_VALUE;
        for (int exponent : terms.keySet()) {
            if (exponent > highestDegree) {
                highestDegree = exponent;
            }
        }
        return highestDegree;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        TreeMap<Integer, Double> sortedTerms = new TreeMap<>((a, b) -> b.compareTo(a)); // Sortare descrescătoare a exponenților
        sortedTerms.putAll(terms);

        for (Map.Entry<Integer, Double> term : sortedTerms.entrySet()) {
            double coefficient = term.getValue();
            int exponent = term.getKey();
            if (coefficient != 0) {
                if (sb.length() > 0) {
                    sb.append(coefficient >= 0 ? " + " : " - ");
                    coefficient = Math.abs(coefficient);
                }
                if (exponent == 0 || coefficient != 1) {
                    sb.append(coefficient);
                }
                if (exponent > 0) {
                    sb.append("x");
                    if (exponent > 1) {
                        sb.append("^").append(exponent);
                    }
                }
            }
        }
        return sb.length() > 0 ? sb.toString() : "0";
    }
}
