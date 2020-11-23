package environnement;

import java.util.ArrayList;
import java.util.HashMap;

public class Generateur {
	
//	public int nbMembres;
//	public int nbCandidats;
//	public int maxMembres = (int) ((Math.random()*6)+1);
//	public int maxMembres = 65;
//	public int maxCandidats = 5;
	
//	public ListeVotes memoVotes = new ListeVotes();
	public HashMap<Integer, ArrayList<Integer>> votesParColonnes = new HashMap<Integer, ArrayList<Integer>>();
	public HashMap<Integer, ArrayList<Integer>> votesParLignes = new HashMap<Integer, ArrayList<Integer>>();
	public ArrayList<Integer> listPhrasesTraduites = new ArrayList<Integer>();
	
	public ArrayList<Membres> listMembre = new ArrayList<Membres>();
	
	public Generateur() {
//		nbMembres = (int) (Math.random() * maxMembres)+1;
//		nbCandidats = (int) (Math.random() * maxCandidats)+1;
		
		int id = 0;
		while(listMembre.size() < Lanceur.nbMembres) {
			listMembre.add(new Membres(id++, this));
		}
	}
	
	
}
