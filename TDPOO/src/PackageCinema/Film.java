package PackageCinema;

import PackageCinema.Interfaces.iFilm;

import java.util.Objects;

public class Film implements iFilm {
	public String Titre;
	public String Realisateur;
	public int Annee;
	public String Descriptif;

    @Override
    public String ShortDesc() {
        return "Titre :" + Titre +
                " Realisateur:" + Realisateur +
                " Annee:" + Annee;
    }

    @Override
    public String LongDesc() {
        return "Titre :" + Titre +
                " Realisateur:" + Realisateur +
                " Annee:" + Annee +
                " Descriptif : "+ Descriptif;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Film film = (Film) o;
        return Titre.equals(film.Titre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Titre);
    }
}
