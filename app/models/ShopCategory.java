package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;

@Entity
public class ShopCategory extends Model {
	@ManyToOne
	public User user;
}
