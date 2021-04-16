package model;

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
@Table(name = "transaction")
public class Transaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private long id;

	@ManyToOne
	private User sender;
	
	@ManyToOne
	private User receiver;
	
	@Column
	private double amount;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date date;
	
	public Transaction() {}

	public Transaction(User sender, User receiver, double amount) {
		this.sender = sender;
		this.receiver = receiver;
		this.amount = amount;
		this.date = new Date();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}
	

}
