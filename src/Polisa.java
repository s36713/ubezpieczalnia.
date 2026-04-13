import java.util.Objects;

public class Polisa {
    private String numerPolisy;
    private String klient;
    private double skladkaBazowa;
    private int poziomRyzyka;
    private double wartoscPojazdu;
    private boolean czyMaAlarm;
    private boolean czyBezszkodowyKlient;
    private static int liczbaUtworzonychPolis;
    private static final double OPLATA_ADMINISTRACYJNA = 200;
    private static final double maskymalanawartosc = 1000;

    public Polisa(String numerPolisy, String klient, double skladkaBazowa, int poziomRyzyka, double wartoscPojazdu, boolean czyMaAlarm, boolean czyBezszkodowyKlient) {
        this.numerPolisy = numerPolisy;
        this.klient = klient;
        this.skladkaBazowa = skladkaBazowa;
        this.poziomRyzyka = poziomRyzyka;
        this.wartoscPojazdu = wartoscPojazdu;
        this.czyMaAlarm = czyMaAlarm;
        this.czyBezszkodowyKlient = czyBezszkodowyKlient;
        liczbaUtworzonychPolis++;
    }

    public String getNumerPolisy() {
        return numerPolisy;
    }

    public String getKlient() {
        return klient;
    }

    public double getSkladkaBazowa() {
        return skladkaBazowa;
    }

    public int getPoziomRyzyka() {
        return poziomRyzyka;
    }

    public double getWartoscPojazdu() {
        return wartoscPojazdu;
    }

    public boolean isCzyMaAlarm() {
        return czyMaAlarm;
    }

    public boolean isCzyBezszkodowyKlient() {
        return czyBezszkodowyKlient;
    }

    public static int getLiczbaUtworzonychPolis() {
        return liczbaUtworzonychPolis;
    }

    public double obliczSkladkeKoncowa() {
        double skladka = this.skladkaBazowa;
        double mnoznikRyzyka = 1.2 + (this.poziomRyzyka * 0.5);
        skladka *= mnoznikRyzyka;

        if (this.czyMaAlarm) {
            skladka *= 0.90;
        }
        if (this.czyBezszkodowyKlient) {
            skladka *= 0.85;
        }
        if (maskymalanawartosc < this.wartoscPojazdu) {
            skladka += (this.wartoscPojazdu * 0.01);
        }
        skladka += OPLATA_ADMINISTRACYJNA;

        if (skladka < this.skladkaBazowa) {
            System.out.println(" koncowa skladka jest niepoprawna");
        }
        return skladka;
    }

    public double ObliczSkladkeOdnowieniowa() {
        double skladkaodnowieniowa = this.obliczSkladkeKoncowa();
        double nowaskladka = skladkaodnowieniowa;
        double progminimalny = skladkaodnowieniowa * 0.90;
        double progmaksymalny = skladkaodnowieniowa * 1.25;
        if (this.poziomRyzyka == 4) {
            nowaskladka *= 1.10;
        } else if (this.poziomRyzyka >= 5) {
            nowaskladka *= 1.20;
        }
        if (this.czyBezszkodowyKlient) {
            nowaskladka *= 0.92;
        }
        if (this.czyMaAlarm) {
            nowaskladka *= 0.95;
        }
        if (nowaskladka < progminimalny) {
            nowaskladka = progminimalny;
        }
        if (nowaskladka > progmaksymalny) {
            nowaskladka = progmaksymalny;
        }
        nowaskladka = Math.round(nowaskladka * 100.0) / 100.0;
        return nowaskladka;

    }

        public String pobierzPodsumowanieRyzyka() {
            if (poziomRyzyka <= 2) return "Niskie";
            if (poziomRyzyka <= 4) return "Średnie";
            return "Wysokie";
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Polisa polisa = (Polisa) o;
        return Objects.equals(numerPolisy, polisa.numerPolisy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numerPolisy);
    }

    @Override
    public String toString() {
        return String.format("Polisa [%s] | Klient: %s | Składka: %.2f zł",
                numerPolisy, klient, obliczSkladkeKoncowa());
    }
}

