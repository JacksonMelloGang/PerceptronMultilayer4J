package neuronal.couche;

public class CoucheEntree extends Couche {

    public CoucheEntree(int nbNeurones) {
        super(nbNeurones, 1, TypeCouche.ENTREE);
    }

    @Override
    public double[] propager(double[] entrees) {
        return entrees;
    }
}
