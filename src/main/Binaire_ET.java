package main;


import fonctions.FonctionLineaire;
import fonctions.FonctionReLU;
import fonctions.FonctionSeuil;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Binaire_ET {

    public static void main(String[] args) {

        // Paramètres de l'IA
        int maxIterations = 1000;
        double learningRate = 0.1;

        // Couche d'entrée (Two Inputs: X and Y)
        CoucheEntree inputLayer = new CoucheEntree(2);
        inputLayer.addNeurone(new NeuroneEntree(2, new FonctionReLU()));
        inputLayer.addNeurone(new NeuroneEntree(2, new FonctionReLU()));

        // Couche de sortie (One Output: Z)
        CoucheSortie outputLayer = new CoucheSortie(1);
        outputLayer.addNeurone(new NeuroneSortie(1, new FonctionReLU()));

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

        ArrayList<double[]> inputslist = new ArrayList<double[]>();
        inputslist.add(new double[]{0, 0});
        inputslist.add(new double[]{0, 1});
        inputslist.add(new double[]{1, 0});
        inputslist.add(new double[]{1, 1});

        for (double[] inputs : inputslist) {
            double[] outputs = network.feedForward(inputs);
            System.out.println(String.format("Résultat pour entrées %s: %s", Arrays.toString(inputs), outputs[0]));
        }
    }
}
