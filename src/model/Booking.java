package model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "booking")
public class Booking {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	
	@ManyToOne
	private User user;
	
	@OneToOne
	private Offer offer;
	
	@OneToOne
	private Transaction transaction;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "bookingDate", nullable = false)
	private Date bookingDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "arrivalDate", nullable = false)
	private Date arrivalDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "departureDate", nullable = false)
	private Date departureDate;
	
	@Column(name = "nbPerson", nullable = false)
	private int nbPerson;
	
	@Column(name = "totalPrice", nullable = false)
	private double totalPrice;

	public Booking() {}

	/**
	 * @param id
	 * @param user
	 * @param offer
	 * @param bookingDate
	 * @param arrivalDate
	 * @param departureDate
	 * @param nbPerson
	 * @param totalPrice
	 */
	public Booking(User user, Offer offer, Transaction transaction, Date arrivalDate, Date departureDate, int nbPerson, double totalPrice) {
		this.bookingDate = new Date();
		this.user = user;
		this.offer = offer;
		this.transaction = transaction;
		this.arrivalDate = arrivalDate;
		this.departureDate = departureDate;
		this.nbPerson = nbPerson;
		this.totalPrice = totalPrice;
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
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the offer
	 */
	public Offer getOffer() {
		return offer;
	}

	/**
	 * @param offer the offer to set
	 */
	public void setOffer(Offer offer) {
		this.offer = offer;
	}

	/**
	 * @return the transaction
	 */
	public Transaction getTransaction() {
		return transaction;
	}

	/**
	 * @param transaction the transaction to set
	 */
	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	/**
	 * @return the bookingDate
	 */
	public Date getBookingDate() {
		return bookingDate;
	}

	/**
	 * @param bookingDate the bookingDate to set
	 */
	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}

	/**
	 * @return the arrivalDate
	 */
	public Date getArrivalDate() {
		return arrivalDate;
	}

	/**
	 * @param arrivalDate the arrivalDate to set
	 */
	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	/**
	 * @return the departureDate
	 */
	public Date getDepartureDate() {
		return departureDate;
	}

	/**
	 * @param departureDate the departureDate to set
	 */
	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}

	/**
	 * @return the nbPerson
	 */
	public int getNbPerson() {
		return nbPerson;
	}

	/**
	 * @param nbPerson the nbPerson to set
	 */
	public void setNbPerson(int nbPerson) {
		this.nbPerson = nbPerson;
	}

	/**
	 * @return the totalPrice
	 */
	public double getTotalPrice() {
		return totalPrice;
	}

	/**
	 * @param totalPrice the totalPrice to set
	 */
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
}
