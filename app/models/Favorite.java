package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import play.db.jpa.Model;

@Entity
public class Favorite extends Model {

	@ManyToOne
	public User user;

	@ManyToOne
	public Shop shop;

	@ManyToOne
	public Product product;

	public String note;

	@Temporal(TemporalType.TIMESTAMP)
	public Date created;

}
