package neuronal.neurone;

import fonctions.FonctionActivation;

public class NeuroneSortie extends Neurone {


    public NeuroneSortie(int nombreEntree, FonctionActivation activationFunction) {
        super(nombreEntree, TypeNeurone.SORTIE, activationFunction);
    }

    private void afficherResultat(double result){
        System.out.println("Résultat de la couche de sortie : " + result);
    }
}