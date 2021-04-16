package model;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "offer")
public class Offer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private int id;
	
	@ManyToOne
	private User user;
	
	@ManyToOne
	private Accommodation accommodation;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "addDate", nullable = false)
	private Date addDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "start", nullable = false)
	private Calendar startAvailability;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "end", nullable = false)
	private Calendar endAvailability;
	
	@Column(name = "pricePerNight", nullable = false)
	private double pricePerNight;
	
	@Column(name = "cleaningFee", nullable = false)
	private double cleaningFee;
	
	public Offer() {}

	public Offer(User user, Accommodation accommodation, Calendar startAvailability, Calendar endAvailability, double pricePerNight, double cleaningFee) {
		this.user = user;
		this.accommodation = accommodation;
		this.addDate = new Date();
		this.startAvailability = startAvailability;
		this.endAvailability = endAvailability;
		this.pricePerNight = pricePerNight;
		this.cleaningFee = cleaningFee;
	}
	
	public void update(Calendar startAvailability, Calendar endAvailability, double pricePerNight, double cleaningFee) {
		this.startAvailability = startAvailability;
		this.endAvailability = endAvailability;
		this.pricePerNight = pricePerNight;
		this.cleaningFee = cleaningFee;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Accommodation getAccommodation() {
		return accommodation;
	}

	public void setAccommodation(Accommodation accommodation) {
		this.accommodation = accommodation;
	}

	/**
	 * @return the addDate
	 */
	public Date getAddDate() {
		return addDate;
	}

	/**
	 * @param addDate the addDate to set
	 */
	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}

	public Calendar getStartAvailability() {
		return startAvailability;
	}

	public void setStartAvailability(Calendar startAvailability) {
		this.startAvailability = startAvailability;
	}

	public Calendar getEndAvailability() {
		return endAvailability;
	}

	public void setEndAvailability(Calendar endAvailability) {
		this.endAvailability = endAvailability;
	}

	public double getPricePerNight() {
		return pricePerNight;
	}

	public void setPricePerNight(double pricePerNight) {
		this.pricePerNight = pricePerNight;
	}

	/**
	 * @return the cleaningFee
	 */
	public double getCleaningFee() {
		return cleaningFee;
	}

	/**
	 * @param cleaningFee the cleaningFee to set
	 */
	public void setCleaningFee(double cleaningFee) {
		this.cleaningFee = cleaningFee;
	}
	
}