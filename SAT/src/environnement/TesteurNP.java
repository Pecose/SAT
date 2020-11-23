package environnement;

import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.TreeSet;

public class TesteurNP {
	public TreeSet<Integer> set = new TreeSet<Integer>();
//	public ArrayList<ArrayList<Integer>> listCombinaisons = new ArrayList<ArrayList<Integer>>();
	
	public boolean tester(Generateur g) {
//		creeListCombinaisons(new ArrayList<Integer>(), 0, g);
		return this.testerCombinaisons(g);
	}
	
//	public void creeListCombinaisons(ArrayList<Integer> list, int mot, Generateur g) {
//		
//		if(list.size() >= Math.pow(2, g.nbCandidats-1)) {
//			listCombinaisons.add(list);
//		}else {
//			ArrayList<Integer> BList = new ArrayList<Integer>();
//			for(int bb : list) BList.add(bb);
//
//			list.add(0);
//			BList.add(1);
//			
//			creeListCombinaisons(list, mot+1, g);
//			creeListCombinaisons(BList, mot+1, g);
//	
//		}
//	}
	


	public boolean testerCombinaisons(Generateur g) {

		//on test les certificats pour voir si il en existe un valide
		//on parcours K et on compare chaqu'un de ses éléments avec chaques éléments de M
		//Si un élément de K possède au moins un vote en commun avec chaques éléments de M alors ce certificat est valide
		
		boolean KTeste = false;
		//On teste si il existe un cerificat valide
		for(ArrayList<Integer> _K : Lanceur.K) {
			boolean certifTeste = true;
			//On teste si tout les éléments de M sont valides pour le certificat
			for(Entry<Integer, ArrayList<Integer>> _M : g.votesParLignes.entrySet()) {
				boolean membreTeste = false;
				//On teste si le certificat est valide pour le membre testé
				//c'est à dire que au moins un des votes connexe est égale à 1-1 ou 0-0
				for(int i = 0; i < Lanceur.nbCandidats; i++) {
					if(_K.get(i) == _M.getValue().get(i)) {
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


