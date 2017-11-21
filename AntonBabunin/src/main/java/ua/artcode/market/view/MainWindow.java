/*package ua.artcode.market.view;

import ua.artcode.market.controllers.TerminalControllerFactory;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainWindow extends JFrame {

    private TextField login = new TextField(20);
    private TextField password = new TextField(20);


    private JTextField textField;

    public MainWindow() {
        super("Test frame");
        createGUI();
    }

    public void createGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        TextField login = new TextField(20);
        TextField password = new TextField();

        panel.add(login);
        panel.add(password);


        JButton ok = new JButton("OK");
        ok.setActionCommand(String.valueOf(TerminalControllerFactory.create().getiAppDb().getProducts().size()));
        panel.add(ok);

        JButton button2 = new JButton("Button 2");
        button2.setActionCommand("Button 2 was pressed!");
        panel.add(button2);

        JButton button3 = new JButton("Button 3");
        button3.setActionCommand("Button 3 was pressed!");
        panel.add(button3);

        textField = new JTextField();
        textField.setColumns(23);
        panel.add(textField);

        ActionListener actionListener = new TestActionListener();

        ok.addActionListener(actionListener);
        button2.addActionListener(actionListener);

        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textField.setText(e.getActionCommand());
            }
        });

        getContentPane().add(panel);
        setPreferredSize(new Dimension(320, 100));
    }

    public class TestActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            textField.setText(e.getActionCommand());
        }
    }
}*/


