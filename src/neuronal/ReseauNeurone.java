package neuronal;

import neuronal.couche.Couche;
import neuronal.neurone.Neurone;
import echantillons.Echantillon;
import echantillons.LotEchantillon;

import java.util.ArrayList;

/**
 * Classe représentant un réseau de neurones.
 */
public class ReseauNeurone {

    // Liste des couches du réseau
    private ArrayList<Couche> layers;

    /**
     * Constructeur par défaut.
     * Initialise une nouvelle liste de couches.
     */
    public ReseauNeurone() {
        layers = new ArrayList<>();
    }

    /**
     * Ajoute une couche au réseau.
     *
     * @param layer La couche à ajouter.
     */
    public void addLayer(Couche layer) {
        layers.add(layer);
    }

    /**
     * Alimente le réseau avec des entrées et retourne les sorties.
     *
     * @param inputs Les entrées à fournir au réseau.
     * @return Les sorties du réseau.
     */
    public double[] feedForward(double[] inputs) {
        double[] sorties = inputs;
        for (Couche layer : layers) {
            sorties = layer.calculerSortie(sorties);
        }

        return sorties;
    }

    /**
     * Entraîne le réseau neuronal avec un lot d'échantillons en utilisant l'algorithme de rétropropagation du gradient.
     *
     * @param lot Le lot d'échantillons à utiliser pour l'entraînement.
     * @param maxIterations Le nombre maximum d'itérations d'entraînement.
     * @param tauxApprentissage Le taux d'apprentissage pour la mise à jour des poids du réseau.
     */
    public void train(LotEchantillon lot, int maxIterations, double tauxApprentissage) {

        // pour chaque itération, on parcourt tous les échantillons du lot, on les fait passer dans le réseau, on calcule l'erreur et on met à jour les poids.
        for (int iteration = 0; iteration < maxIterations; iteration++) {
            System.out.println("\n---------------------------------------------------");
            System.out.println("Itération : n°" + iteration);
            for (Echantillon sample : lot.getEchantillons()) {
                // Afficher le numéro de l'échantillon
                System.out.println("Echantillon " + lot.getEchantillons().indexOf(sample) + ": " + sample.toString());

                // Faire passer les entrées de l'échantillon à travers le réseau
                double[] inputs = sample.getEntrees();
                double[] targets = sample.getResultatAttendus();
                double[] outputs = feedForward(inputs);

                // Calculer l'erreur pour chaque sortie (l'erreur est la différence entre la sortie attendue et la sortie réelle, donc une erreur négativ
                // proche de 0 est correcte (à condition que ce n'est pas trop grand)
                double[] errors = new double[outputs.length];
                for (int i = 0; i < outputs.length; i++) {
                    errors[i] = targets[i] - outputs[i];
                }

                // Calculer l'erreur quadratique moyenne (MSE)
                double mse = 0;
                for (double error : errors) {
                    mse += error * error;
                }
                mse /= errors.length;

                System.out.println("MSE: " + mse);

                // Propager l'erreur en arrière à travers le réseau et mettre à jour les poids
                backpropagate(errors, tauxApprentissage);
            }
        }
    }

    /**
     * Propage l'erreur en arrière à travers le réseau et met à jour les poids.
     *
     * @param errors Les erreurs à propager.
     * @param learningRate Le taux d'apprentissage pour la mise à jour des poids.
     *
     */
    private void backpropagate(double[] errors, double learningRate) {
        // Parcourir toutes les couches du réseau en partant de la dernière
        for (int i = layers.size() - 1; i >= 0; i--) {
            Couche layer = layers.get(i);
            double[] nextLayerErrors = new double[layer.getNbNeurones()];

            // Parcourir tous les neurones de la couche
            for (int j = 0; j < layer.getNbNeurones(); j++) {
                Neurone neuron = layer.getNeurones()[j];

                // si c'est la couche de sortie, on met à jour les poids
                if (i == layers.size() - 1) {
                    neuron.updatePoids(learningRate, errors[j]);
                } else {
                    // si ce n'est pas la couche de sortie, on propage l'erreur à la couche précédente
                    for (int k = 0; k < layers.get(i + 1).getNbNeurones(); k++) {
                        if (j < layers.get(i + 1).getNeurones()[k].getPoids().length && k < errors.length) {
                            nextLayerErrors[j] += layers.get(i + 1).getNeurones()[k].getPoids()[j] * errors[k];
                        }
                    }

                    double output = neuron.activer(layer.calculerSortie(layer.getNeurones()[j].getPoids())); // récupérer la sortie du neurone
                    double gradient = nextLayerErrors[j] * output * (1 - output); // dérivé de la fonction d'activation (sigmoide)
                    neuron.updatePoids(learningRate, gradient);
                }
            }
            errors = nextLayerErrors;
        }
    }
}