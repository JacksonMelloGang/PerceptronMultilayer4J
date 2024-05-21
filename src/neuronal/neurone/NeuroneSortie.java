package neuronal.neurone;


public class NeuroneSortie extends Neurone {

    public NeuroneSortie(int nombreEntree) {
        super(nombreEntree);
    }

    private void afficherResultat(double result){
        System.out.println("Résultat de la couche de sortie : " + result);
    }
}