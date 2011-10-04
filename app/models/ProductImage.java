package models;

import java.io.File;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.Play;
import play.data.validation.Required;
import play.db.jpa.Model;
import play.libs.Files;

@Entity
public class ProductImage extends Model {
	@Required
	@ManyToOne
	public Product product;

	public String filename;

	public String path;

	public String url;

	public static ProductImage saveProductImage(File image, Product product) {
		/*Images.resize(image,
				Play.getFile("public/attachment/" + image.getName()), 120,
				120);*/
		Files.copy(image, Play.getFile("public/attachment/" + image.getName()));
		ProductImage productImage = new ProductImage();
		productImage.filename = image.getName();
		productImage.path = "public/attachment/" + image.getName();
		productImage.product = product;
		productImage.save();
		return productImage;
	}
}
