package fonctions;

public class FonctionReLU implements FonctionActivation {
    @Override
    public double activer(double input) {
        return Math.max(0, input);
    }
}
