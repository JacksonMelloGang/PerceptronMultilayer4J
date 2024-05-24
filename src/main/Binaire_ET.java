package main;


import neuronal.ReseauNeurone;
import neuronal.couche.CoucheCachee;
import neuronal.couche.CoucheEntree;
import neuronal.couche.CoucheSortie;
import echantillons.Echantillon;
import echantillons.LotEchantillon;

import java.util.ArrayList;
import java.util.Arrays;

public class Binaire_ET {

    public static void main(String[] args) {

        // Paramètres de l'IA
        int maxIterations = 7000;
        double learningRate = 0.1;

        // Création du réseau de neurones
        ReseauNeurone network = new ReseauNeurone(2, null, 1);

        // Batch
        LotEchantillon batch = new LotEchantillon();
        batch.addEchantillon(new Echantillon(new double[]{0, 0}, new double[]{0}));
        batch.addEchantillon(new Echantillon(new double[]{0, 1}, new double[]{0}));
        batch.addEchantillon(new Echantillon(new double[]{1, 0}, new double[]{0}));
        batch.addEchantillon(new Echantillon(new double[]{1, 1}, new double[]{1}));

        // Entrainement
        network.train(batch, maxIterations, learningRate);

        System.out.println("-----------------------------------");
        System.out.println("Entrainement terminé.");
        System.out.println("-----------------------------------");

        // Prédiction (test)
        ArrayList<double[]> inputslist = new ArrayList<double[]>();
        inputslist.add(new double[]{0, 0});
        inputslist.add(new double[]{0, 1});
        inputslist.add(new double[]{1, 0});
        inputslist.add(new double[]{1, 1});

        for (double[] inputs : inputslist) {
            double[] outputs = network.predire(inputs);
            double result = outputs[0];

            // si le résultat est supérieur à 0.5, on considère que c'est 1, sinon 0
            if(outputs[0] > 0.5){
                result = 1;
            } else {
                result = 0;
            }

            System.out.println(String.format("Résultat pour entrées %s: %s (%.5f)", Arrays.toString(inputs), result, outputs[0]));

        }
    }
}
