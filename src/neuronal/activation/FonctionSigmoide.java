package neuronal.activation;

import java.io.Serializable;

/**
 * Classe représentant la fonction d'activation sigmoide.
 */
public class FonctionSigmoide implements IFonctionActivation, Serializable {
    /**
     * Active le neurone en utilisant la fonction d'activation sigmoide, elle détermine la valeur d'output du neurone.
     * @param input La somme pondérée des entrées du neurone.
     * @return La valeur d'output du neurone.
     */
    @Override
    public double activer(double input) {
        return 1 / (1 + Math.exp(-input));
    }

    /**
     * Calcule la dérivée de la fonction d'activation sigmoide.
     * Est utilisé pour la rétropropagation du gradient.
     * @param input La somme pondérée des entrées du neurone.
     * @return La dérivée de la fonction d'activation sigmoide.
     */
    @Override
    public double derivee(double input) {
        return activer(input) * (1 - activer(input));
    }
}
