package web;

import java.util.Set;
import java.util.SortedSet;

import metier.Candidat;
import metier.Equipe;

public class InscriptionModel {

	private Set<Candidat>  AllEquip;
	private String mode = "Envoyer";
	private  String valSelect ;
	

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public  String getValSelect() {
		return valSelect;
	}

	public  void setValSelect(String valSelect) {
		this.valSelect = valSelect;
	}

	public Set<Candidat> getAllEquip() {
		return AllEquip;
	}

	public void setAllEquip(SortedSet<Candidat> sortedSet) {
		AllEquip = sortedSet;
	} 
}
