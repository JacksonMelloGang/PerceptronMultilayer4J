package neuronal.couche;

public class CoucheSortie extends Couche {

    public CoucheSortie(int nombreEntreesParNeurone) {
        super(1, nombreEntreesParNeurone, TypeCouche.SORTIE);
    }

    @Override
    public double[] propager(double[] entrees) {
        double[] outputs = new double[1];
        outputs[0] = this.getNeurones()[0].activer(entrees);
        return outputs;
    }
}
