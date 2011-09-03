package models;

import javax.persistence.Entity;

import play.data.validation.MaxSize;
import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class Config extends Model {
	@Required
	@MaxSize(256)
	public String key_name;

	@Required
	@MaxSize(64000)
	public String value;
}
