package environnement;

public class Selection {
	public String def;
	public int id;
	public int cible;
	public int valeur;
	
	public Selection(Membres m, Votes v) {
		id =  m.id;
		cible = v.cible;
		valeur = v.valeur;
	}
	
	public String getDef() {
		return "Membre " + id + " . " + cible + " " + valeur;
	}
}
