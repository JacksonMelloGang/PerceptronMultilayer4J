package neuronal.couche;

import neuronal.neurone.Neurone;

/**
 * Classe représentant une couche dans un réseau de neurones.<br>
 * Représente la classe mère pour {@link CoucheCachee}, {@link CoucheSortie} et {@link CoucheEntree}.
 */
public abstract class Couche {
    protected Neurone[] neurones; // Les neurones de la couche
    protected int nbNeurones; // Le nombre de neurones de la couche
    protected TypeCouche typeCouche; // Le type de la couche (ENTREE, CACHEE, SORTIE)

    /**
     * Constructeur de la classe Couche.
     *
     * On instancie ici les neurones de la couche, et définissions les différents attributs.
     *
     * @param numNeurons Le nombre de neurones de la couche.
     * @param numInputsPerNeuron Le nombre d'entrées par neurone.
     * @param typeCouche Le type de la couche.
     */
    public Couche(int numNeurons, int numInputsPerNeuron, TypeCouche typeCouche) {
        neurones = new Neurone[numNeurons];
        this.nbNeurones = numNeurons;
        this.typeCouche = typeCouche;
        for (int i = 0; i < numNeurons; i++) {
            neurones[i] = new Neurone(numInputsPerNeuron);
        }
    }

    /**
     * Retourne les sorties de la couche.
     * @return Les sorties de la couche.
     */
    public double[] getSorties() {
        double[] outputs = new double[neurones.length];
        for (int i = 0; i < neurones.length; i++) {
            outputs[i] = neurones[i].getOutput();
        }
        return outputs;
    }

    public abstract double[] propager(double[] inputs);

    /////////////////////////////
    // Getters
    /////////////////////////////

    public Neurone[] getNeurones() {
        return neurones;
    }

    public int getNbNeurones() {
        return nbNeurones;
    }

    public TypeCouche getTypeCouche() {
        return typeCouche;
    }
}