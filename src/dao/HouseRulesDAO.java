package dao;

import java.time.LocalTime;

import javax.ejb.Local;

import model.HouseRules;

@Local
public interface HouseRulesDAO {
	
	public void createHouseRules(HouseRules houseRules);
	
	public HouseRules createHouseRules(LocalTime arrivalHour, LocalTime departureHour, boolean petAllowed, boolean partyAllowed, boolean smokeAllowed);

	public void updateHouseRules(HouseRules houseRules);
}
