package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import play.data.validation.MaxSize;
import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class Address extends Model {
	@ManyToOne
	public Country country;

	@ManyToOne
	public User user;

	@MaxSize(value = 256)
	public String province;

	@MaxSize(value = 256)
	public String city;

	@Required
	@MaxSize(value = 256)
	public String address;

	@Required
	@MaxSize(value = 64)
	public String firstName;

	@Required
	@MaxSize(value = 64)
	public String lastName;

	@Required
	@MaxSize(value = 32)
	public String phone;

	@Temporal(TemporalType.TIMESTAMP)
	public Date created;

	@Temporal(TemporalType.TIMESTAMP)
	public Date updated;

	@PrePersist
	void onPrePersist() {
		created = new Date();
	}

	@PreUpdate
	void onPreUpdate() {
		updated = new Date();
	}
}
