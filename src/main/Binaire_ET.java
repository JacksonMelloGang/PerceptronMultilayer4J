package main;


import fonctions.FonctionSigmoide;
import neuronal.ReseauNeurone;
import neuronal.couche.CoucheCache;
import neuronal.couche.CoucheEntree;
import neuronal.couche.CoucheSortie;
import neuronal.neurone.NeuroneCache;
import neuronal.neurone.NeuroneEntree;
import neuronal.neurone.NeuroneSortie;
import echantillons.Echantillon;
import echantillons.LotEchantillon;

import java.util.Collections;

public class Binaire_ET {

    public static void main(String[] args) {

        // Paramètres de l'IA
        int maxIterations = 1500;
        double learningRate = 0.01;

        // Couche d'entrée (Two Inputs: X and Y)
        CoucheEntree inputLayer = new CoucheEntree(2);
        inputLayer.addNeurone(new NeuroneEntree(2, new FonctionSigmoide()));
        inputLayer.addNeurone(new NeuroneEntree(2, new FonctionSigmoide()));

        // Couche cachée

        // Couche de sortie (One Output: Z)
        CoucheSortie outputLayer = new CoucheSortie(1);
        outputLayer.addNeurone(new NeuroneSortie(1, new FonctionSigmoide()));

        ReseauNeurone network = new ReseauNeurone();
        network.addCouche(inputLayer);
        network.addCouche(outputLayer);

        // Batch 1
        LotEchantillon batch = new LotEchantillon();
        batch.addEchantillon(new Echantillon(new double[]{0, 0}, new double[]{0}));
        batch.addEchantillon(new Echantillon(new double[]{0, 1}, new double[]{0}));
        batch.addEchantillon(new Echantillon(new double[]{1, 0}, new double[]{0}));
        batch.addEchantillon(new Echantillon(new double[]{1, 1}, new double[]{1}));


        network.train(batch, maxIterations, learningRate);



        System.out.println("-----------------------------------");
        System.out.println("Entrainement terminé.");
        System.out.println("-----------------------------------");



        // exemple cas d'utilisation avec 2 entrées de valeurs "aléatoire" 0,5 et 0,8
        // Créer un tableau pour les nouvelles entrées
        double[] newInputs = {1, 1};

        // Utiliser le réseau de neurones pour faire une prédiction
        double[] outputs = network.feedForward(newInputs);

        // Afficher les sorties
        for (double output : outputs) {
            System.out.println(output);
        }

        // Explication:

        // Le réseau de neurones a été entraîné pour effectuer l'opération ET.
        // Les entrées fournies sont 0,5 et 0,8.
        // la sortie attendue correspond à une prediction obtenu à partir du réseau de neurones.

        // Cette sortie correspond à la probabilité que les 2 entrées soient vraies (donc 1),

        // la sortie théorique doit être de 0 car0,5 et 0,8 ne sont pas tous les deux vrais. (=1)
    }
}
