package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import play.data.validation.MaxSize;
import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class User extends Model {
	@ManyToOne
	public Country country;

	@MaxSize(value = 32)
	public String username;

	@Required
	@MaxSize(value = 64)
	public String password;

	@Required
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
	
	public String lastIp;
	
	public Boolean isAdmin;
}
