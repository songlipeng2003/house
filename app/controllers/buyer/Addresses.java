package controllers.buyer;

import java.util.List;

import models.Address;
import models.Country;
import models.Favorite;
import play.data.validation.Required;
import play.data.validation.Valid;
import play.mvc.Controller;
import utils.Paginator;

/**
 * Buyer's addresses管理
 * 
 * @author ray
 * 
 */
public class Addresses extends Controller {

	/**
	 * 地址列表
	 */
	public static void index() {
		List<Address> addresses = Address.find("user=? order by updated desc",
				Member.getSessionUser()).fetch();
		Paginator paginator=new Paginator(0, 10, 11, "buyer.Addresses.index");
		render(addresses,paginator);
	}

	/**
	 * 添加或编辑地址信息
	 * 
	 * @param id
	 */
	public static void form(Long id) {
		List<Country> countrys = Country.findAll();
		renderArgs.put("countrys", countrys);
		Address address = new Address();
		if (id == null) {
			render();
		}

		address = Address.findById(id);
		render(address);
	}

	/**
	 * 保存地址信息
	 * 
	 * @param address
	 */
	public static void save(@Valid Address address) {
		if (validation.hasErrors()) {
			validation.keep(); // keep the errors for the next request
			List<Country> countrys = Country.findAll();
			renderArgs.put("countrys", countrys);
			render("@form", address);
		}
		if (address.country != null && address.country.id != null) {
			if (address.country.getId() != null) {
				Country c = Country.findById(address.country.getId());
				address.country = c;
			}
		}
		address.user = Member.getSessionUser();
		address.save();

		flash.success("Address saved successfully!");
		index();
	}

	public static void delete(Long id) {
		Address address = Address.findById(id);
		notFoundIfNull(address);
		address.delete();

		flash.success("This Address Deleted  successfully!");
		index();
	}
}
