package app;

import javax.swing.*;
import view.CalculatorPolinoame;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CalculatorPolinoame calculator = new CalculatorPolinoame();
            calculator.setVisible(true);
        });
    }

}

