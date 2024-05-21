package neuronal.neurone;


import neuronal.activation.FonctionActivation;
import neuronal.activation.FonctionSigmoide;

import java.util.Random;

/**
 * Classe représentant un neurone dans un réseau de neurones.
 */
public class Neurone {

    private double[] poids;
    private double seuil; // seuil


    private double[] input;
    private double output;
    private double delta;

    private FonctionActivation fonctionActivation;

    private Random rdm = new Random();

    /**
     * Constructeur pour la classe Neurone.
     *
     * @param nombreEntrees Le nombre d'entrées pour le neurone.
     */
    public Neurone(int nombreEntrees) {
        poids = new double[nombreEntrees]; // Initialisation du tableau des poids

        // NOTE: on peut aussi utiliser rdm.nextGaussian() à la place de Math.random()

        // Initialisation des poids à des valeurs aléatoires
        poids = new double[nombreEntrees];
        for (int i = 0; i < nombreEntrees; i++) {
            poids[i] = Math.random();
        }

        this.seuil = Math.random();
        this.fonctionActivation = new FonctionSigmoide();
    }

    /**
     * Active le neurone en utilisant la fonction d'activation.
     *
     * @param entrees Les entrées pour le neurone.
     * @return Le résultat de l'activation du neurone.
     */
    public double activer(double[] entrees){
        this.input = entrees;

        double sommePonderee = seuil;
        for (int i = 0; i < poids.length; i++) {
            sommePonderee += poids[i] * entrees[i];
        }

        double output = fonctionActivation.activer(sommePonderee);
        this.output = output;

        return output;
    }

    private double sigmoid(double input) {
        return 1 / (1 + Math.exp(-input));
    }

    public void updateWeights(double[] inputs, double learningRate) {
        for (int i = 0; i < poids.length; i++) {
            poids[i] -= learningRate * delta * inputs[i];
        }
        seuil -= learningRate * delta;
    }


    /////////////////////////////
    // Getters and Setters
    /////////////////////////////

    public double[] getPoids() {
        return poids;
    }


    public double getSeuil() {
        return seuil;
    }

    public double[] getInput() {
        return input;
    }

    public double getOutput() {
        return output;
    }

    public double getDelta() {
        return delta;
    }

    public FonctionActivation getFonctionActivation() {
        return fonctionActivation;
    }

    public void setDelta(double delta) {
        this.delta = delta;
    }

    public void setSeuil(double seuil) {
        this.seuil = seuil;
    }
}