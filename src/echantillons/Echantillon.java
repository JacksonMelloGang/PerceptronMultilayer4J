package echantillons;

import java.util.Arrays;

// Classe qui représente un échantillon, c'est-à-dire une entrée (tableau) et sa sortie attendue (tableau).
public class Echantillon {

    private double[] entrees; // Tableau des entrées
    private double[] resultatAttendus; // Tableau des résultats attendus

    /**
     * Constructeur de la classe Echantillon,
     * Initialise les entrées et les résultats attendus
     * Demande en paramètres deux tableaux de doubles: les inputs (entrées) et le/les résultats attendus.
     *
     * @param entrees Tableau d'entrées
     * @param resultatAttendus Tableau de sortie
     */
    public Echantillon(double[] entrees, double[] resultatAttendus) {
        this.entrees = entrees;
        this.resultatAttendus = resultatAttendus;
    }

    /**
     * Méthode pour obtenir les entrées
     * @return Tableau des entrées
     */
    public double[] getEntrees() {
        return entrees;
    }

    /**
     * Méthode pour obtenir les résultats attendus
     * @return Tableau des résultats attendus
     */
    public double[] getResultatAttendus(){
        return resultatAttendus;
    }

    /**
     * Méthode toString pour afficher les entrées et les résultats attendus
     * @return String représentant les entrées et les résultats attendus
     */
    @Override
    public String toString() {
        return "Echantillon{" + "entrees=" + Arrays.toString(entrees) + ", résultat attendu=" + Arrays.toString(resultatAttendus) + '}';
    }
}
