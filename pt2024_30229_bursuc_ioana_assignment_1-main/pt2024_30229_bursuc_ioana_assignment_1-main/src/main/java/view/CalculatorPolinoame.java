package view;

import model.Operatii;
import model.Polinom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class CalculatorPolinoame extends JFrame implements ActionListener {
    private JTextField polinom1Field, polinom2Field;
    private JTextArea resultArea;

    public CalculatorPolinoame() {
        setTitle("Calculator Polinoame");
        setSize(400, 300);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 3));

        JLabel label1 = new JLabel("Polinom 1:");
        polinom1Field = new JTextField();
        label1.setBounds(0,0,200,40);

        JLabel label2 = new JLabel("Polinom 2:");
        polinom2Field = new JTextField();
        label2.setBounds(40,40,200,40);


        JButton addButton = new JButton("Adunare");
        JButton subtractButton = new JButton("Scadere");
        JButton multiplyButton = new JButton("Inmultire");
        JButton divideButton = new JButton("Impartire");
        JButton derivativeButton = new JButton("Derivata");
        JButton integrateButton = new JButton("Integrala");

        resultArea = new JTextArea("Rezultat");
        resultArea.setEditable(false);
        resultArea.setLineWrap(true);

        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        add(label1);
        add(polinom1Field);
        add(new JLabel());

        add(label2);
        add(polinom2Field);
        add(new JLabel());

        add(addButton);
        add(subtractButton);
        add(multiplyButton);
        add(divideButton);
        add(derivativeButton);
        add(integrateButton);

        add(scrollPane);

        addButton.addActionListener(this);
        subtractButton.addActionListener(this);
        multiplyButton.addActionListener(this);
        divideButton.addActionListener(this);
        derivativeButton.addActionListener(this);
        integrateButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String polinom1Text = polinom1Field.getText();
        String polinom2Text = polinom2Field.getText();

        Polinom poly1=new Polinom();
        poly1=poly1.Parse(polinom1Text);
        Polinom poly2=new Polinom();
        poly2=poly2.Parse(polinom2Text);

        if (e.getActionCommand().equals("Adunare")) {
            if (!polinom1Text.isEmpty() && !polinom2Text.isEmpty()) {
                String result = Operatii.adunare(poly1, poly2);
                resultArea.setText(result);
            } else {
                JOptionPane.showMessageDialog(this, "Introduceți polinoame valide în ambele câmpuri.", "Eroare", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getActionCommand().equals("Scadere")) {
            if (!polinom1Text.isEmpty() && !polinom2Text.isEmpty()) {
                Polinom result = Operatii.scadere(poly1, poly2);
                resultArea.setText(result.toString());
            } else {
                JOptionPane.showMessageDialog(this, "Introduceți polinoame valide în ambele câmpuri.", "Eroare", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getActionCommand().equals("Inmultire")) {
            if (!polinom1Text.isEmpty() && !polinom2Text.isEmpty()) {
                Polinom result = Operatii.inmultire(poly1, poly2);
                resultArea.setText(result.toString());
            } else {
                JOptionPane.showMessageDialog(this, "Introduceți polinoame valide în ambele câmpuri.", "Eroare", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getActionCommand().equals("Impartire")) {
            if (!polinom1Text.isEmpty() && !polinom2Text.isEmpty()) {
                String result = Operatii.impartire(poly1, poly2);
                resultArea.setText(result);
            } else {
                JOptionPane.showMessageDialog(this, "Introduceți polinoame valide în ambele câmpuri.", "Eroare", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getActionCommand().equals("Derivata")) {
            if (!polinom1Text.isEmpty() && polinom2Text.isEmpty()) {
                String result = Operatii.derivare(poly1);
                resultArea.setText(result);
            } else {
                JOptionPane.showMessageDialog(this, "Introduceți polinom valid doar in primul camp.", "Eroare", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getActionCommand().equals("Integrala")) {
            if (!polinom1Text.isEmpty() && polinom2Text.isEmpty()) {
                String result = Operatii.integrala(poly1);
                resultArea.setText(result);
            } else {
                JOptionPane.showMessageDialog(this, "Introduceți polinom valid doar in primul camp.", "Eroare", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}
