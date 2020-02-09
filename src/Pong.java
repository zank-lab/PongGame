import javax.swing.*;

public class Pong {
    private JPanel panel;
    private Komponent komponent;

    public void CreateGUI() {
        JFrame frame = new JFrame("Pong");
        frame.setSize(800, 600);
        frame.setContentPane(new Pong().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                System.out.println(Thread.currentThread().getName());
                Pong app = new Pong();
                app.CreateGUI();
            }
        });
    }

    private void createUIComponents() {
        komponent = new Komponent(800,600,10);
    }
}
