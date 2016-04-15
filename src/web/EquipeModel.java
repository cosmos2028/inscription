package web;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import metier.Equipe;
import metier.Inscriptions;
import metier.Personne;

public class EquipeModel 
{
	private Set<Equipe> equipe;
	private String motCle ;
	private Inscriptions inscriptions = Inscriptions.getInscriptions();
	private Equipe equip = inscriptions.createEquipe(" ");
	private String msgError;
	private String inputNom;
	private Map<String, String> AllPersInEquipe;
	private Set<Personne> personne;
	
	public Set<Personne> getPersonne() {
		return personne;
	}
	public void setPersonne(Set<Personne> personne) {
		this.personne = personne;  
	}
	public Map<String, String> getAllPersInEquipe() {
		return AllPersInEquipe;
	}
	public void setAllPersInEquipe(Map<String, String> allPersInEquipe) {
		this.AllPersInEquipe = allPersInEquipe;
	}
	public String getInputNom() {
		return inputNom;
	}
	public void setInputNom(String inputNom) {
		this.inputNom = inputNom;
	}
	public String getMsgError() {
		return msgError;
	}
	public void setMsgError(String msgError) {
		this.msgError = msgError;
	}
	public Set<Equipe> getEquipe() {
		return equipe;
	}
	public void setEquipe(Set<Equipe> equipe) {
		this.equipe = equipe;
	}
	public String getMotCle() {
		return motCle;
	}
	public void setMotCle(String motCle) {
		this.motCle = motCle;
	}
	public Inscriptions getInscriptions() {
		return inscriptions;
	}
	public void setInscriptions(Inscriptions inscriptions) {
		this.inscriptions = inscriptions;
	}
	public Equipe getEquip() {
		return equip;
	}
	public void setEquip(Equipe equip) {
		this.equip = equip;
	}
	

}
