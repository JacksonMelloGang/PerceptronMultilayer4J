package neuronal.couche;

public class CoucheSortie extends Couche {

    /**
     * Constructeur de la couche de sortie
     * @param nombreEntreesParNeurone Le nombre d'entrées par neurone.
     */
    public CoucheSortie(int nombreNeuronCree, int nombreEntreesParNeurone) {
        super(nombreNeuronCree, nombreEntreesParNeurone, TypeCouche.SORTIE);
    }

    /**
     * Propage les entrées dans la couche de sortie.
     *
     * <h1 style="color:red">/!\ Méthode Non Testé /!\</h1>
     *
     * @param entrees Les entrées à propager.
     * @return Les sorties de la couche de sortie.
     */
    @Override
    public double[] propager(double[] entrees) {
        double[] outputs = new double[1];
        outputs[0] = this.getNeurones()[0].activer(entrees);
        return outputs;
    }
}
