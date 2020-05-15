package PackageCinema;

public class Salle {
	public String Numero;
	public int NbPlace;

	@Override
	public String toString() {
		return "Salle" +
				"Numéro:" + Numero + "(" + NbPlace +" Places )";
	}
}
