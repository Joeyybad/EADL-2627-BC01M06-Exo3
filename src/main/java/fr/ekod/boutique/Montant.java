package fr.ekod.boutique;

/**
 * Un montant en euros, stocké en centimes pour éviter les erreurs d'arrondi des décimaux.
 * Un montant n'est jamais négatif.
 */
public record Montant(long centimes) {

    public static final Montant ZERO = new Montant(0);

    public Montant {
        if (centimes < 0) {
            throw new IllegalArgumentException("montant négatif");
        }
    }

    public static Montant euros(double euros) {
        return new Montant(Math.round(euros * 100));
    }

    public Montant plus(Montant autre) {
        return new Montant(centimes + autre.centimes);
    }

    public boolean estSuperieurOuEgalA(Montant autre) {
        return centimes >= autre.centimes;
    }

    @Override
    public String toString() {
        return centimes / 100 + "," + String.format("%02d", centimes % 100) + " €";
    }
}
