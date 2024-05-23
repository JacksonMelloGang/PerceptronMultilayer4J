package main;


import echantillons.Echantillon;
import echantillons.LotEchantillon;
import neuronal.ReseauNeurone;

import java.util.ArrayList;
import java.util.Arrays;

public class Binaire_N {

    public static void main(String[] args) {

        // Paramètres de l'IA
        int maxIterations = 150;
        double tauxApprentissage = 0.2;

        ReseauNeurone network = new ReseauNeurone(4, null);

        // Batch
        LotEchantillon batch = new LotEchantillon();
        batch.addEchantillon(new Echantillon(new double[]{0, 0, 1, 0}, new double[]{0}));
        batch.addEchantillon(new Echantillon(new double[]{0, 1, 0, 0}, new double[]{0}));
        batch.addEchantillon(new Echantillon(new double[]{1, 0, 1, 1}, new double[]{0}));
        batch.addEchantillon(new Echantillon(new double[]{1, 1, 0, 1}, new double[]{1}));

        network.train(batch, maxIterations, tauxApprentissage);

        System.out.println("-----------------------------------");
        System.out.println("Entrainement terminé.");
        System.out.println("-----------------------------------");

        ArrayList<double[]> inputslist = new ArrayList<double[]>();
        inputslist.add(new double[]{0, 0, 0, 1});
        inputslist.add(new double[]{0, 1, 0, 0});
        inputslist.add(new double[]{1, 0, 1, 1});
        inputslist.add(new double[]{1, 1, 0, 0});

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
