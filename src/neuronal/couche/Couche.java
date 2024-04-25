package neuronal.couche;

import neuronal.neurone.Neurone;

/**
 * Classe représentant une couche dans un réseau de neurones.
 */
public class Couche {
    private int nombreNeurones;
    private Neurone[] neurones;

    private TypeCouche typeCouche;

    /**
     * Constructeur pour la classe Couche.
     *
     * @param nombreNeurones Le nombre de neurones dans la couche.
     * @param layerType Le type de couche (par exemple, caché, sortie).
     */
    public Couche(int nombreNeurones, TypeCouche layerType){
        this.typeCouche = layerType;

        neurones = new Neurone[nombreNeurones];
    }

    /**
     * Ajoute un neurone à la couche.
     *
     * @param neurone Le neurone à ajouter.
     */
    public void addNeurone(Neurone neurone){
        if (neurone.getNeuronType().toString().equals(this.typeCouche.toString()))  {
            for (int i = 0; i < neurones.length; i++) {
                if (neurones[i] == null) {
                    neurones[i] = neurone;
                    nombreNeurones++;
                    break;
                }
            }
        } else {
            throw new IllegalArgumentException("Le neurone n'appartient pas à cette couche !");
        }
    }

    /**
     * Calcule la sortie de la couche en activant chaque neurone avec les entrées fournies.
     *
     * @param entrees Les entrées pour la couche.
     * @return Les sorties de la couche.
     */
    public double[] calculerSortie(double[] entrees) {
        double[] sorties = new double[nombreNeurones];
        for (int i = 0; i < nombreNeurones; i++) {
            sorties[i] = neurones[i].activer(entrees);
        }
        return sorties;
    }

    /**
     * Récupère le nombre de neurones dans la couche.
     *
     * @return Le nombre de neurones.
     */
    public int getNbNeurones(){
        return this.nombreNeurones;
    }

    /**
     * Récupère les neurones de la couche.
     *
     * @return Les neurones de la couche.
     */
    public Neurone[] getNeurones(){
        return this.neurones;
    }

    /**
     * Récupère le type de couche.
     *
     * @return Le type de couche.
     */
    public TypeCouche getTypeCouche(){
        return this.typeCouche;
    }
}