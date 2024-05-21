package echantillons;

import java.util.ArrayList;
import java.util.List;

public class LotEchantillon {

    private ArrayList<Echantillon> echantillons;

    public LotEchantillon() {
        echantillons = new ArrayList<>();
    }

    public void addEchantillon(Echantillon echantillon) {
        echantillons.add(echantillon);
    }

    public Echantillon getEchantillon(int index) {
        return echantillons.get(index);
    }

    public List<Echantillon> getEchantillons() {
        return echantillons;
    }

}
