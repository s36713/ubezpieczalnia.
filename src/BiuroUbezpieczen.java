import java.util.ArrayList;

public class BiuroUbezpieczen {
    private String nazwa;
    private ArrayList<Polisa> polisy;

    public BiuroUbezpieczen(String nazwa) {
        this.nazwa = nazwa;
        this.polisy = new ArrayList<>();
    }

    public void dodajPolisa(Polisa polisa) {
        this.polisy.add(polisa);
    }

    public double policzLacznaSkladke() {
        double suma = 0;
        for (Polisa p : polisy) {
            suma += p.obliczSkladkeKoncowa();


        }
        return suma;
    }

    public double policzLacznaPrognozeOdnowien() {
        double suma = 0;
        for (Polisa p : polisy) {
            suma += p.getSkladkaBazowa();
        }
        return suma;
    }

    public int policzPolisywysokiegoRyzyka() {
        int licznik = 0;
        for (Polisa p : polisy) {
            if (p.getPoziomRyzyka() >= 4) {
                licznik++;
            }

        }
        return licznik;
    }

    public Polisa znajdzPoNumerze(String numerPolisy) {
        for (Polisa p : polisy) {
            if (p.getNumerPolisy().equals(numerPolisy)) {
                return p;
            }
        }
        return null;
    }

    public void wypiszTanszeNiz(double prog) {
        System.out.println("Polisy tańsze niż " + prog + " zł:");
        for (Polisa p : polisy) {
            if (p.obliczSkladkeKoncowa() < prog) {
                System.out.println(p);
            }
        }
    }
    public void wypiszRaport() {
        System.out.println("--- RAPORT BIURA: " + nazwa + " ---");
        System.out.println("Liczba wszystkich polis: " + polisy.size());

        for (Polisa p : polisy) {
            System.out.println(p.toString());
        }

        System.out.println("---------------------------------");
        System.out.println("Łączna wartość składek: " + policzLacznaSkladke() + " zł");
        System.out.println("Łączna prognoza odnowień: " + policzLacznaPrognozeOdnowien() + " zł");
        System.out.println("Ilość polis wysokiego ryzyka: " + policzPolisywysokiegoRyzyka());
    }

}