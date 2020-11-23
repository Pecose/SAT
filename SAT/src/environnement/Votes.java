package environnement;

public class Votes {
	public int cible;
	public int valeur;
	
	public Votes(int cible, int valeur) {
		this.cible = cible;
		this.valeur = valeur;
	}
	
	public Votes(int cible) {
		
		this.cible = cible;
		double m = Math.random();
		
		if(m < 1/3d) {
			valeur = 0;
		}else if(m > 2*1/3d) {
			valeur = 1;
		}else {
			valeur = 2;
		}
		
	}
}
