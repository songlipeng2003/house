package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import play.data.validation.Email;
import play.data.validation.MaxSize;
import play.data.validation.Password;
import play.data.validation.Required;
import play.db.jpa.Model;
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

	@Transient
	@Password
	public String rePassword;

	@Transient
	public String newPassword;

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

	@Enumerated(EnumType.ORDINAL)
	public UserType userType = UserType.BUYER;

	@PrePersist
	void onPrePersist() {
		created = new Date();
	}

	@PreUpdate
	void onPreUpdate() {
		updated = new Date();
	}

	public enum UserType {
		BUYER, SELLER, ADMIN
	}

	public static User login(String email, String password, UserType userType,
			String ip) {
		User user = User.find("byEmailAndPasswordAndUserType", email, password,
				userType).first();

		if (user != null) {
			user.lastIp = ip;
			user.lastLogin = new Date();
			user.loginTimes = user.loginTimes + 1;
			user.save();
		}

		return user;
	}
}
