package main;


import fonctions.FonctionSigmoide;
import neuronal.ReseauNeurone;
import neuronal.couche.CoucheEntree;
import neuronal.couche.CoucheSortie;
import neuronal.neurone.NeuroneEntree;
import neuronal.neurone.NeuroneSortie;
import echantillons.Echantillon;
import echantillons.LotEchantillon;

public class Binaire_ET {

    public static void main(String[] args) {

        // Settings for the neural network
        int maxIterations = 1500;
        double learningRate = 0.001;

        // Définition de l'opération ET
        int[][] examples = {{0, 0}, {0, 1}, {1, 0}, {1, 1}};
        int[] expectedOutput = {0, 0, 0, 1};

        // Input Layer (Two Inputs: X and Y)
        CoucheEntree inputLayer = new CoucheEntree(2);
        inputLayer.addNeurone(new NeuroneEntree(2, new FonctionSigmoide()));
        inputLayer.addNeurone(new NeuroneEntree(2, new FonctionSigmoide()));

        // HiddenLayer

        // Output Layer (X AND Y)
        CoucheSortie outputLayer = new CoucheSortie(1);
        outputLayer.addNeurone(new NeuroneSortie(1, new FonctionSigmoide()));

        ReseauNeurone network = new ReseauNeurone();
        network.addLayer(inputLayer);
        network.addLayer(outputLayer);

        // Batch 1
        LotEchantillon batch = new LotEchantillon();
        batch.addEchantillon(new Echantillon(new double[]{0, 0}, new double[]{0}));
        batch.addEchantillon(new Echantillon(new double[]{0, 1}, new double[]{0}));
        batch.addEchantillon(new Echantillon(new double[]{1, 0}, new double[]{0}));
        batch.addEchantillon(new Echantillon(new double[]{1, 1}, new double[]{1}));
        network.train(batch, maxIterations, learningRate);

        // Batch 2
        LotEchantillon batch2 = new LotEchantillon();
        batch2.addEchantillon(new Echantillon(new double[]{0, 0}, new double[]{0}));
        batch2.addEchantillon(new Echantillon(new double[]{0, 0}, new double[]{0}));
        batch2.addEchantillon(new Echantillon(new double[]{1, 1}, new double[]{1}));
        batch2.addEchantillon(new Echantillon(new double[]{1, 1}, new double[]{1}));
        network.train(batch2, maxIterations, learningRate);

        // Batch 3
        LotEchantillon batch3 = new LotEchantillon();
        batch3.addEchantillon(new Echantillon(new double[]{0, 1}, new double[]{0}));
        batch3.addEchantillon(new Echantillon(new double[]{1, 0}, new double[]{0}));
        batch3.addEchantillon(new Echantillon(new double[]{1, 1}, new double[]{1}));
        batch3.addEchantillon(new Echantillon(new double[]{1, 1}, new double[]{1}));
        network.train(batch3, maxIterations, learningRate);

    }
}
