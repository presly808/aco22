
package ua.artcode.market;

import ua.artcode.market.view.MainWindow;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
//                JFrame.setDefaultLookAndFeelDecorated(true);
                MainWindow frame = new MainWindow();

                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}
