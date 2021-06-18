package org.gotham.bo;

import java.time.LocalDate;

public class Page {
	
	private String titre;
	private int nbHabitants;
	private String contenu;
	private LocalDate dateConseil;
	private int numeroPage;
	
	//constructeur
	public Page()
	{
		
	}
	
	public Page(String titre, int nbHabitants, String contenu, LocalDate dateConseil, int numeroPage) {
		super();
		this.titre = titre;
		this.nbHabitants = nbHabitants;
		this.contenu = contenu;
		this.dateConseil = dateConseil;
		this.numeroPage = numeroPage;
	}

	public Page(String titre, int nombreHbts, String contenu, LocalDate date_conseil) {
		super();
		this.titre = titre;
		this.nbHabitants = nbHabitants;
		this.contenu = contenu;
		this.dateConseil = dateConseil;
	}

	//getter et setter
	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public int getNbHabitants() {
		return nbHabitants;
	}

	public void setNbHabitants(int nbHabitants) {
		this.nbHabitants = nbHabitants;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	public LocalDate getDateConseil() {
		return dateConseil;
	}

	public void setDateConseil(LocalDate dateConseil) {
		this.dateConseil = dateConseil;
	}

	//toString
	@Override
	public String toString() {
		return "Page [titre=" + titre + ", nbHabitants=" + nbHabitants + ", contenu=" + contenu + ", dateConseil="
				+ dateConseil + "]";
	}

	//HashCode
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((contenu == null) ? 0 : contenu.hashCode());
		result = prime * result + ((dateConseil == null) ? 0 : dateConseil.hashCode());
		result = prime * result + nbHabitants;
		result = prime * result + ((titre == null) ? 0 : titre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Page other = (Page) obj;
		if (contenu == null) {
			if (other.contenu != null)
				return false;
		} else if (!contenu.equals(other.contenu))
			return false;
		if (dateConseil == null) {
			if (other.dateConseil != null)
				return false;
		} else if (!dateConseil.equals(other.dateConseil))
			return false;
		if (nbHabitants != other.nbHabitants)
			return false;
		if (titre == null) {
			if (other.titre != null)
				return false;
		} else if (!titre.equals(other.titre))
			return false;
		return true;
	}

	public int getNumeroPage() {
		return numeroPage;
	}

	public void setNumeroPage(int numeroPage) {
		this.numeroPage = numeroPage;
	}
	

}
