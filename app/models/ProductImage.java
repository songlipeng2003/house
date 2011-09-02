package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class ProductImage extends Model {
	@Required
	@ManyToOne
	public Product product;

	public String filename;

	public String path;

	public String url;
}
