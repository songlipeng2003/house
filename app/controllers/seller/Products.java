package controllers.seller;

import java.io.File;
import java.util.List;

import models.Product;
import models.ProductImage;
import play.Play;
import play.data.validation.Valid;
import play.libs.Files;
import play.libs.Images;

import com.mysql.jdbc.Messages;

/**
 * 卖家产品管理
 * 
 * @author ray
 * 
 */
public class Products extends Application {

	/**
	 * 产品列表
	 */
	public static void index() {

		List<Product> products = Product.findAll();
		render(products);
	}

	/**
	 * 添加产品
	 */
	public static void add() {
		render();
	}

	public static void save(@Valid Product product, File picture) {
		if (validation.hasErrors()) {
			params.flash();
			validation.keep();
			add();
		}
		if (picture != null) {
			ProductImage productImage = ProductImage.saveProductImage(picture,
					null);
			product.image = productImage;
		}
		product.save();
		flash.success(Messages.getString("product.addsuccess"));
		index();
	}

	public static void edit(Long id) {
		Product product = Product.findById(id);
		notFoundIfNull(product);
		render(product);
	}

	public static void update(@Valid Product product, File picture) {
		if (validation.hasErrors()) {
			params.flash();
			validation.keep();
			render("@edit", product);
		}
		if (picture != null) {
			if (product.image != null) {
				product.image.delete();
			}
			ProductImage productImage = ProductImage.saveProductImage(picture,
					product);
			product.image = productImage;
		}
		product.save();
		flash.success(Messages.getString("product.addsuccess"));
		index();
	}

	public static void delete(Long id) {

		Product product = Product.findById(id);
		notFoundIfNull(product);
		product.delete();
		flash.success(Messages.getString("pproduct.delsuccess"));
		index();
	}

}
