import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

public class Komponent extends JComponent implements ActionListener, KeyListener {

    private Timer timer;
    private Plansza gra;
    private boolean pierwszeRozpoczecieGry = false;
    private boolean czyMoznaZrestartowac = false;

    Komponent(int szerokosc, int wysokosc, int opoznienie) {
        gra = new Plansza(szerokosc, wysokosc);
        timer = new Timer(opoznienie, this);
        addKeyListener(this);
        setFocusable(true); // enable KeyListener
        setFocusTraversalKeysEnabled(false);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int klawisz = e.getKeyCode();
        if (klawisz == KeyEvent.VK_UP) { gra.ruszProstokatemGracza(-1); }
        if (klawisz == KeyEvent.VK_DOWN) { gra.ruszProstokatemGracza(1); }
        if (klawisz == KeyEvent.VK_ENTER && !pierwszeRozpoczecieGry) {
            timer.start();
            pierwszeRozpoczecieGry = true;
        }
        if (klawisz== KeyEvent.VK_SPACE && czyMoznaZrestartowac){
            gra.restart();
            timer.start();
            czyMoznaZrestartowac=false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        gra.ruszProstokatemGracza(0);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 600);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.WHITE);
        gra.wyrysuj(g2d);
        if (!pierwszeRozpoczecieGry) {
            g2d.drawString("Wcisnij ENTER aby zaczac rozgrywke", 180, getHeight()/3);
        }
        if(gra.czyKoniec()){
            g2d.drawString("Koniec Gry! Wcisnij SPACJE by zagrac jeszcze raz",80,getHeight()/3);
            czyMoznaZrestartowac =true;
            timer.stop();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
        revalidate();
    }
}