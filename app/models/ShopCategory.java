package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.data.validation.MaxSize;
import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class ShopCategory extends Model {
	@ManyToOne
	public User user;
	
	@Required
	@MaxSize(value=255)
	public String name;
}
