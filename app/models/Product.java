package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.data.validation.Max;
import play.data.validation.MaxSize;
import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class Product extends Model {
	@ManyToOne
	public Country country;

	@Required
	@MaxSize(value = 256)
	public String key_name;

	@Required
	@MaxSize(value = 256)
	public String name;

	public Double width;
	public Double height;
	public Double high;

	@Max(value = 64000)
	public String description;
}
