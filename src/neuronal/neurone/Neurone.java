package neuronal.neurone;


import neuronal.activation.IFonctionActivation;
import neuronal.activation.FonctionSigmoide;

/**
 * Classe représentant un neurone dans un réseau de neurones.
 * Un neurone est composé de poids, d'entrées, d'une sortie, d'un delta et d'une fonction d'activation.<br>
 *
 * <ul>
 *      <li>Les poids sont utilisés pour déterminer l'importance de chaque entrée pour le neurone.</li>
 *      <li>La sortie du neurone est déterminée en utilisant la fonction d'activation.</li>
 *      <li>Le delta est utilisé pour ajuster les poids du neurone lors de l'apprentissage.</li>
 *      <li>La fonction d'activation est utilisée pour activer le neurone.</li>
 * </ul>
 *
 * Classe mère pour {@link NeuroneEntree}, {@link NeuroneCache} et {@link NeuroneSortie}.
 *
 */
public class Neurone {

    private double[] poids; // Poids du neurone (dépend du nombre d'entrées) (valeur aléatoire)
    private double seuil; // seuil: valeur qui détermine si le neurone est activé ou non (valeur aléatoire par défaut, ajuster au cours de l'apprentissage)


    private double[] input; // valeurs d'entrées du neurone
    private double output; // valeur de sortie du neurone
    private double delta; // Delta du neurone: représente la différence entre la sortie attendue et la sortie réelle, elle est utilisé pour ajuster le poids du neuronne

    private IFonctionActivation fonctionActivation; // Fonction d'activation du neurone

    //private Random rdm = new Random();

    /**
     * Constructeur pour la classe Neurone.
     * Initialise les poids du neurone à des valeurs aléatoires ainsi que le seuil (valeur aléatoire également).
     *
     * Le seuil (bias) permet de définir "le niveau de sensibilité du neurone" : il permet de décaler la fonction d'activation et donc en d'autre terme définir à quel point il est facile d'activer le neurone ou non.
     * Les poids permettent de définir l'importance de chaque entrée pour le neurone
     *
     * @param nombreEntrees Le nombre d'entrées pour le neurone.
     */
    public Neurone(int nombreEntrees) {
        poids = new double[nombreEntrees]; // Initialisation du tableau des poids

        // NOTE: on peut aussi utiliser rdm.nextGaussian() à la place de Math.random()

        // Initialisation des poids à des valeurs aléatoires
        poids = new double[nombreEntrees];
        for (int i = 0; i < nombreEntrees; i++) {
            poids[i] = Math.random();
        }

        this.seuil = Math.random();
        this.fonctionActivation = new FonctionSigmoide(); // TODO: Mettre en paramètre du constructeur pour permettre de changer la fonction d'activation
    }

    /**
     * Active le neurone en utilisant la fonction d'activation (fonction sigmoide).
     *
     * @param entrees Les entrées pour le neurone.
     * @return Le résultat de l'activation du neurone.
     */
    public double activer(double[] entrees){
        this.input = entrees; // On sauvegarde les entrées pour un futur potentiel traitement / utilisation

        // On calcule la somme pondérée en ajoutant le seuil puis en multipliant chaque entrée par son poids correspondant,
        double sommePonderee = seuil;
        for (int i = 0; i < poids.length; i++) {
            sommePonderee += poids[i] * entrees[i];
        }

        // On utilise la fonction d'activation pour obtenir la sortie du neurone
        double output = fonctionActivation.activer(sommePonderee);
        this.output = output;

        // retourne valeur de sortie
        return output;
    }

    /**
     * Met a jour le poids du neurone: <br>
     * <strong style="color:orange ; font-size:14px">poids[x] = poids[x] - tauxApprentissage * delta * entree[x]</strong><br>
     * Le poids est mis à jour pour chaque entrée du neurone.
     * On met a jour le seuil également en fonction du taux d'apprentissage et du delta du neurone
     *
     * @param inputs Les entrées du neurone.
     * @param learningRate Le taux d'apprentissage pour la mise à jour des poids.
     */
    public void updateWeights(double[] inputs, double learningRate) {
        for (int i = 0; i < poids.length; i++) {
            poids[i] -= learningRate * delta * inputs[i];
        }
        seuil -= learningRate * delta;
    }


    /////////////////////////////
    // Getters and Setters
    /////////////////////////////

    public double[] getPoids() {
        return poids;
    }


    public double getSeuil() {
        return seuil;
    }

    public double[] getInput() {
        return input;
    }

    public double getOutput() {
        return output;
    }

    public double getDelta() {
        return delta;
    }

    public IFonctionActivation getFonctionActivation() {
        return fonctionActivation;
    }

    public void setDelta(double delta) {
        this.delta = delta;
    }

    public void setSeuil(double seuil) {
        this.seuil = seuil;
    }
}