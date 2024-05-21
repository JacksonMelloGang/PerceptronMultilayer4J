package neuronal.activation;

import java.io.Serializable;

public class FonctionTanh  implements FonctionActivation, Serializable {
    @Override
    public double activer(double input) {
        return Math.tanh(input);
    }

    @Override
    public double derivee(double input) {
        double sig = activer(input);
        return sig * (1 - sig);
    }
}
