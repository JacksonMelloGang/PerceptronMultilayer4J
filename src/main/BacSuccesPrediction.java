package main;

//import neuronal.ReseauNeuroneMention;
import echantillons.Echantillon;
import echantillons.LotEchantillon;
import neuronal.ReseauNeurone;
import neuronal.ReseauNeuroneMention;

import java.util.ArrayList;
import java.util.Arrays;

public class BacSuccesPrediction {

    /**
     * Note: Il manque le fichier "notes Farfaraway", donc pas possible de faire la 3.2.a ni la 3.1.b (je crois)
     */


    public static void main(String[] args) {

        // Paramètres de l'IA
        int maxIterations = 1500;
        double learningRate = 0.1;

        // Création du réseau de neurones
        ReseauNeurone reseauBac = new ReseauNeurone(5, null, 1);

        // Batch
        LotEchantillon batch = new LotEchantillon();
        batch.addEchantillon(new Echantillon(new double[]{0, 0, 0, 0, 1}, new double[]{0}));
        batch.addEchantillon(new Echantillon(new double[]{1, 1, 0, 0, 0}, new double[]{0}));
        batch.addEchantillon(new Echantillon(new double[]{0, 0, 1, 0, 1}, new double[]{0}));
        batch.addEchantillon(new Echantillon(new double[]{1, 1, 0, 1, 1}, new double[]{1}));
        batch.addEchantillon(new Echantillon(new double[]{0, 0, 1, 0, 0}, new double[]{0}));


        // Entrainement
        reseauBac.train(batch, maxIterations, learningRate);

        System.out.println("-----------------------------------");
        System.out.println("Entrainement terminé.");
        System.out.println("-----------------------------------");

        // Prédiction (test)
        ArrayList<double[]> inputslist = new ArrayList<double[]>();
        inputslist.add(new double[]{0, 0, 0, 0, 1});
        inputslist.add(new double[]{0, 1, 0, 0, 0});
        inputslist.add(new double[]{0, 0, 0, 0, 0});
        inputslist.add(new double[]{0, 0, 0, 0, 0});
        inputslist.add(new double[]{1, 0, 0, 1, 1});

        for (double[] inputs : inputslist) {
            double[] outputs = reseauBac.predire(inputs);
            double result = outputs[0];
            // si le résultat est supérieur à 0.5, on considère que c'est 1, sinon 0
            if(outputs[0] > 0.5){
                result = 1;
            } else {
                result = 0;
            }

            System.out.println(String.format("Résultat pour entrées %s: %s (%.5f)", Arrays.toString(inputs), result, outputs[0]));
        }






        // Paramètres de l'IA
        int maxIterations2 = 10;
        double learningRate2 = 0.1;

        ReseauNeurone reseauMention = new ReseauNeuroneMention(5, null, 3);
        // Batch 2
        LotEchantillon batch2 = new LotEchantillon();
        batch.addEchantillon(new Echantillon(new double[]{0, 0, 0, 0, 1}, new double[]{0}));
        batch.addEchantillon(new Echantillon(new double[]{1, 1, 0, 0, 0}, new double[]{0}));
        batch.addEchantillon(new Echantillon(new double[]{0, 0, 1, 0, 1}, new double[]{0}));
        batch.addEchantillon(new Echantillon(new double[]{1, 1, 0, 1, 1}, new double[]{1}));
        batch.addEchantillon(new Echantillon(new double[]{0, 0, 1, 0, 0}, new double[]{0}));

        // Entrainement 2
        reseauMention.train(batch2, maxIterations2, learningRate2);

        System.out.println("-----------------------------------");
        System.out.println("Entrainement terminé.");
        System.out.println("-----------------------------------");

        // Prédiction 2 (test)
        ArrayList<double[]> inputslist2 = new ArrayList<double[]>();
        inputslist2.add(new double[]{0, 0, 0, 0, 1});
        inputslist2.add(new double[]{0, 1, 0, 0, 0});
        inputslist2.add(new double[]{0, 0, 0, 0, 0});
        inputslist2.add(new double[]{0, 0, 0, 0, 0});

        for (double[] inputs : inputslist2) {
            double[] outputs = reseauMention.predire(inputs);
            double result = outputs[0];

            System.out.println(String.format("Résultat pour entrées %s: %s (%.5f)", Arrays.toString(inputs), result, outputs[0]));
        }

    }

}
