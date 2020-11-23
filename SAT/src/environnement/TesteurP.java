package environnement;

import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.TreeSet;

public class TesteurP {
	
	public ArrayList<ArrayList<Integer>> X = new ArrayList<ArrayList<Integer>>();
	public ArrayList<ArrayList<Integer>> M = new ArrayList<ArrayList<Integer>>();
	public TreeSet<Integer> set = new TreeSet<Integer>();
//	public ArrayList<ArrayList<Integer>> listCombinaisons = new ArrayList<ArrayList<Integer>>();
	
	public boolean tester(Generateur g) {
//		creeListCombinaisons(new ArrayList<Integer>(), 0, g);
		return this.testerCombinaisons(g);
	}
	


	public boolean testerCombinaisons(Generateur g) {

		X = new ArrayList<ArrayList<Integer>>();
		X.add(new ArrayList<Integer>());
		for(int i = 0; i < Lanceur.nbCandidats; i++) {
			X.get(0).add(2);
		}
		M = new ArrayList<ArrayList<Integer>>();
		for(Entry<Integer, ArrayList<Integer>> _M : g.votesParLignes.entrySet()) {
			M.add(new ArrayList<Integer>());
			for(int i = 0; i < Lanceur.nbCandidats; i++) {
				M.get(M.size()-1).add(_M.getValue().get(i));
			}
		}
		
		
//		System.out.println("M:");
//		for(Entry<Integer, ArrayList<Integer>> _M : g.votesParLignes.entrySet()) {
//			for(int i = 0; i < Lanceur.nbCandidats; i++) {
//				System.out.print(_M.getValue().get(i));
//			}
//			System.out.println();
//		}
//		System.out.println();System.out.println("////");System.out.println();
		
//		System.out.println("X:");
//		for(ArrayList<Integer> _X1 : X){
//			for(int i = 0; i < Lanceur.nbCandidats; i++) {
//				System.out.print(_X1.get(i));
//			}
//			System.out.println();
//		}
		
		for(int k = 0; k < M.size(); k++){
			
			for(int j = 0; j < X.size(); j++){
				
				boolean b = true;
				for(int i = 0; i < Lanceur.nbCandidats; i++) {
					if(X.get(j).get(i) == 0 && M.get(k).get(i) == 1 || X.get(j).get(i) == 1 && M.get(k).get(i) == 0) {
						b = false;
					}
				}
				
				if(b){
					ArrayList<Integer> _X = (ArrayList<Integer>) X.get(j).clone();		
					X.remove(j);
					j--;
					
					for(int i = 0 ; i < Lanceur.nbCandidats; i++) {
						
						if(_X.get(i) == 2 && M.get(k).get(i) == 0) {
//							System.out.print("0 ");
//							System.out.print(_X);
							_X.set(i, 1);
//							System.out.print(" ");
//							System.out.print(_X);
							X.add((ArrayList<Integer>) _X.clone());
							j++;
							_X.set(i, 0);
							break;
//							System.out.print(" ");
//							System.out.print(_X);
//							System.out.println();
						}else if(_X.get(i) == 2 && M.get(k).get(i) == 1) {
//							System.out.print("0 ");
//							System.out.print(_X);
							_X.set(i, 0);
//							System.out.print(" ");
//							System.out.print(_X);
							X.add((ArrayList<Integer>) _X.clone());
							j++;
							_X.set(i, 1);
							break;
//							System.out.print(" ");
//							System.out.print(_X);
//							System.out.println();
						}
						
					}
					
				}
				
			}
			
		}
		
		if(X.size() > 0){
			for(int j = 0; j < X.size(); j++){
				boolean b = false;
				for(int k = 0; k < M.size(); k++){
					boolean a = true;
					for(int i = 0; i < Lanceur.nbCandidats; i++) {
						if(X.get(j).get(i) == 0 && M.get(k).get(i) == 1 || X.get(j).get(i) == 1 && M.get(k).get(i) == 0) {
							a = false;
						}
					}
					if(a){ b = true; }
				}
				if(b){
					X.remove(j);
					j--;
				}
			}
				
				
		}
		
		if(X.size() > 0){
			return true;
		}
		return false;
	}
}

