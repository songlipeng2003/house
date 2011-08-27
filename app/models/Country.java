package models;

import javax.persistence.Entity;

import play.data.validation.Max;
import play.data.validation.MaxSize;
import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class Country extends Model {
	@Required
	@MaxSize(value = 64)
	public String name;
}
