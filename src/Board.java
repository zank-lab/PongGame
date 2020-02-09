import java.awt.*;

public class Board {
    private Prostokat komputer, gracz;
    private Pilka pilka;
    private int dlugoscProstokata = 100, szerokoscProstokata = 20, xGracza = 10, xKomputera = 770;
    private int predkoscKomputer = 5;
    private int szerokoscPilki = 20, dlugoscPilki = 20;
    private int obliczonaPozycjaKomp;
    private int width, height;


    public Board(int w, int h) {
        width = w;
        height = h;
        komputer = new Prostokat(dlugoscProstokata, szerokoscProstokata, xKomputera, height);
        pilka = new Pilka(szerokoscPilki, dlugoscPilki, width, height);
        gracz = new Prostokat(dlugoscProstokata, szerokoscProstokata, xGracza, height);
    }

    public void wyrysuj(Graphics2D g2d) {
        kolizja();
        animacjaKomputera();
        komputer.rysujProstokat(g2d);
        gracz.rysujProstokat(g2d);
        pilka.rysujPilkeIWynik(g2d);
    }

    public void setPredkoscGracz(int v) {
        gracz.setPredkosc(v);
    }

    public boolean czyKoniec(){
        return pilka.koniec();
    }
    public void restart(){
        pilka.restart();
        komputer.restart();
        gracz.restart();
    }

    private void kolizja() {
        if (pilka.getX() <= xGracza + szerokoscProstokata) {
            if (pilka.getY() == gracz.getY() + dlugoscProstokata && pilka.getY() + dlugoscPilki == gracz.getY()) { pilka.odbijKrotkiBok();} // krotkie boki gracza
            else if (pilka.getY() < gracz.getY() + dlugoscProstokata && pilka.getY() + dlugoscPilki > gracz.getY()) { pilka.odbijDlugiBok();}  // dlugie boki gracza
        } else if (pilka.getX() + szerokoscPilki >= xKomputera) {
            if (pilka.getY() == komputer.getY() + dlugoscProstokata && pilka.getY() + dlugoscPilki == komputer.getY()) { pilka.odbijKrotkiBok(); }               // krotkie boki komputera
            else if (pilka.getY() < komputer.getY() + dlugoscProstokata && pilka.getY() + dlugoscPilki > komputer.getY()) { pilka.odbijDlugiBok(); }           // dlugie boki komputera
        }
    }

    private void animacjaKomputera() {
        aktualizujPozycjeKomp();
        if (obliczonaPozycjaKomp > komputer.getY() + dlugoscProstokata) {
            komputer.setPredkosc(predkoscKomputer);
        } else if (obliczonaPozycjaKomp < komputer.getY()) {
            komputer.setPredkosc(-1 * predkoscKomputer);
        } else {
            komputer.setPredkosc(0);
        }
    }

    public void aktualizujPozycjeKomp() {
        if (pilka.leciWKomputer()) {
            obliczonaPozycjaKomp = obliczPozycjeKomputera();
            pilka.obliczonoPozycjeKomp();
        }
    }

    private int obliczPozycjeKomputera() {        // liczy pozycje Y dla jakiej komputer powinien sie ustawic
        int y = pilka.getY();
        int v = pilka.getVy();
        int x = pilka.getX();
        while (x < width) {
            if (y + dlugoscPilki > height || y < 0) { v *= -1; }
            x += pilka.getVx();
            y += v;
            if (x + szerokoscPilki > xKomputera) { return y; }
        }
        return 0;
    }
}
