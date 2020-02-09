import java.awt.*;

public class Wynik {
    private Pilka pilka;
    private final String fontName="TimesRoman";
    private final int fontSize=28;
    private int wynikGracza=0,wynikKomputera=0;

    public Wynik(Pilka pilka){
        this.pilka=pilka;
    }
    public void stanWyniku(){
        if(pilka.graczPunkt()){wynikGracza++; }
        if(pilka.komputerPunkt()){wynikKomputera++;}
    }

    public boolean czyKoniec(){
        if (wynikKomputera==10 || wynikGracza==10){
            return true;
        }
        else return false;
    }
    public void restartWyniku(){
        wynikGracza=0;
        wynikKomputera=0;
    }

    public void rysujWynik(Graphics2D g2d,int szerokosc,int wysokosc) {
        stanWyniku();
        g2d.setFont(new Font(fontName, Font.BOLD, fontSize));
        g2d.drawString(String.valueOf(wynikGracza), szerokosc / 4, wysokosc);
        g2d.drawString(String.valueOf(wynikKomputera), szerokosc - szerokosc / 4, wysokosc);
    }
}
