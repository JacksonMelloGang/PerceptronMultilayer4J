package neuronal.activation;

import java.io.Serializable;

public class FonctionLineaire implements FonctionActivation, Serializable {
    @Override
    public double activer(double input) {
        return input;
    }

    @Override
    public double derivee(double input) {
        return input;
    }
}
