package controllers.seller;

import java.util.List;

import controllers.buyer.Member;
import models.Address;
import models.Country;
import models.Shop;
import models.ShopCategory;
import models.User;
import play.data.validation.Valid;
import play.i18n.Messages;
import play.mvc.Controller;
import utils.Paginator;

/**
 * 卖家商铺信息管理
 * 
 * @author ray
 * 
 */
public class Shopinfo extends Application {

	public static void shopinfo() {
		User user = getSessionUser();
		Shop shop = Shop.find("byUser", user).first();
		render(shop);
	}

	public static void saveshopinfo(@Valid Shop shop) {
		if (validation.hasErrors()) {
			params.flash();
			validation.keep();
			render("@shopinfo", shop);
		}
		if (shop.id == null) {
			User user = getSessionUser();
			shop.user = user;
		}
		shop.save();
		flash.success(Messages.get("shop.savesuccess"));
		shopinfo();
	}

	/**
	 * 商店分类
	 */
	public static void categorylist() {
		List<ShopCategory> shopcates = ShopCategory.find(
				"user=?", getSessionUser()).fetch();
		render(shopcates);
	}

	public static void shopcateform(Long id) {
		ShopCategory shopcate = new ShopCategory();
		if (id == null) {
			render();
		}

		shopcate = ShopCategory.findById(id);
		render(shopcate);
	}

	public static void savecategory(@Valid ShopCategory shopcate) {
		if (validation.hasErrors()) {
			validation.keep(); // keep the errors for the next request
			render("@shopcateform", shopcate);
		}
		shopcate.user = getSessionUser();
		shopcate.save();

		flash.success(Messages.get("shopcate.savesuccess"));
		categorylist();
	}
	
	public static void deleteshopcate(Long id){
		ShopCategory shopcate = ShopCategory.findById(id);
		notFoundIfNull(shopcate);
		shopcate.delete();
		flash.success(Messages.get("shopcate.delsuccess"));
		categorylist();
	}

}
