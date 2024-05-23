package neuronal;

import echantillons.Echantillon;
import echantillons.LotEchantillon;
import neuronal.couche.CoucheCachee;
import neuronal.couche.CoucheEntree;
import neuronal.couche.CoucheSortie;
import neuronal.neurone.Neurone;

import java.util.ArrayList;

/**
 * Classe représentant un réseau de neurones.
 * Un réseau de neurones est composé de plusieurs couches de neurones.
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

    // Taux d'apprentissage
    private double tauxApprentissage;
    /**
     * Constructeur du réseau de neurones.
     * Initialise la couche d'entrée, les couches cachées et la couche de sortie.
     *
     * @param tailleEntree La taille de la couche d'entrée (nombre de neurone dans la couche d'entrée).
     * @param tailleCoucheCachees Les tailles des couches cachées.
     */
    public ReseauNeurone(int tailleEntree, int[] tailleCoucheCachees){
        this.coucheentree = new CoucheEntree(tailleEntree); // On créer la couche d'entrée

        // Si l'on souhaite créer des couches cachées
        if(tailleCoucheCachees != null){
            aCoucheCachee = true;

            // créer les couches cachées, on récupere la taille des neurones de la couche d'entrées pour la première couche cachée
            this.couchecaches = new ArrayList<CoucheCachee>();
            int tailleNeuronePrecedente = tailleEntree;
            for(int i = 0; i < tailleCoucheCachees.length; i++){
                CoucheCachee couche_c = new CoucheCachee(tailleCoucheCachees[i], tailleNeuronePrecedente);
                couchecaches.add(couche_c);
                tailleNeuronePrecedente = tailleCoucheCachees[i];
            }

            // On crée la couche de sortie
            this.couchesortie = new CoucheSortie(tailleNeuronePrecedente);
        } else {
            // Si on a pas de couches cachées a créer, on crée directement la couche de sortie
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
     * @param inputs Les valeurs d'entrées que l'on fournit au réseau.
     * @return La sortie du réseau.
     */
    public double[] predire(double[] inputs){
        // S'il y a des couches cachées, on fait passer les entrées à travers les couches cachées
        if (aCoucheCachee) {
            double[] currentInputs = inputs;
            // Pour chaque couche cachée, on active les neurones
            for (CoucheCachee coucheCachee : couchecaches) {
                for (Neurone neuron : coucheCachee.getNeurones()) {
                    neuron.activer(currentInputs);
                }

                // on récupère les sorties de la dernière couche cachée pour les passer à la couche suivante
                currentInputs = coucheCachee.getSorties();
            }

            // Pour chaque neurone dans la couche de sortie, on active le neurone
            for (Neurone neurone : couchesortie.getNeurones()) {
                neurone.activer(currentInputs);
            }
        } else {
            // Si on a pas de couches cachées, pour chaque neuronne dans la couche de sortie, on active le neurone
            for (Neurone neurone : couchesortie.getNeurones()) {
                neurone.activer(inputs);
            }
        }

        // On retourne les sorties de la couche de sortie
        return couchesortie.getSorties();
    }

    /**
     * Entraîne le réseau neuronal avec un ensemble d'échantillons en utilisant l'algorithme de rétropropagation.
     *
     * Pour chaque lot d'échantillon, on fait passer les entrées à travers le réseau,
     * on calcule l'erreur et on met à jour les poids du réseau.
     *
     * @param lot L'ensemble d'échantillons à utiliser pour l'entraînement.
     * @param maxIterations Le nombre maximum d'itérations d'entraînement.
     * @param tauxApprentissage Le taux d'apprentissage pour la mise à jour des poids du réseau.
     */
    public void train(LotEchantillon lot, int maxIterations, double tauxApprentissage) {
        this.tauxApprentissage = tauxApprentissage; // Initialisation du taux d'apprentissage

        // pour chaque itération, on parcourt tous les échantillons du lot, on les fait passer dans le réseau, on calcule l'erreur et on met à jour les poids.
        for (int iteration = 0; iteration < maxIterations; iteration++) {
            System.out.println("\n---------------------------------------------------");
            System.out.println("Itération : n°" + iteration);
            double erreurTotal = 0; // Initialisation de l'erreur totale

            // Pour chaque échantillon dans le lot
            for (Echantillon echantillon : lot.getEchantillons()) {

                // Afficher le numéro de l'échantillon
                //System.out.println("Echantillon " + lot.getEchantillons().indexOf(echantillon) + ": " + echantillon.toString());

                // Récupérer les entrées et le résultat attendues
                double[] inputs = echantillon.getEntrees();
                double[] target = echantillon.getResultatAttendus();

                // Prédire la sortie pour les entrées données
                double[] outputs = predire(inputs);

                /*Rétropropagation du gradient (apprentissage par pattern)*/
                backpropagate(inputs, target);

                // Calcul de l'erreur quadratique moyenne (MSE), on ajoute le résultat de l'erreur à erreurTotale
                erreurTotal += Math.pow(outputs[0] - target[0], 2); // erreur quadratique moyenne (MSE)
            }

            // Calcule le coût moyen pour chaque itération
            double cout = erreurTotal / lot.getEchantillons().size();
            System.out.println("Coût : " + cout);
        }
    }

    /**
     * Effectue la rétropropagation sur le réseau.<br>
     * Calcule l'erreur pour chaque neurone et met à jour les poids en conséquence.
     *
     * <p style="color: red; font-size:14px">RAPPEL: LE DELTA SERVIRA POUR AJUSTER LE NEURONE</p><br>
     * CF {@link Neurone#updateWeights(double[], double)}
     *
     * @param inputs Les entrées du réseau.
     * @param targets Les sorties attendues.
     */
    private void backpropagate(double[] inputs, double[] targets) {
        // passer les entrées a travers le réseau
        double[] outputs = predire(inputs);

        // on récupere la sortie du réseau et on compare la différence entre le résultat obtenu et le résultat attendue
        double sortie = outputs[0]; // sortie du réseau
        double erreurSortie = sortie - targets[0]; // dérivié de la fonction de cout (MSE) (la différence entre sortie réelle et sortie attendue)

        // on calcule le delta de la couche de sortie a partir de la dérivé de la fonction de cout (MSE) et on applique le delta sur le neurone de sortie
        // sortie * (1 - sortie) => dérivée de la fonction d'activation sigmoide
        // erreurSortie => dérivée de la fonction de cout (MSE)
        double sortieDelta = erreurSortie * sortie * (1 - sortie); // utilise la dérivée de la fonction de coût (MSE) pour calculer le delta pour la couche de sortie.
        couchesortie.getNeurones()[0].setDelta(sortieDelta); // on définit le delta du neurone de sortie

        // Si des couches cachées existent (on part de la fin vers le début)
        if(aCoucheCachee){
            // TODO: check si on peut fusionner les 2 bouts de codes [175-183] et [185-204]

            // pour chaque neurone de la dernière couche cachée, on calcule le delta
            for(int i = 0; i < couchecaches.get(couchecaches.size() - 1).getNbNeurones(); i++){
                Neurone neurone_c = couchecaches.get(couchecaches.size() - 1).getNeurones()[i]; // on récupère le neurone de la couche cachée
                double erreur_c = sortieDelta * couchesortie.getNeurones()[0].getPoids()[i]; // on calcule l'erreur du neurone
                double delta_c = erreur_c * neurone_c.getFonctionActivation().derivee(neurone_c.getOutput()); // calcul du delta
                neurone_c.setDelta(delta_c); // on définit le delta du neurone de la couche cachée
            }

            // on calcule les deltas des couches cachées restantes (jusqu'a la couche d'entree (EXCLU))
            for (int layerIndex = couchecaches.size() - 2; layerIndex >= 0; layerIndex--) {
                CoucheCachee coucheCachee = couchecaches.get(layerIndex); // on récupère la couche cachée
                CoucheCachee coucheSuivante = couchecaches.get(layerIndex + 1); // on récupère la couche précédente (en partant de la fin donc la couche actuel +1 )

                // pour chaque neurone de la couche cachée actuelle, on calcule le delta
                for (int i = 0; i < coucheCachee.getNeurones().length; i++) {
                    Neurone neurone_c = coucheCachee.getNeurones()[i]; // on récupère le neurone de la couche cachée
                    double erreurCachee = 0.0;
                    for (int j = 0; j < coucheSuivante.getNeurones().length; j++) {
                        erreurCachee += coucheSuivante.getNeurones()[j].getDelta() * coucheSuivante.getNeurones()[j].getPoids()[i];
                    }

                    double deltaCachee = erreurCachee * neurone_c.getFonctionActivation().derivee(neurone_c.getOutput()); // calcul du gradient de l'erreur par rapport aux poids du réseau
                    neurone_c.setDelta(deltaCachee); // on définit le delta du neurone de la couche cachée
                }
            }

            // on met a jour les poids des neurones
            for (int layer_index = couchecaches.size() - 1; layer_index >= 0; layer_index--) {
                CoucheCachee coucheCachee = couchecaches.get(layer_index); // on récupère la couche cachée
                double[] coucheEntrees = layer_index == 0 ? inputs : couchecaches.get(layer_index - 1).getSorties(); // traduction: si on est a la première couche cachée, on prend les entrées (inputs), sinon on prend les sorties de la couche précédente

                // pour chaque neurone de la couche cachée, on met a jour les poids
                for (Neurone neurone : coucheCachee.getNeurones()) {
                    neurone.updateWeights(coucheEntrees, tauxApprentissage);
                }
            }

            // on met a jour les poids de la couche de sortie
            double[] finalSortieCachee = couchecaches.get(couchecaches.size() - 1).getSorties();
            for(Neurone neurone : couchesortie.getNeurones()){
                neurone.updateWeights(finalSortieCachee, tauxApprentissage);
            }
        } else {
            // Si on a pas de couches cachées, on met a jour les neuronnes de sorties directement
            // parcourt la liste des neurones dans la couche de sortie et met a jour les poids
            for(Neurone neurone : couchesortie.getNeurones()){
                neurone.updateWeights(inputs, tauxApprentissage);
            }
        }
    }

}