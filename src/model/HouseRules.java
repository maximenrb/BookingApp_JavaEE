package model;

import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import function.textTools;

@Entity
@Table(name = "houseRules")
public class HouseRules {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;

	@Temporal(TemporalType.TIME)
	@Column(name = "arrivalHour", nullable = false)
	private LocalTime arrivalHour;

	@Temporal(TemporalType.TIME)
	@Column(name = "departureHour", nullable = false)
	private LocalTime departureHour;
	
	@Column(name = "petAllowed", nullable = false)
	private boolean petAllowed;
	
	@Column(name = "partyAllowed", nullable = false)
	private boolean partyAllowed;
	
	@Column(name = "smokeAllowed", nullable = false)
	private boolean smokeAllowed;
	
	
	public HouseRules() {
		super();
	}
	
	/**
	 * @param arrivalHour
	 * @param departureHour
	 * @param petAllowed
	 * @param partyAllowed
	 * @param smokeAllowed
	 */
	public HouseRules(LocalTime arrivalHour, LocalTime departureHour, boolean petAllowed, boolean partyAllowed, boolean smokeAllowed) {
		this.arrivalHour = arrivalHour;
		this.departureHour = departureHour;
		this.petAllowed = petAllowed;
		this.partyAllowed = partyAllowed;
		this.smokeAllowed = smokeAllowed;
	}
	
	public void update(LocalTime arrivalHour, LocalTime departureHour, boolean petAllowed, boolean partyAllowed, boolean smokeAllowed) {
		this.arrivalHour = arrivalHour;
		this.departureHour = departureHour;
		this.petAllowed = petAllowed;
		this.partyAllowed = partyAllowed;
		this.smokeAllowed = smokeAllowed;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the arrivalHour
	 */
	public LocalTime getArrivalHour() {
		return arrivalHour;
	}

	/**
	 * @param arrivalHour the arrivalHour to set
	 */
	public void setArrivalHour(LocalTime arrivalHour) {
		this.arrivalHour = arrivalHour;
	}

	/**
	 * @return the departureHour
	 */
	public LocalTime getDepartureHour() {
		return departureHour;
	}

	/**
	 * @param departureHour the departureHour to set
	 */
	public void setDepartureHour(LocalTime departureHour) {
		this.departureHour = departureHour;
	}

	/**
	 * @return the petAllowed
	 */
	public boolean isPetAllowed() {
		return petAllowed;
	}

	/**
	 * @param petAllowed the petAllowed to set
	 */
	public void setPetAllowed(boolean petAllowed) {
		this.petAllowed = petAllowed;
	}

	/**
	 * @return the partyAllowed
	 */
	public boolean isPartyAllowed() {
		return partyAllowed;
	}

	/**
	 * @param partyAllowed the partyAllowed to set
	 */
	public void setPartyAllowed(boolean partyAllowed) {
		this.partyAllowed = partyAllowed;
	}

	/**
	 * @return the smokeAllowed
	 */
	public boolean isSmokeAllowed() {
		return smokeAllowed;
	}

	/**
	 * @param smokeAllowed the smokeAllowed to set
	 */
	public void setSmokeAllowed(boolean smokeAllowed) {
		this.smokeAllowed = smokeAllowed;
	}

	@Override
	public String toString() {
		return "Arrivée après : " + arrivalHour + " - Départ avant : " + departureHour
				+ "<br>" + "Animaux : " + textTools.getTextFromBool(petAllowed) + " | Fête : " + textTools.getTextFromBool(partyAllowed) + " | Fumeur : " + textTools.getTextFromBool(smokeAllowed);
	}
}
