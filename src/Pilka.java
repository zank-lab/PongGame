import java.awt.*;
import java.util.Random;

public class Pilka {
    private int predkoscPilkiX, predkoscPilkiY, pozycjaPilkiX, pozycjaPilkiY;
    private int szerokoscPilki, dlugoscPilki;
    private int wynikGracza = 0, wynikKomputera = 0;
    private int width, height;
    private int uderzenia;
    private boolean leciWStroneKomputera = false;
    private Random r = new Random();

    public Pilka(int szerokosc, int dlugosc, int w, int h) {
        width = w;
        height = h;
        szerokoscPilki = szerokosc;
        dlugoscPilki = dlugosc;
        resetPilki();
    }

    public void rysujPilkeIWynik(Graphics2D g2d) {
        stanPilki();
        g2d.fillOval(pozycjaPilkiX, pozycjaPilkiY, szerokoscPilki, dlugoscPilki);
        g2d.setFont(new Font("TimesRoman", Font.BOLD, 28));
        g2d.drawString(String.valueOf(wynikGracza), width / 4, height);
        g2d.drawString(String.valueOf(wynikKomputera), width - width / 4, height);
    }

    private void resetPilki() {
        int[] values = {-3, 3};
        pozycjaPilkiX = width / 2;
        pozycjaPilkiY = height / 2;
        predkoscPilkiX = values[r.nextInt(2)];
        predkoscPilkiY = 3;
        uderzenia = 0;
        if (predkoscPilkiX > 0) { leciWStroneKomputera = true; }
    }

    private void stanPilki() {
        if (pozycjaPilkiY + dlugoscPilki > height || pozycjaPilkiY < 0) { predkoscPilkiY *= -1; }
        if (pozycjaPilkiX + dlugoscPilki > width) {
            resetPilki();
            wynikGracza++;
        }
        if (pozycjaPilkiX < 0) {
            resetPilki();
            wynikKomputera++;
        }
        pozycjaPilkiX += predkoscPilkiX;
        pozycjaPilkiY += predkoscPilkiY;
    }

    public void odbijDlugiBok() {
        uderzenia++;
        if (predkoscPilkiX < 0) { predkoscPilkiX -= uderzenia / 5; }
        else { predkoscPilkiX += uderzenia / 5; }
        predkoscPilkiX *= -1;
        predkoscPilkiY = 7 - r.nextInt(14);
        if (predkoscPilkiY == 0) { predkoscPilkiY = 1; }
    }

    public void odbijKrotkiBok(){
        predkoscPilkiY *=-1;
        predkoscPilkiX *=-1;
    }

    public boolean koniec(){
        if(wynikGracza==10 || wynikKomputera==10){
            return true;
        }
        else return false;
    }

    public void restart(){
        wynikKomputera=0;
        wynikGracza=0;
        resetPilki();
    }

    public int getX() { return pozycjaPilkiX; }

    public int getY() { return pozycjaPilkiY; }

    public int getVx() { return predkoscPilkiX; }

    public int getVy() { return predkoscPilkiY; }

    public boolean leciWKomputer() { return leciWStroneKomputera; }

    public void obliczonoPozycjeKomp() { leciWStroneKomputera = false; }


}
