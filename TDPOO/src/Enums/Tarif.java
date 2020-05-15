package Enums;

public enum Tarif {
	 NORMAL  (5),
	 REDUIT(2),
	 OFFERT   (0)
;
    private final int montant;
		Tarif(int i) {
			montant = i;
		}
    public int getMontant() {
        return this.montant;
    }
    public String AfficheMontant() {
        return  this.montant+" Euros";
    }
}
