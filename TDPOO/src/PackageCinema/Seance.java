package PackageCinema;

import Enums.Tarif;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Seance {
    public Film Film = new Film();
    public String jourHoraire="";
    public ArrayList<Tarif> Achats = new ArrayList<Tarif>();
    public Salle Salle = new Salle();


    public boolean estComplete() {
        return Salle.NbPlace == Achats.stream().count();
    }

    public double Taux() {
        return Achats.stream().count() / Salle.NbPlace * 100;
    }

    public int Recette() {
        return Achats.stream().mapToInt(Tarif::getMontant)
                .sum();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seance seance = (Seance) o;
        return jourHoraire.equals(seance.jourHoraire);
    }


    @Override
    public int hashCode() {
        return Objects.hash(jourHoraire);
    }

    @Override
    public String toString() {
        return
                "Film: " + Film.ShortDesc() +
                        Salle.toString() +
                        "Date :" + jourHoraire +
                        "\n Recettes: " +
                        Recette() +
                        "Taux: " + Taux() + "% ";
    }
}
