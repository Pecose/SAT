package environnement;

public class Afficheur {
	public static void afficher(Generateur g) {
		for(Membres m : g.listMembre) {
			System.out.println("Membre " + m.id);
			
			for(Votes v : m.listVotes) {
				System.out.println(v.cible + " . " + v.valeur);
			}
		}
	}
}
