package echantillons;

import java.util.ArrayList;
import java.util.List;

// Classe qui représente un lot d'échantillons, c'est-à-dire un ensemble d'échantillons.
public class LotEchantillon {

    // Liste des échantillons
    private ArrayList<Echantillon> echantillons;

    /**
     * Constructeur du lot d'échantillons (Instancie la liste des échantillons)
     */
    public LotEchantillon() {
        echantillons = new ArrayList<>();
    }

    /**
     * Ajoute un échantillon à la liste des échantillons
     * @param echantillon Echantillon à ajouter
     */
    public void addEchantillon(Echantillon echantillon) {
        echantillons.add(echantillon);
    }

    /**
     *  Retourne un échantillon à un index donné
     * @param index index de l'échantillon à retourner
     * @return l'échantillon demandé qui se trouve à l'index donné
     */
    public Echantillon getEchantillon(int index) {
        return echantillons.get(index);
    }

    /**
     * Retourne la liste des échantillons
     *
     * @return la liste des échantillons
     */
    public List<Echantillon> getEchantillons() {
        return echantillons;
    }

}
