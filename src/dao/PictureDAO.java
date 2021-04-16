package dao;

import javax.ejb.Local;

import model.Accommodation;

@Local
public interface PictureDAO {

	public void createPicture(Accommodation accommodation, String url);
	
}
