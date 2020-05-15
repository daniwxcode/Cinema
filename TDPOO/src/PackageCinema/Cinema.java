package PackageCinema;

import java.util.*;


import Exceptions.PasDeSeanceException;
import PackageCinema.Interfaces.ICinema;
import PackageCinema.Interfaces.IGestCinema;

public final class Cinema implements ICinema, IGestCinema {
    public ArrayList<Film> Films = new ArrayList<Film>();
    public ArrayList<Salle> Salles = new ArrayList<Salle>();
    public ArrayList<Seance> Seances= new ArrayList<Seance>();

    public ArrayList<String> Listefilms() {
    ArrayList<String> retour = new ArrayList<String>();
        for (Film film :
                Films) {
            retour.add(film.ShortDesc());
        }
        return retour;
    }
    public ArrayList<String> ListeSalles() {
        ArrayList<String> retour = new ArrayList<String>();
        for (Salle salle :
                Salles) {
            retour.add(salle.toString());
        }
        return retour;
    }






    @Override
    public void creerFilm(String titreFilm, String descriptif, String realisateur, int annee) {

        Film film = new Film();
        film.Titre = titreFilm;
        film.Descriptif = descriptif;
        film.Realisateur = realisateur;
        film.Annee = annee;
        if (!Films.contains(film))
            Films.add(film);
    }

    @Override
    public int calculeChiffreAffaires() {
        return Seances.stream().mapToInt(Seance::Recette).sum();
    }

    @Override
    public void nouvelleSemaine() {
        Seances = new ArrayList<Seance>();
    }

    @Override
    public float tauxRemplissage(String titreFilm) {
        return (float) Seances.stream().mapToDouble(Seance::Taux).average().getAsDouble();
    }

    @Override
    public List<String> lesSeances() {
        List<String> retour = null;
        for (Seance seance :
                Seances) {
            retour.add(seance.toString());
        }
        return retour;

    }

    @Override
    public String descriptifFilm(String titreFilm) {
        Optional<Film> film = Films.stream().filter(p -> p.Titre == titreFilm).findFirst();
        if (film.isPresent()) {
            return film.get().LongDesc();
        }
        return null;
    }

    @Override
    public List<String> filmsALAffiche() {
        List<String> retour = null;
        for (Seance seance :
                Seances) {
            retour.add(seance.Film.ShortDesc());
        }
        return (List<String>) new HashSet<String>(retour);
    }




    @Override
    public boolean achetePlace(String titreFilm, String jourHoraire, int tarif) throws PasDeSeanceException {
        Optional<Seance> optseance= Seances.stream().filter(p->p.jourHoraire.equalsIgnoreCase(jourHoraire)).findFirst();
        if(!optseance.isPresent())
            throw new PasDeSeanceException();
        Seance seance = optseance.get();
        if(!seance.Film.Titre.equalsIgnoreCase(titreFilm))
            throw  new PasDeSeanceException();
        seance.Achats.add(tarif);
        return true;

    }

}
