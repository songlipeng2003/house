package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import play.db.jpa.Model;

@Entity
public class Shop extends Model {
	@ManyToOne
	public User user;
	
	public String key_name;
	
	public String name;
	
	public String description;
	
	@Temporal(TemporalType.TIMESTAMP)
	public Date created;
}
