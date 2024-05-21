package neuronal.activation;

import java.io.Serializable;

public class FonctionSeuil implements FonctionActivation, Serializable {

    @Override
    public double activer(double input) {
        return input >= 0 ? 1 : 0;
    }

    @Override
    public double derivee(double input) {
        return 0;
    }
}
