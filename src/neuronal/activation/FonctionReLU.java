package neuronal.activation;

import java.io.Serializable;

public class FonctionReLU implements FonctionActivation, Serializable {
    @Override
    public double activer(double input) {
        return Math.max(0, input);
    }

    @Override
    public double derivee(double input) {
        return input > 0 ? 1 : 0;
    }
}
