package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.data.validation.MaxSize;
import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class User extends Model {
	@ManyToOne
	public Category category;

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
}
