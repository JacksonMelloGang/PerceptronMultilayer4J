package neuronal;

import echantillons.Echantillon;
import echantillons.LotEchantillon;
import neuronal.couche.CoucheCachee;
import neuronal.couche.CoucheEntree;
import neuronal.couche.CoucheSortie;
import neuronal.neurone.Neurone;
import neuronal.neurone.NeuroneCache;

import java.util.ArrayList;

/**
 * Classe représentant un réseau de neurones.
 */
public class ReseauNeurone {

    // Si le réseau a des couches cachées
    private boolean aCoucheCachee;

    // Couche de sortie
    private CoucheSortie couchesortie;

    // Couche d'entrée
    private CoucheEntree coucheentree;

    // Liste des couches cachees
    private ArrayList<CoucheCachee> couchecaches;

    private double tauxApprentissage;
    /**
     * Constructeur du réseau de neurones.
     * Initialise la couche d'entrée, les couches cachées et la couche de sortie.
     *
     * @param tailleEntree La taille de la couche d'entrée.
     * @param tailleCoucheCachees Les tailles des couches cachées.
     */
    public ReseauNeurone(int tailleEntree, int[] tailleCoucheCachees){
        this.coucheentree = new CoucheEntree(tailleEntree);

        if(tailleCoucheCachees != null){
            aCoucheCachee = true;

            // créer les couches cachées, on récupere la taille de la 1ere couche pour définir la taille des couches cachees
            this.couchecaches = new ArrayList<CoucheCachee>();
            int taillePrecedente = tailleEntree;
            for(int i = 0; i < tailleCoucheCachees.length; i++){
                CoucheCachee couche_c = new CoucheCachee(tailleCoucheCachees[i], taillePrecedente);
                couchecaches.add(couche_c);
                taillePrecedente = tailleCoucheCachees[i];
            }
            this.couchesortie = new CoucheSortie(taillePrecedente);
        } else {
            // Si on a pas de couches cachées a créer
            this.couchesortie = new CoucheSortie(tailleEntree);
        }
    }


    /**
     * Ajoute une couche au réseau.
     *
     * @param layer La couche à ajouter.
     */
    public void addCouche(CoucheCachee layer) {
        couchecaches.add(layer);
    }

    /**
     * Prédit la sortie pour les entrées données.
     *
     * @param inputs Les entrées du réseau.
     * @return La sortie du réseau.
     */
    public double[] predire(double[] inputs){
        if (aCoucheCachee) {
            double[] currentInputs = inputs;
            for (CoucheCachee hiddenLayer : couchecaches) {
                for (Neurone neuron : hiddenLayer.getNeurones()) {
                    neuron.activer(currentInputs);
                }
                currentInputs = hiddenLayer.getSorties();
            }
            for (Neurone neuron : couchesortie.getNeurones()) {
                neuron.activer(currentInputs);
            }
        } else {
            for (Neurone neuron : couchesortie.getNeurones()) {
                neuron.activer(inputs);
            }
        }
        return couchesortie.getSorties();
    }

    /**
     * Entraîne le réseau neuronal avec un ensemble d'échantillons en utilisant l'algorithme de rétropropagation.
     *
     * @param lot L'ensemble d'échantillons à utiliser pour l'entraînement.
     * @param maxIterations Le nombre maximum d'itérations d'entraînement.
     * @param tauxApprentissage Le taux d'apprentissage pour la mise à jour des poids du réseau.
     */
    public void train(LotEchantillon lot, int maxIterations, double tauxApprentissage) {
        this.tauxApprentissage = tauxApprentissage;

        // pour chaque itération, on parcourt tous les échantillons du lot, on les fait passer dans le réseau, on calcule l'erreur et on met à jour les poids.
        for (int iteration = 0; iteration < maxIterations; iteration++) {
            System.out.println("\n---------------------------------------------------");
            System.out.println("Itération : n°" + iteration);
            double erreurTotal = 0;

            for (Echantillon sample : lot.getEchantillons()) {

                // Afficher le numéro de l'échantillon
                //System.out.println("Echantillon " + lot.getEchantillons().indexOf(sample) + ": " + sample.toString());

                // Faire passer les entrées de l'échantillon à travers le réseau
                double[] inputs = sample.getEntrees();
                double[] targets = sample.getResultatAttendus();

                double[] outputs = predire(inputs);
                backpropagate(inputs, targets);
                erreurTotal += Math.pow(outputs[0] - targets[0], 2);
            }

            double cout = erreurTotal / lot.getEchantillons().size();
            System.out.println("Coût : " + cout);
        }
    }

