package models;

import play.data.validation.MaxSize;
import play.data.validation.Required;
import play.db.jpa.Model;

public class Admin extends Model{
	@MaxSize(value = 32)
	public String username;

	@Required
	@MaxSize(value = 64)
	public String password;

	@Required
	@MaxSize(value = 64)
	public String email;
}
