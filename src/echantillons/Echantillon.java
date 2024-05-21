package echantillons;

import java.util.Arrays;

public class Echantillon {

    private double[] entrees;
    private double[] resultatAttendus;


    public Echantillon(double[] entrees, double[] resultatAttendus) {
        this.entrees = entrees;
        this.resultatAttendus = resultatAttendus;
    }

    public double[] getEntrees() {
        return entrees;
    }
    public double[] getResultatAttendus(){
        return resultatAttendus;
    }

    @Override
    public String toString() {
        return "Echantillon{" + "entrees=" + Arrays.toString(entrees) + ", résultat attendu=" + Arrays.toString(resultatAttendus) + '}';
    }
}
