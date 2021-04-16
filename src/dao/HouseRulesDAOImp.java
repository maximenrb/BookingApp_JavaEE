package dao;

import java.time.LocalTime;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.HouseRules;

@Stateless
public class HouseRulesDAOImp implements HouseRulesDAO {

	@PersistenceContext(unitName = "pu")
	private EntityManager em;

	@Override
	public void createHouseRules(HouseRules houseRules) {
		em.persist(houseRules);
	}
	
	@Override
	public HouseRules createHouseRules(LocalTime arrivalHour, LocalTime departureHour, boolean petAllowed, boolean partyAllowed, boolean smokeAllowed) {
		HouseRules houseRules = new HouseRules(arrivalHour, departureHour, petAllowed, partyAllowed, smokeAllowed);
		em.persist(houseRules);
		
		return houseRules;
	}

	@Override
	public void updateHouseRules(HouseRules houseRules) {
		em.merge(houseRules);
	}
}
