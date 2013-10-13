package testjavaeditor;

import javax.swing.SwingUtilities;

public class TestJavaEditor {

    public static void main(String[] args) {

        // Start all Swing applications on the EDT.
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new JavaEditor().setVisible(true);
            }
        });
    }
}
