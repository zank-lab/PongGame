import java.awt.*;
import java.util.Random;

public class Plansza {
    private Prostokat komputer, gracz;
    private Pilka pilka;
    private Wynik wynik;
    private int dlugoscProstokata = 100, szerokoscProstokata = 20, xGracza = 10, xKomputera = 770;
    private int szerokoscPilki = 20, dlugoscPilki = 20;
    private int obliczonaPozycjaKomp;
    private int szerokosc, wysokosc;
    private Random blad=new Random();

    public Plansza(int szerokosc, int wysokosc) {
        this.szerokosc = szerokosc;
        this.wysokosc = wysokosc;
        komputer = new Prostokat(dlugoscProstokata, szerokoscProstokata, xKomputera, this.wysokosc);
        pilka = new Pilka(szerokoscPilki, dlugoscPilki, this.szerokosc, this.wysokosc);
        gracz = new Prostokat(dlugoscProstokata, szerokoscProstokata, xGracza, this.wysokosc);
        wynik = new Wynik(pilka);
    }

    public void wyrysuj(Graphics2D g2d) {
        kolizja();
        animacjaKomputera();
        komputer.rysujProstokat(g2d, wysokosc);
        gracz.rysujProstokat(g2d, wysokosc);
        pilka.rysujPilke(g2d);
        wynik.rysujWynik(g2d, szerokosc, wysokosc);
    }

    public void ruszProstokatemGracza(int kierunek) {
        gracz.ruszProstokatem(kierunek);
    }

    public boolean czyKoniec(){ return wynik.czyKoniec();}

    public void restart(){
        pilka.restartPilki();
        komputer.restart(wysokosc);
        gracz.restart(wysokosc);
        wynik.restartWyniku();
    }

    private void kolizja() {
        if (pilka.getX() <= xGracza + szerokoscProstokata) {
            if (pilka.getY() == gracz.getY() + dlugoscProstokata || pilka.getY() + dlugoscPilki == gracz.getY()) { pilka.odbijKrotkiBok();} // krotkie boki gracza
            else if (pilka.getY() < gracz.getY() + dlugoscProstokata && pilka.getY() + dlugoscPilki > gracz.getY()) { pilka.odbijDlugiBok();}  // dlugie boki gracza
        } else if (pilka.getX() + szerokoscPilki >= xKomputera) {
            if (pilka.getY() == komputer.getY() + dlugoscProstokata || pilka.getY() + dlugoscPilki == komputer.getY()) { pilka.odbijKrotkiBok(); }               // krotkie boki komputera
            else if (pilka.getY() < komputer.getY() + dlugoscProstokata && pilka.getY() + dlugoscPilki > komputer.getY()) { pilka.odbijDlugiBok(); }           // dlugie boki komputera
        }
    }

    private void animacjaKomputera() {
        aktualizujPozycjeKomp();
        if (obliczonaPozycjaKomp > komputer.getY() + dlugoscProstokata) {
            komputer.ruszProstokatem(1);
        } else if (obliczonaPozycjaKomp < komputer.getY()) {
            komputer.ruszProstokatem(-1);
        } else {
            komputer.ruszProstokatem(0);
        }
    }

    public void aktualizujPozycjeKomp() {
        if (pilka.leciWKomputer()) {
            obliczonaPozycjaKomp = obliczPozycjeKomputera()+obliczBladKomputera(4);     // im wyzej tym trudniej
            pilka.obliczonoPozycjeKomp();
        }
    }

    public int obliczBladKomputera(int poziomTrudnosci){
        return dlugoscProstokata/poziomTrudnosci - blad.nextInt(dlugoscProstokata*2/poziomTrudnosci);
    }

    private int obliczPozycjeKomputera() {        // liczy pozycje Y dla jakiej komputer powinien sie ustawic
        int y = pilka.getY();
        int v = pilka.getVy();
        int x = pilka.getX();
        while (x < szerokosc) {
            if (y + dlugoscPilki >= wysokosc || y <= 0) { v *= -1; }
            x += pilka.getVx();
            y += v;
            if (x + szerokoscPilki >= xKomputera) { return y; }
        }
        return 0;
    }
}
