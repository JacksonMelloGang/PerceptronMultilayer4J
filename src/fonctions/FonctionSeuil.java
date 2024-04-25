package fonctions;

public class FonctionSeuil implements FonctionActivation{
    private double seuil;

    public FonctionSeuil(double seuil) {
        this.seuil = seuil;
    }

    @Override
    public double activer(double input) {
        return input > seuil ? 1 : 0;
    }
}
