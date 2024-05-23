package neuronal.couche;

public class CoucheEntree extends Couche {

    /**
     * Constructeur de la couche d'entrée
     * @param nbNeurones Le nombre de neurones de la couche d'entrée a créer.
     */
    public CoucheEntree(int nbNeurones) {
        super(nbNeurones, 1, TypeCouche.ENTREE);
    }

    /**
     * Propage les entrées dans la couche d'entrée.
     *
     * <h1 style="color:red">/!\ Méthode Non Testé /!\</h1>
     * @param entrees Les entrées à propager.
     * @return Les sorties de la couche d'entrée.
     */
    @Override
    public double[] propager(double[] entrees) {
        return entrees;
    }
}
