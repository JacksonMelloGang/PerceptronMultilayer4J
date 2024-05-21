package main;

import echantillons.Echantillon;
import echantillons.LotEchantillon;
import neuronal.ReseauNeurone;

import java.util.ArrayList;
import java.util.Arrays;

public class Binaire_IMPLIQUE {

    public static void main(String[] args) {

        // Paramètres de l'IA
        int maxIterations = 2000;
        double learningRate = 0.5;

        int[] taille_couche_c = {2, 2}; // 2 Couche cachées, 2 neurones chacune

        ReseauNeurone network = new ReseauNeurone(2, taille_couche_c);

        // Batch 1
        LotEchantillon batch = new LotEchantillon();
        batch.addEchantillon(new Echantillon(new double[]{0, 0}, new double[]{1}));
        batch.addEchantillon(new Echantillon(new double[]{0, 1}, new double[]{0}));
        batch.addEchantillon(new Echantillon(new double[]{1, 0}, new double[]{1}));
        batch.addEchantillon(new Echantillon(new double[]{1, 1}, new double[]{1}));

        network.train(batch, maxIterations, learningRate);

        System.out.println("-----------------------------------");
        System.out.println("Entrainement terminé.");
        System.out.println("-----------------------------------");

        ArrayList<double[]> inputslist = new ArrayList<double[]>();
        inputslist.add(new double[]{0, 0});
        inputslist.add(new double[]{0, 1});
        inputslist.add(new double[]{1, 0});
        inputslist.add(new double[]{1, 1});

        for (double[] inputs : inputslist) {
            double[] outputs = network.predire(inputs);
            System.out.println(String.format("Résultat pour entrées %s: %s", Arrays.toString(inputs), outputs[0]));
        }
    }
}
