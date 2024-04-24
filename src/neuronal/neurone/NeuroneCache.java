package neuronal.neurone;

import fonctions.FonctionActivation;

public class NeuroneCache extends Neurone {
    public NeuroneCache(int nombreEntrees, FonctionActivation activationFunction) {
        super(nombreEntrees, TypeNeurone.CACHEE, activationFunction);
    }
}
