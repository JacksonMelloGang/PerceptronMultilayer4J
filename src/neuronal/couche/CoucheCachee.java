package neuronal.couche;

public class CoucheCachee extends Couche {
    public CoucheCachee(int nombreNeurones, int nombreEntreesParNeurone) {
        super(nombreNeurones, nombreEntreesParNeurone, TypeCouche.CACHEE);
    }

    @Override
    public double[] propager(double[] inputs) {
        double[] outputs = new double[neurones.length];
        for (int i = 0; i < neurones.length; i++) {
            outputs[i] = neurones[i].activer(inputs);
        }
        return outputs;
    }
}
