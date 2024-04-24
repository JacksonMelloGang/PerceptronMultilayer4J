package neuronal.neurone;

import fonctions.FonctionActivation;

import java.util.Arrays;

public class NeuroneEntree extends Neurone {
    public NeuroneEntree(int nombreEntree, FonctionActivation activationFunction) {
        super(nombreEntree, TypeNeurone.ENTREE, activationFunction);
        System.out.println("Neurone d'entrée crée, type: " + TypeNeurone.ENTREE + " | poids: " + Arrays.toString(super.getPoids()));
    }
}
