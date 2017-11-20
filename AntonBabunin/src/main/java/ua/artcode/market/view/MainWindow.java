/*package ua.artcode.market.view;

import ua.artcode.market.controllers.IAppDbImpl;
import ua.artcode.market.controllers.TerminalFactory;
import ua.artcode.market.interfaces.IAppDb;
import ua.artcode.market.interfaces.ITerminalController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import static com.sun.deploy.uitoolkit.ToolkitStore.dispose;

public class MainWindow implements ActionListener, WindowListener{

    TextField login = new TextField(20);
    TextField password = new TextField(20);

    public MainWindow(String title){
        super();
        mainWindow();
    }

    public void mainWindow(){

        JFrame frame = new JFrame("MarketApp");
        frame.setSize(new Dimension(1024, 768));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout((new FlowLayout()));



        frame.add(login);
        frame.add(password);
        JButton ok = new JButton("Connect");
        frame.add(ok);
        ok.addActionListener(this);

        frame.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        IAppDb iAppDb = new IAppDbImpl();
        try {
            iAppDb.login(login.getText(), password.getText());
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {
        try {
            dispose();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        System.exit(0);
    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }*//*

}
*/
