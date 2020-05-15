package PackageCinema.Interfaces;

import java.util.List;
import Exceptions.PasDeSeanceException;

public interface ICinema {
	 public List<String> lesSeances();
	 public String descriptifFilm(String titreFilm);
	 public List<String> filmsALAffiche(); 
	 public boolean achetePlace(String titreFilm, String jourHoraire, int tarif)          
	 throws PasDeSeanceException; 

}
