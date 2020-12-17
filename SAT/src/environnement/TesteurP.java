package environnement;

import java.util.ArrayList;
import java.util.Map.Entry;

public class TesteurP {
	
	public ArrayList<ArrayList<Integer>> K;
	public ArrayList<ArrayList<Integer>> M;
	public Generateur g;
	
	public boolean tester(Generateur g) {
		return this.testerCombinaisons(g);
	}
	
	public boolean testerCombinaisons(Generateur g) {
		this.g = g;
		this.construireK();
		this.construireM();
		
		return this.testerM();
	}
	
	public boolean testerM() {
		
		this.parcourirMetK();
		
		if(K.size() > 0){
			for(int j = 0; j < K.size(); j++){
				boolean b = false;
				for(int k = 0; k < M.size(); k++){
					boolean a = true;
					for(int i = 0; i < Lanceur.nbCandidats; i++) {
						if(K.get(j).get(i) == 0 && M.get(k).get(i) == 1 || K.get(j).get(i) == 1 && M.get(k).get(i) == 0) {
							a = false;
						}
					}
					if(a){ b = true; }
				}
				if(b){
					K.remove(j);
					j--;
				}
			}
		}
		
		return this.testerTailleDeK();
	}
	
	public void parcourirMetK() {
		for(int k = 0; k < M.size(); k++){
			for(int j = 0; j < K.size(); j++){
				if(this.testerKFamillierM(j, k)){
					ArrayList<Integer> _K = (ArrayList<Integer>) K.get(j).clone();		
					ArrayList<Integer> _M = (ArrayList<Integer>) M.get(k).clone();	
					K.remove(j);
					j--;
					this.supprimerMdeK(_K, _M);
				}
			}
		}
	}
	
	public void supprimerMdeK(ArrayList<Integer> _K, ArrayList<Integer> _M) {
		
		for(int i = 0 ; i < Lanceur.nbCandidats; i++) {
			
			if(_K.get(i) == 2 && _M.get(i) == 0) {
				_K.set(i, 1);
				K.add((ArrayList<Integer>) _K.clone());
				_K.set(i, 0);
			}else if(_K.get(i) == 2 && _M.get(i) == 1) {
				_K.set(i, 0);
				K.add((ArrayList<Integer>) _K.clone());
				_K.set(i, 1);
			}
			
		}
	}
	
	public boolean testerTailleDeK() {
		if(K.size() > 0){
			return true;
		}
		return false;
	}
	
	public boolean testerKFamillierM(int j, int k) {
		boolean b = true;
		for(int i = 0; i < Lanceur.nbCandidats; i++) {
			if(K.get(j).get(i) == 0 && M.get(k).get(i) == 1 || K.get(j).get(i) == 1 && M.get(k).get(i) == 0) {
				b = false;
			}
		}
		return b;
	}
	
	public void construireK() {
		K = new ArrayList<ArrayList<Integer>>();
		K.add(new ArrayList<Integer>());
		for(int i = 0; i < Lanceur.nbCandidats; i++) {
			K.get(0).add(2);
		}
	}
	
	public void construireM() {
		M = new ArrayList<ArrayList<Integer>>();
		for(Entry<Integer, ArrayList<Integer>> _M : g.votesParLignes.entrySet()) {
			M.add(new ArrayList<Integer>());
			for(int i = 0; i < Lanceur.nbCandidats; i++) {
				M.get(M.size()-1).add(_M.getValue().get(i));
			}
		}
	}
}
