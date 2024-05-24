package neuronal;

import neuronal.neurone.Neurone;

import java.util.Arrays;

/**
 * Classe représentant un réseau de neurones pour prédire la mention au bac.
 * Classe fille de de {@link ReseauNeurone}.
 */
public class ReseauNeuroneMention extends ReseauNeurone {
    public ReseauNeuroneMention(int nbEntree, double[] coucheCachee, int nbSortie) {
        super(nbEntree, null, nbSortie);
    }

    /**
     *
     */
    @Override
    public double[] predire(double[] inputs) {
        int nbNeuroneSortie = getCouchesortie().getNbNeurones();
        Neurone[] neuroneSortie = getCouchesortie().getNeurones();

        // Calcul des sorties
        double[] outputs = new double[nbNeuroneSortie];
        for (int i = 0; i < neuroneSortie.length; i++) {
            outputs[i] = neuroneSortie[i].activer(inputs);
        }

        // Application de la fonction softmax sur les valeurs de sorties
        return softmax(outputs);
    }

    /**
     * Fonction pour convertir un résultat en probabilité
     * en utilisant la fonction softmax.
     *
     * @param inputs Les valeurs à passer dans la fonction softmax.
     * @return Les valeurs après avoir passé dans la fonction softmax.
     */

    public static double[] softmax(double[] inputs) {
        double max = Arrays.stream(inputs).max().orElse(Double.NEGATIVE_INFINITY);

        // Calcul des exponentielles des inputs en soustrayant le max pour la stabilité numérique
        double[] expScores = Arrays.stream(inputs).map(score -> Math.exp(score - max)).toArray();

        // Calcul de la somme des exponentielles
        double sumExpScores = Arrays.stream(expScores).sum();

        // Calcul des probabilités softmax
        return Arrays.stream(expScores).map(expScore -> expScore / sumExpScores).toArray();
    }

    /**
     * Fonction pour ajuster les poids du réseau de neurones.
     *
     * <h1 style="color:red">/!\ Méthode Non Testé /!\</h1>
     *
     * @param inputs Les entrées du réseau.
     * @param targets Les sorties attendues.
     */
    @Override
    public void backpropagate(double[] inputs, double[] targets) {
        double[] outputs = this.predire(inputs);
        double[] deltas = new double[getCouchesortie().getNeurones().length];

        for (int i = 0; i < getCouchesortie().getNeurones().length; i++) {
            double error = outputs[i] - targets[i];
            deltas[i] = error * outputs[i] * (1 - outputs[i]);
        }

        // Mise à jour des poids de la couche de sortie
        // Pour chaque neurone de la couche de sortie
        for (int i = 0; i < getCouchesortie().getNeurones().length; i++) {
            Neurone neuron = getCouchesortie().getNeurones()[i]; // On récupère le neurone
            double[] poids = neuron.getPoids();
            // Pour chaque poids du neurone, le mettre a jour
            for (int j = 0; j < poids.length; j++) {
                poids[j] -= getTauxApprentissage() * deltas[i] * inputs[j];
            }

            neuron.setSeuil(neuron.getSeuil() - getTauxApprentissage() * deltas[i]); // Mise à jour du seuil
            neuron.updateWeights(poids, getTauxApprentissage()); // Mise à jour des poids
        }
    }


}
