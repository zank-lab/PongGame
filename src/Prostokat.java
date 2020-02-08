import java.awt.*;

public class Prostokat {
    private int dlugoscProstokata, szerokoscProstokata, wspolrzednaX, wspolrzednaY;
    private int height, predkosc;

    public Prostokat(int d, int s, int x, int h) {
        height = h;
        dlugoscProstokata = d;
        szerokoscProstokata = s;
        wspolrzednaX = x;
        wspolrzednaY = h / 2 - dlugoscProstokata / 2;
    }

    public void rysujProstokat(Graphics2D g2d) {
        stan();
        g2d.fillRect(wspolrzednaX, wspolrzednaY, szerokoscProstokata, dlugoscProstokata);
    }

    public void stan() {
        if (wspolrzednaY > height - dlugoscProstokata) {
            wspolrzednaY = height - dlugoscProstokata;
        }
        if (wspolrzednaY < 0) {
            wspolrzednaY = 0;
        }
        wspolrzednaY += predkosc;
    }

    public void restart() {
        wspolrzednaY = height / 2 - dlugoscProstokata / 2;
    }

    public void setPredkosc(int a) {
        predkosc = a;
    }

    public int getY() {
        return wspolrzednaY;
    }

}
