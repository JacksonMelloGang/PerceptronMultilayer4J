package neuronal.couche;

import neuronal.neurone.Neurone;

public abstract class Couche {
    protected Neurone[] neurones;
    protected int nbNeurones;

    public Couche(int numNeurons, int numInputsPerNeuron, TypeCouche typeCouche) {
        neurones = new Neurone[numNeurons];
        this.nbNeurones = numNeurons;
        for (int i = 0; i < numNeurons; i++) {
            neurones[i] = new Neurone(numInputsPerNeuron);
        }
    }

    public double[] getSorties() {
        double[] outputs = new double[neurones.length];
        for (int i = 0; i < neurones.length; i++) {
            outputs[i] = neurones[i].getOutput();
        }
        return outputs;
    }

    public abstract double[] propager(double[] inputs);

    public Neurone[] getNeurones() {
        return neurones;
    }

    public int getNbNeurones() {
        return nbNeurones;
    }
}