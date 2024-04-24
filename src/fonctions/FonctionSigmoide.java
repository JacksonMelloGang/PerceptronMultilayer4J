package fonctions;

public class FonctionSigmoide implements FonctionActivation {
    @Override
    public double activer(double input) {
        return 1 / (1 + Math.exp(-input));
    }
}
