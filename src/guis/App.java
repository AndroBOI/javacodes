package guis;


import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class App extends JFrame {

    App() {

        setTitle("Main App");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JMenuBar bar = new JMenuBar();
        JMenu app = new JMenu("Applications");
        JMenu developers = new JMenu("Developers");

        JMenuItem dev1 = new JMenuItem("Mikella");
        JMenuItem dev2 = new JMenuItem("Kassandra");
        JMenuItem dev3 = new JMenuItem("Mikaella");
        JMenuItem dev4 = new JMenuItem("Andrew");


        developers.add(dev1);
        developers.add(dev2);
        developers.add(dev3);
        developers.add(dev4);

        JMenuItem tempItem = new JMenuItem("Temperature Converter");
        JMenuItem curr = new JMenuItem("Currency Converter");
        JMenuItem rand = new JMenuItem("Guessing Number Game");
        JMenuItem rps = new JMenuItem("RockPaperScissors");
        JMenuItem calc = new JMenuItem("Calculator");


        app.add(tempItem);
        app.add(curr);
        app.add(rand);
        app.add(rps);
        app.add(calc);


        bar.add(app);
        bar.add(developers);
        setJMenuBar(bar);


        tempItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TemperatureConverterGUI().setVisible(true);
            }
        });

        curr.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CurrencyConverter().setVisible(true);
            }
        });

        rand.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RandomNumberGame().setVisible(true);
            }
        });

        rps.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RockPaperScissorsGUI().setVisible(true);
            }
        });

        calc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Calculator().setVisible(true);
            }

        });


    }


    public static void main(String[] args) {
        App app = new App();
        app.setVisible(true);
    }
}