    /**
     * Effectue la rétropropagation sur le réseau.
     * Calcule l'erreur pour chaque neurone et met à jour les poids en conséquence.
     *
     * @param inputs Les entrées du réseau.
     * @param targets Les sorties attendues.
     */
    private void backpropagate(double[] inputs, double[] targets) {
        // passer les entrées a travers le réseau
        double[] outputs = predire(inputs);

        // on récupere la sortie du réseau et on compare la différence entre le résultat attendu et le résultat obtenu
        double sortie = outputs[0];
        double erreurSortie = sortie - targets[0];

        // on calcule le delta de la couche de sortie a partir de la dérivé de la fonction de cout (MSE) et on applique le delta sur le neurone de sortie
        double sortieDelta = erreurSortie * sortie * (1 - sortie);
        couchesortie.getNeurones()[0].setDelta(sortieDelta);

        // Si on a des couches cachées
        if(aCoucheCachee){

            // on calcule les deltas de la derniere couche cachées
            double deltas[] = new double[couchecaches.getLast().getNbNeurones()];
            for(int i = 0; i < couchecaches.get(couchecaches.size() - 1).getNbNeurones(); i++){
                Neurone neurone_c = couchecaches.get(couchecaches.size() - 1).getNeurones()[i];
                double erreur_c = sortieDelta * couchesortie.getNeurones()[0].getPoids()[i];
                double delta_c = erreur_c * neurone_c.getOutput() * (1 - neurone_c.getOutput());
                neurone_c.setDelta(delta_c);
                deltas[i] = delta_c;
            }

            // on calcule les deltas des couches cachées restantes (jusqu'a la couche d'entree (EXCLU))
            for (int layerIndex = couchecaches.size() - 2; layerIndex >= 0; layerIndex--) {
                CoucheCachee coucheCachee = couchecaches.get(layerIndex);
                CoucheCachee coucheSuivante = couchecaches.get(layerIndex + 1);
                double[] nextDeltas = new double[coucheCachee.getNeurones().length];

                for (int i = 0; i < coucheCachee.getNeurones().length; i++) {
                    Neurone neurone_c = coucheCachee.getNeurones()[i];
                    double erreurCachee = 0.0;
                    for (int j = 0; j < coucheSuivante.getNeurones().length; j++) {
                        erreurCachee += coucheSuivante.getNeurones()[j].getDelta() * coucheSuivante.getNeurones()[j].getPoids()[i];
                    }

                    double deltaCachee = erreurCachee * neurone_c.getOutput() * (1 - neurone_c.getOutput()); // derive sigmoide TODO: changer pour d'autres fonctions d'activation
                    neurone_c.setDelta(deltaCachee);
                    nextDeltas[i] = deltaCachee;
                }

                deltas = nextDeltas;
            }

            // on met a jour les poids des neurones
            for (int layer_index = couchecaches.size() - 1; layer_index >= 0; layer_index--) {
                CoucheCachee coucheCachee = couchecaches.get(layer_index);
                double[] coucheEntrees = layer_index == 0 ? inputs : couchecaches.get(layer_index - 1).getSorties();

                for (Neurone neurone : coucheCachee.getNeurones()) {
                    neurone.updateWeights(coucheEntrees, tauxApprentissage);
                }
            }

            double[] finalSortieCachee = couchecaches.get(couchecaches.size() - 1).getSorties();
            couchesortie.getNeurones()[0].updateWeights(finalSortieCachee, tauxApprentissage);
        } else {

            // Si on a pas de couches cachées
            // TODO: prendre cas si pas de couche cachées
            couchesortie.getNeurones()[0].updateWeights(inputs, tauxApprentissage);
        }
    }

}