import java.awt.*;
import java.util.Random;

public class Pilka {
    private int predkoscPilkiX, predkoscPilkiY, pozycjaPilkiX, pozycjaPilkiY;
    private int szerokoscPilki, dlugoscPilki;
    private int szerokosc, wysokosc;
    private int uderzenia;
    private boolean leciWStroneKomputera = false;
    private Random r = new Random();

    public Pilka(int szerokoscPilki, int dlugoscPilki, int szerokosc, int wysokosc) {
        this.szerokosc = szerokosc;
        this.wysokosc = wysokosc;
        this.szerokoscPilki = szerokoscPilki;
        this.dlugoscPilki = dlugoscPilki;
        restartPilki();
    }

    public void rysujPilke(Graphics2D g2d) {
        stanPilki();
        g2d.fillOval(pozycjaPilkiX, pozycjaPilkiY, szerokoscPilki, dlugoscPilki);
    }

    public void restartPilki() {
        int[] values = {-3, 3};
        pozycjaPilkiX = szerokosc / 2;
        pozycjaPilkiY = wysokosc / 2;
        predkoscPilkiX = values[r.nextInt(2)];
        predkoscPilkiY = 3;
        uderzenia = 0;
        sprawdzCzyLeciWKomputer();
    }

    private void stanPilki() {
        if (pozycjaPilkiY + dlugoscPilki >= wysokosc || pozycjaPilkiY <= 0) { predkoscPilkiY *= -1; }
         pozycjaPilkiX += predkoscPilkiX;
         pozycjaPilkiY += predkoscPilkiY;
    }

    public boolean graczPunkt(){
        if (pozycjaPilkiX + dlugoscPilki > szerokosc) {
            restartPilki();
            return true;
        }
        else return false;
    }

    public boolean komputerPunkt(){
            if (pozycjaPilkiX < 0) {
                restartPilki();
            return true;
        }
            else return false;
    }

    public void odbijDlugiBok() {
        uderzenia++;
        if (predkoscPilkiX < 0) { predkoscPilkiX -= uderzenia / 5; }
        else { predkoscPilkiX += uderzenia / 5; }
        predkoscPilkiX *= -1;
        predkoscPilkiY = 7 - r.nextInt(14);
        if (predkoscPilkiY == 0) { predkoscPilkiY = 1; }
        sprawdzCzyLeciWKomputer();
    }

    public void odbijKrotkiBok(){
        predkoscPilkiY *=-1;
        predkoscPilkiX *=-1;
        sprawdzCzyLeciWKomputer();
    }

    public void sprawdzCzyLeciWKomputer(){
        if (predkoscPilkiX > 0) { leciWStroneKomputera = true; }
    }

    public int getX() { return pozycjaPilkiX; }

    public int getY() { return pozycjaPilkiY; }

    public int getVx() { return predkoscPilkiX; }

    public int getVy() { return predkoscPilkiY; }

    public boolean leciWKomputer() { return leciWStroneKomputera; }

    public void obliczonoPozycjeKomp() { leciWStroneKomputera = false; }
}
