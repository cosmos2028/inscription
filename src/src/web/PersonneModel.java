package web;

import java.util.Set;

import metier.Inscriptions;
import metier.Personne;

public class PersonneModel 
{
	private String mode = "ajouter";
	private String msgError;
	private Set<Personne> personnes;
	private String motCle ;
	private Inscriptions inscriptions = Inscriptions.getInscriptions();
	private Personne pers = inscriptions.createPersonne("", "", "");

	
	
	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}
	
	public String getMsgError() {
		return msgError;
	}

	public void setMsgError(String msgError) {
		this.msgError = msgError;
	}
	
	public String getMotCle() {
		return motCle;
	}

	public Personne getPers() {
		return pers;
	}

	public void setPers(Personne pers) {
		this.pers = pers;
	}

	public void setMotCle(String motCle) {
		this.motCle = motCle;
	}

	public Set<Personne> getPersonnes() {
		return personnes;
	}

	public void setPersonnes(Set<Personne> personnes) {
		this.personnes = personnes;
	}

}
