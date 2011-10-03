package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import play.data.validation.MaxSize;
import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class Shop extends Model {
	@ManyToOne
	public User user;

	public String key;

	@Required
	public String name;

	@MaxSize(value = 64000)
	public String description;

	@Temporal(TemporalType.TIMESTAMP)
	public Date created;

	@PrePersist
	void onPrePersist() {
		created = new Date();
	}
}
