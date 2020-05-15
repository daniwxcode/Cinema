package PackageCinema;

import org.omg.CosNaming.IstringHelper;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;

import static java.lang.System.exit;
import static java.lang.System.setOut;

public class App {

    public static void main(String[] args) {

        Cinema LeCinema = new Cinema();

        // Creation des Salles
        for (int i = 0; i < (int) getRandomintRange(2, 5); i++) {
            Salle salle = new Salle();
            salle.Numero = "Salle " + (i + 1);
            salle.NbPlace = getRandomintRange(1, 50);
            LeCinema.Salles.add(salle);
        }
        // création de deux films
        LeCinema.creerFilm("Forrest Gump", "Quelques décennies d'histoire américaine," +
                " des années 1940 à la fin du XXème siècle, à travers le regard et " +
                "l'étrange odyssée d'un homme simple et pur, Forrest Gump.", "Robert Zemeckis", 2015);
        LeCinema.creerFilm("La Ligne verte", "Paul Edgecomb, Gardien-chef du pénitencier de Cold " +
                "Mountain en 1935, était chargé de veiller au bon déroulement des exécutions capitales. Parmi les " +
                "prisonniers se trouvait un colosse du nom de John Coffey...", "Frank Darabont", 2019);


        Menu(LeCinema);

    }

    private static void cls() {
        try {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                Runtime.getRuntime().exec("cls");
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (final Exception e) {
            // on ignore l'erreur
        }
    }


    private static String saisirChamps(String champs) {
        System.out.print("Veuillez saisir le " + champs + " : ");
        Scanner scan = new Scanner(System.in);

        String retour = "";
        while (retour.length()<1) {
            retour = scan.nextLine();
        }
        scan.close();
        return retour;
    }

    private static void afficheListe(List<String> list, String titre) {
        cls();
        System.out.println("\n");
        System.out.println("-----" + titre + "-----");
        int i = 1;
        for (String option : list) {
            System.out.println(i + " - " + option);
            i++;
        }
        System.out.println("----------");
    }

    private static void afficheMessageChoix(String titre, List<String> Options, String retour) {
        cls();
        System.out.println("\n");
        System.out.println("-----" + titre + "-----");
        System.out.println("Veuillez choisir une Option");
        if (Options.size() == 0) {
            System.out.println("liste Vide");
        }
        for (String option : Options) {
            System.out.println(Options.indexOf(option) + 1 + " - " + option);
        }
        System.out.println("0 - " + retour);
        System.out.print("Votre Choix: ");

    }

    private static int getRandomintRange(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    // Gestion des Menus
    private static void Menu(Cinema cinema) {
        ArrayList<String> options = new ArrayList<String>() {
            {
                add("Option Client");
                add("Option Gestion Cinéma");
            }
        };
        afficheMessageChoix("MENU PRINCIPAL", options, "Quitter");
        int ret = 0;
        Scanner scan = new Scanner(System.in);
        try {
            if (scan.hasNext()) {
                ret = scan.nextInt();
            }
        } catch (Exception e) {
            System.out.println("Veuillez saissir une valeur comprise entre 0 et " + options.size());

        }

        switch (ret) {
            case 0: {
                exit(0);
                break;
            }
            case 1: {
                MenuClient(cinema);
                break;
            }
            case 2: {
                MenuGestion(cinema, scan);
                break;
            }
            default:
                Menu(cinema);
        }
    }

    private static void MenuGestion(Cinema cinema, Scanner scan) {
        ArrayList<String> options = new ArrayList<String>() {
            {
                add("Creer un Film");
                add("Chiffre d'Affaire");
                add("Nouvelle Semmaine ");
                add("Taux de remplissage ");
            }
        };
        afficheMessageChoix("MENU GESTION", options, "Retour");
        int num = 0;
        try {
            if (scan.hasNext()) {
                num = scan.nextInt();
            }
        } catch (Exception e) {
            System.out.println("Veuillez saissir une valeur comprise entre 0 et " + options.size());

        }
        switch (num) {

            case 0: {
                Menu(cinema);
                break;
            }
            case 1: {
                creationFIlm(cinema, scan);
                break;
            }
            case 2: {
                MenuGestion(cinema, scan);
                break;
            }
            case 3: {
                creationSeance(cinema, scan);
                break;
            }
            case -1: {
                Menu(cinema);
                break;
            }
            default:
                Menu(cinema);
        }

    }

    private static void MenuClient(Cinema cinema) {
        ArrayList<String> options = new ArrayList<String>() {
            {
                add("Les séances");
                add("Descriptif d'un Film");
                add("Film à l'Affiche");
                add("Achat de place");
            }
        };
        afficheMessageChoix("MENU GESTION", options, "Retour");
        int ret = 0;
        Scanner scan = new Scanner(System.in);
        try {
            if (scan.hasNext()) {
                ret = scan.nextInt();
            }
        } catch (Exception e) {
            System.out.println("Veuillez saissir une valeur comprise entre 0 et " + options.size());
        }
        switch (ret) {

            case 0: {
                Menu(cinema);
                break;
            }
            case 1: {
                for (Film film : cinema.Films
                ) {
                    System.out.println(film.ShortDesc());
                }
                break;
            }
            default:
                MenuClient(cinema);
        }
    }

    // Fin definition des Menus
    private static void creationFIlm(Cinema cinema, Scanner scan) {
        afficheListe(cinema.Listefilms(), "Liste des Films");

        cinema.creerFilm(saisirChamps("Titre"),
                saisirChamps("Descriptif"),
                saisirChamps("Réalisateur"),
                Integer.parseInt(saisirChamps("Année")));
        afficheListe(cinema.Listefilms(), "Liste des films");
        System.out.println("Pour Enregistrer un autre film taper O");
        System.out.print("Votre Réponse");

        String rep = scan.nextLine();
        System.out.println(rep);
        scan.close();
        if (rep.equalsIgnoreCase("O")) {
            creationFIlm(cinema, scan);
        } else {
            MenuGestion(cinema, scan);
        }
    }

    private static void creationSeance(Cinema cinema, Scanner scan) {
        if (cinema.Seances.size() > 0) {
            afficheListe(cinema.lesSeances(), "Liste des Seances");
        }
        Seance seance = new Seance();
        seance.jourHoraire = saisirChamps("Jour Horaire");
        afficheListe(cinema.Listefilms(), "Liste de Films");
        int choix = 0;
        try {
            choix = Integer.parseInt(saisirChamps("numéro du Film"));
            seance.Film = cinema.Films.get(choix - 1);
        } catch (Exception e) {
            System.out.println("Veuillez saissir une valeur comprise entre 1 et " + cinema.Films.size());
        }

        afficheListe(cinema.ListeSalles(), "choix de la Salle");
        try {
            if (scan.hasNext()) {
                choix = scan.nextInt();
                seance.Salle = cinema.Salles.get(choix - 1);
            }
        } catch (Exception e) {
            System.out.println("Veuillez saissir une valeur comprise entre 1 et " + cinema.Salles.size());
        }
        cinema.Seances.add(seance);
        System.out.println("Pour Enregistrer une Autre séance taper O");
        System.out.print("Votre Réponse : ");
        String rep = "";
        while (scan.hasNextLine()) {
            scan.nextLine();
        }

        System.out.println(rep);
        scan.close();
        if (rep.equalsIgnoreCase("O")) {
            creationSeance(cinema, scan);
        } else {
            MenuGestion(cinema, scan);
        }


    }
}
