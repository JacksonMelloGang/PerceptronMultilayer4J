package neuronal.couche;

public class CoucheCachee extends Couche {
    /**
     * Constructeur de la couche cachée
     * @param nombreNeurones Le nombre de neurones de la couche cachée à créer.
     * @param nombreEntreesParNeurone Le nombre d'entrées par neurone.
     */
    public CoucheCachee(int nombreNeurones, int nombreEntreesParNeurone) {
        super(nombreNeurones, nombreEntreesParNeurone, TypeCouche.CACHEE);
    }

    /**
     * Propage les entrées dans la couche cachée.
     *
     * <h1 style="color:red">/!\ Méthode Non Testé /!\</h1>
     *
     * @param inputs Les entrées à propager.
     * @return Les sorties de la couche cachée.
     */
    @Override
    public double[] propager(double[] inputs) {
        double[] outputs = new double[neurones.length];
        for (int i = 0; i < neurones.length; i++) {
            outputs[i] = neurones[i].activer(inputs);
        }
        return outputs;
    }
}
