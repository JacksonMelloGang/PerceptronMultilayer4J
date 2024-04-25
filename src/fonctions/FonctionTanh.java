package fonctions;

public class FonctionTanh  implements FonctionActivation {
    @Override
    public double activer(double input) {
        return Math.tanh(input);
    }
}
