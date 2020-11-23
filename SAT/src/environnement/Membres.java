package environnement;

import java.util.ArrayList;

public class Membres {
	
	public int id;
	public ListeVotes listVotes = new ListeVotes();
	public ListePhrases listPhrases = new ListePhrases();
//	public ListeVotes memoVotes = new ListeVotes();
	public ArrayList<ArrayList<Integer>> listCombinaisons = new ArrayList<ArrayList<Integer>>();
	
	public Membres(int id, Generateur g) {
		this.id = id;
		int cible = 0;
		while(listVotes.size() < Lanceur.nbCandidats) {
			listVotes.add(new Votes(cible++));
		}
		for(Votes i : listVotes) {
			if(!g.votesParLignes.containsKey(id)){
				g.votesParLignes.put(id, new ArrayList<Integer>());
			}
			g.votesParLignes.get(id).add(i.valeur);
			
			
			if(!g.votesParColonnes.containsKey(i.cible)){
				g.votesParColonnes.put(i.cible, new ArrayList<Integer>());
			}
			g.votesParColonnes.get(i.cible).add(i.valeur);

		}
		expoPhrase(0, 0);
		calculePhrase(g);
		
	}

	public void expoPhrase(int mot, int save) {
		
		listPhrases.add(listVotes);
		for(int pos = 0; pos < listPhrases.size(); pos++) {
			ListeVotes lv = listPhrases.get(pos);
			for(Votes v : lv) {
				if(v.valeur == 2) {
					lv.get(lv.indexOf(v)).valeur = 1;
					listPhrases.add(lv);
					
					lv.get(lv.indexOf(v)).valeur = 0;
					pos--;
					break;
				}
			}
		}
	}
	
	public void creeListCombinaisons(ArrayList<Integer> list, int mot, Generateur g) {
		
		if(list.size() >= Math.pow(2, Lanceur.nbCandidats-1)) {
			listCombinaisons.add(list);
		}else {
			ArrayList<Integer> BList = new ArrayList<Integer>();
			for(int bb : list) BList.add(bb);

			list.add(0);
			BList.add(1);
			
			creeListCombinaisons(list, mot+1, g);
			creeListCombinaisons(BList, mot+1, g);
	
		}
	}

	public void calculePhrase(Generateur g) {
		
		// 0 = 0 ; 1 = 1 ; 2 = null
		
		for(ListeVotes lv : listPhrases) {
			int total = 0;
			for(Votes v : lv) {
				if(v.valeur == 1) {
					total += Math.pow(2, lv.indexOf(v));
				}
			}
			g.listPhrasesTraduites.add(total);
		}
	}
}
