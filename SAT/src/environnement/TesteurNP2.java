package environnement;

import java.util.ArrayList;
import java.util.Map.Entry;

public class TesteurNP2 {
	
	
	public boolean tester(Generateur g) {
		return this.testerCombinaisons(g);
	}
	
	public boolean testerCombinaisons(Generateur g) {

		//on s'assure que dans K, au moins une ligne, à tout ses votes qui sont différants des votes connexes dans M
		//si c'est le cas pour toutes les lignes alors il n'y à pas dans K d'éléments qui ont leur inverse dans M
		//si au contraire tout les éléments de K ont leurs inverse dans M alors il n'y a pas de chemin possible
		
		boolean KTeste = false;
		//On teste tout les cerificats de K
		for(ArrayList<Integer> _K : Lanceur.K) {
			boolean certifTeste = true;
			//On teste si au moins un éléments de M est identique au certificat testé
			for(Entry<Integer, ArrayList<Integer>> _M : g.votesParLignes.entrySet()) {
				boolean membreTeste = false;
				//On teste si le certificat est invalide pour le membre testé
				//c'est à dire que tout les votes connexes sont differants de 0-1 ou 1-0
				for(int i = 0; i < Lanceur.nbCandidats; i++) {
					if(_K.get(i) == 0 && _M.getValue().get(i) == 1 || _K.get(i) == 1 && _M.getValue().get(i) == 0) {
						membreTeste = true;
					}
				}
				if(certifTeste)certifTeste = membreTeste;
			}
			if(!KTeste)KTeste = certifTeste;
		}
		return KTeste;
	}

}


