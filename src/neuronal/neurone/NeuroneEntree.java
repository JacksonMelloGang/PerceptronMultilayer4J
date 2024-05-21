package neuronal.neurone;


import java.util.Arrays;

public class NeuroneEntree extends Neurone {
    public NeuroneEntree(int nombreEntree) {
        super(nombreEntree);
        System.out.println("Neurone d'entrée crée, type: " + TypeNeurone.ENTREE + " | poids: " + Arrays.toString(super.getPoids()));
    }
}
