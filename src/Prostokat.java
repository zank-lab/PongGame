import java.awt.*;

public class Prostokat {
    private int dlugoscProstokata, szerokoscProstokata, wspolrzednaX, wspolrzednaY;
    private int predkosc;

    public Prostokat(int dlugosc, int szerokosc, int x, int wysokosc) {
        dlugoscProstokata = dlugosc;
        szerokoscProstokata = szerokosc;
        wspolrzednaX = x;
        wspolrzednaY = wysokosc / 2 - dlugoscProstokata / 2;
    }

    public void rysujProstokat(Graphics2D g2d, int wysokosc) {
        stan(wysokosc);
        g2d.fillRect(wspolrzednaX, wspolrzednaY, szerokoscProstokata, dlugoscProstokata);
    }

    public void stan(int height) {
        wspolrzednaY += predkosc;
        if (wspolrzednaY > height - dlugoscProstokata) {
            wspolrzednaY = height - dlugoscProstokata;
        }
        if (wspolrzednaY < 0) {
            wspolrzednaY = 0;
        }
    }

    public void restart(int height) {
        wspolrzednaY = height / 2 - dlugoscProstokata / 2;
    }

    public void ruszProstokatem(int kierunek){
        if (kierunek==-1){predkosc=-5;}
        if (kierunek==0){predkosc=0;}
        if(kierunek==1){predkosc=5;}
    }

    public int getY() {
        return wspolrzednaY;
    }

}
