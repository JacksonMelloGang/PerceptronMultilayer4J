package main;

import echantillons.Echantillon;
import echantillons.LotEchantillon;
import neuronal.ReseauNeurone;

import java.util.ArrayList;
import java.util.Arrays;

public class Binaire_NON {
    public static void main(String[] args) {

        // Paramètres de l'IA
        int maxIterations = 1000;
        double learningRate = 0.1;


        ReseauNeurone network = new ReseauNeurone(1, null);

        // Batch
        LotEchantillon batch = new LotEchantillon();
        batch.addEchantillon(new Echantillon(new double[]{0}, new double[]{1}));
        batch.addEchantillon(new Echantillon(new double[]{1}, new double[]{0}));

        network.train(batch, maxIterations, learningRate);

        System.out.println("-----------------------------------");
        System.out.println("Entrainement terminé.");
        System.out.println("-----------------------------------");

        ArrayList<double[]> inputslist = new ArrayList<double[]>();
        inputslist.add(new double[]{0});
        inputslist.add(new double[]{1});

        for (double[] inputs : inputslist) {
            double[] outputs = network.predire(inputs);
            double result = outputs[0];

            if(outputs[0] > 0.5){
                result = 1;
            } else {
                result = 0;
            }
            System.out.println(String.format("Résultat pour entrées %s: %s (%.5f)", Arrays.toString(inputs), result, outputs[0]));

        }
    }
}