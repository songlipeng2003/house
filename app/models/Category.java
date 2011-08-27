package models;

import javax.persistence.Entity;

import play.data.validation.MaxSize;
import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class Category extends Model {
	@Required
	public String key;

	@Required
	@MaxSize(value = 32)
	public String name;
}
