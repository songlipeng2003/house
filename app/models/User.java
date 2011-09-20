package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import play.data.validation.Email;
import play.data.validation.MaxSize;
import play.data.validation.Password;
import play.data.validation.Required;
import play.db.jpa.Model;
import utils.StringUtils;
import validation.Unique;

@Entity
public class User extends Model {
	@ManyToOne
	public Country country;

	@MaxSize(value = 32)
	@Unique
	public String username;

	@Required
	@MaxSize(value = 64)
	@Password
	public String password;

	@Required
	@Email
	@Unique
	@MaxSize(value = 64)
	public String email;

	@MaxSize(value = 32)
	public String firstName;

	@MaxSize(value = 32)
	public String lastName;

	@Temporal(TemporalType.TIMESTAMP)
	public Date created;

	@Temporal(TemporalType.TIMESTAMP)
	public Date updated;

	@Temporal(TemporalType.TIMESTAMP)
	public Date lastLogin;

	public Integer loginTimes = 0;

	public String lastIp;

	public Boolean isAdmin = false;

	@PrePersist
	void onPrePersist() {
		created = new Date();
	}

	@PreUpdate
	void onPreUpdate() {
		updated = new Date();
	}

	public static User findByUsername(String username) {
		return User.find("byUsername", username).first();
	}
}
