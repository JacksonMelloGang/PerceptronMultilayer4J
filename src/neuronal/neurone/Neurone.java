package neuronal.neurone;

import fonctions.FonctionActivation;

/**
 * Classe représentant un neurone dans un réseau de neurones.
 */
public class Neurone {
    private FonctionActivation activateFunction;
    private double[] poids;
    private double seuil; // seuil
    private TypeNeurone neuronType;

    /**
     * Constructeur pour la classe Neurone.
     *
     * @param nombreEntrees Le nombre d'entrées pour le neurone.
     * @param neuronType Le type de neurone (par exemple, caché, sortie).
     * @param activationFunction La fonction d'activation à utiliser pour le neurone.
     */
    public Neurone(int nombreEntrees, TypeNeurone neuronType, FonctionActivation activationFunction) {
        poids = new double[nombreEntrees]; // Initialisation du tableau des poids

        // Initialisation des poids à des valeurs aléatoires
        poids = new double[nombreEntrees];
        for (int i = 0; i < nombreEntrees; i++) {
            poids[i] = Math.sqrt(2.0 / nombreEntrees) * (Math.random() - 0.5);
        }

        this.seuil = Math.random();
        this.neuronType = neuronType;
        this.activateFunction = activationFunction;
    }

    /**
     * Active le neurone en utilisant la fonction d'activation.
     *
     * @param entrees Les entrées pour le neurone.
     * @return Le résultat de l'activation du neurone.
     */
    public double activer(double[] entrees){
        double sommePonderee = 0;
        for (int i = 0; i < poids.length; i++) {
            sommePonderee += poids[i] * entrees[i];
        }

        return this.activateFunction.activer(sommePonderee + seuil);
    }

    /**
     * Récupère le type de neurone.
     *
     * @return Le type de neurone.
     */
    public TypeNeurone getNeuronType() {
        return this.neuronType;
    }

    /**
     * Récupère les poids du neurone.
     *
     * @return Les poids du neurone.
     */
    public double[] getPoids() {
        return poids;
    }

    /**
     * Met à jour les poids du neurone en fonction du taux d'apprentissage et de l'erreur.
     *
     * @param learningRate Le taux d'apprentissage.
     * @param error L'erreur.
     */
    public void updatePoids(double learningRate, double error) {
        // Mettre à jour chaque poids dans le neuronne
        for (int i = 0; i < poids.length; i++) {
            // Le nouveu poids = ancien poids + (taux d'apprentissage * erreur)
            poids[i] += learningRate * error;
        }

        // Mettre à jour le seuil
        seuil += learningRate * error;
    }
}