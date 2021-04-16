package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "address")
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	
	@Column(name = "streetAndNumber", nullable = false)
	private String streetAndNumber;
	
	@Column(name = "complement", nullable = true)
	private String complement;
	
	@Column(name = "postalCode", nullable = false)
	private String postalCode;
	
	@Column(name = "city", nullable = false)
	private String city;
	
	@Column(name = "country", nullable = false)
	private String country;
    
    public Address() {}
    
    public Address(String streetAndNumber, String complement, String postalCode, String city, String country) {
		super();
		this.streetAndNumber = streetAndNumber;
		this.complement = complement;
		this.postalCode = postalCode;
		this.city = city;
		this.country = country;
	}
    
    public void update(String streetAndNumber, String complement, String postalCode, String city, String country) {
		this.streetAndNumber = streetAndNumber;
		this.complement = complement;
		this.postalCode = postalCode;
		this.city = city;
		this.country = country;
    }

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the streetAndNumber
	 */
	public String getStreetAndNumber() {
		return streetAndNumber;
	}

	/**
	 * @return the complement
	 */
	public String getComplement() {
		return complement;
	}

	/**
	 * @return the postalCode
	 */
	public String getPostalCode() {
		return postalCode;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @param streetAndNumber the streetAndNumber to set
	 */
	public void setStreetAndNumber(String streetAndNumber) {
		this.streetAndNumber = streetAndNumber;
	}

	/**
	 * @param complement the complement to set
	 */
	public void setComplement(String complement) {
		this.complement = complement;
	}

	/**
	 * @param postalCode the postalCode to set
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	public String toString() {
    	return streetAndNumber + ",\n" + complement + "\n" + postalCode + " " + city + ",\n" + country;
    }

}
