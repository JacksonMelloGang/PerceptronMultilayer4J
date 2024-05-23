package neuronal.activation;

/**
 * Interface représentant une fonction d'activation pour un neurone.
 */
public interface IFonctionActivation {

    /**
     * Active la fonction d'activation.
     *
     * @param sommePonderee La somme pondérée des entrées du neurone.
     * @return La valeur d'output de la fonction d'activation.
     */
    double activer(double sommePonderee);

    /**
     * Calcule la dérivée de la fonction d'activation.
     * Est utilisé pour la rétropropagation du gradient.
     * @param input la valeur d'entrée utilisé pour la dérivé.
     * @return La dérivée de la fonction d'activation.
     */
    double derivee(double input);
}
