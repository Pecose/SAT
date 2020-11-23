package environnement;

import java.util.ArrayList;
import java.util.Map.Entry;

public class Lanceur {
	
	public static ArrayList<ArrayList<Integer>> K = new ArrayList<ArrayList<Integer>>();
	public static int nbMembres;
	public static int nbCandidats = 5;
	public static int maxMembres = 20;
	public static int maxCandidats;
	
	public static void main(String[] args) {

		nbMembres = (int) (Math.random() * maxMembres)+1;
//		nbCandidats = (int) (Math.random() * maxCandidats)+1;
		creeK(new ArrayList<Integer>(), 0);
		
		for(int i = 0; i < 10000; i++) {
			System.out.println();
			for(int j = 0; j < 10000; j++) {
				Generateur g = new Generateur();
				
				TesteurP pT = new TesteurP();
				boolean p = pT.tester(g);
				
				TesteurNP npT = new TesteurNP();
				boolean np = npT.tester(g);
				
				
				if(p == np) {
					if(p && np) {
						System.out.print("!");
					}else if(!p && !np){
						System.out.print("?");
					}else {
						System.out.print("$");
					}
					
				}else {
					
					System.out.println();
					System.out.println("NP : "+np);
					System.out.println("P : "+p);
					
					erreurNP(npT, g);
					erreurP(pT, g);
					
					System.exit(0);
				}
				
			}
		}
	}
		
	public static void erreurNP2(TesteurP pT, Generateur g) {
		System.out.println("liste une fois triée:");
//		for(int l : pT.set) {
//			System.out.print(l + " ");
//		}
		System.out.println();
		System.out.println("liste des phrases en decimal:");
		for(int l : g.listPhrasesTraduites) {
			System.out.print(l + " ");
		}
		System.out.println();
		
		System.out.println("liste des phrases en binaire:");
		int size = 0;
		
		
		System.out.println("nb membres : "+nbMembres+" - nb phrases: "+size+ " ::::");
	}
	
	public static void erreurP(TesteurP pT, Generateur g) {
		
		//
		System.out.println("X:");
		for(ArrayList<Integer> _X : pT.X){
			for(int i = 0; i < Lanceur.nbCandidats; i++) {
				System.out.print(_X.get(i));
			}
			System.out.println();
		}
		
		
	}
	
	public static void erreurNP(TesteurNP npT, Generateur g) {

//		Afficheur.afficher(g);
		
		System.out.println("Tableau des votes par colonnes:");
		for(Entry<Integer, ArrayList<Integer>> e : g.votesParColonnes.entrySet()) {
			System.out.print(e.getKey()+":   ");
			for(int i : e.getValue()) {
				System.out.print(i+" ");
			}
			System.out.println();
		}
		
		System.out.println("Tableau des votes par lignes:");
		for(Entry<Integer, ArrayList<Integer>> e : g.votesParLignes.entrySet()) {
			System.out.print(e.getKey()+":   ");
			for(int i : e.getValue()) {
				System.out.print(i+" ");
			}
			System.out.println();
		}
		
	}
	
	public static void creeK(ArrayList<Integer> list, int mot) {
		
		if(mot >= nbCandidats) {
			K.add(list);
		}else {
			ArrayList<Integer> BList = new ArrayList<Integer>();
			for(int bb : list) BList.add(bb);

			list.add(0);
			BList.add(1);
			
			creeK(list, mot+1);
			creeK(BList, mot+1);
	
		}
	}
	
	public static void verifierK() {
		for(ArrayList<Integer> _K : Lanceur.K) {
			for(int v = 0; v < Lanceur.nbCandidats; v++) {
				System.out.print(_K.get(v));
			}
			System.out.println();
			
		}
		System.exit(0);
	}

}
