package neuronal.activation;

import java.io.Serializable;

public class FonctionSigmoide implements FonctionActivation, Serializable {
    @Override
    public double activer(double input) {
        return 1 / (1 + Math.exp(-input));
    }

    @Override
    public double derivee(double input) {
        return activer(input) * (1 - activer(input));
    }
}
