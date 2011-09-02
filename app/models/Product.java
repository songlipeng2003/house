package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import play.data.validation.Max;
import play.data.validation.MaxSize;
import play.data.validation.Min;
import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class Product extends Model {
	@ManyToOne
	public Country country;

	@ManyToOne
	public ProductImage image;

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

	@Temporal(TemporalType.TIMESTAMP)
	public Date created;

	@Temporal(TemporalType.TIMESTAMP)
	public Date updated;

	public Boolean isOn;
	
	@Required
	@Min(1)
	public Double price;

}
