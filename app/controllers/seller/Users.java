package controllers.seller;

import controllers.Security;
import models.Country;
import models.Seller;
import models.User;
import models.User.UserType;
import play.data.validation.Required;
import play.data.validation.Valid;
import play.i18n.Messages;
import play.mvc.Controller;
import play.mvc.With;

@With(Security.class)
public class Users extends Controller {

	public static void register() {
		render();
	}

	public static void reg(@Valid User user, @Valid Seller seller) {
		validation.equals(user.password, user.rePassword);

		if (validation.hasErrors()) {
			params.flash(); // add http parameters to the flash scope
			validation.keep(); // keep the errors for the next request

			register();
		} else {
			user.userType = UserType.SELLER;
			user.create();
			seller.user = user;

			seller.create();

			flash.success("Thanks for registation %s", user.username);
			render();
		}
	}

	/**
	 * 个人信息配置
	 */
	public static void setting() {
		User user = Application.getSessionUser();
		Seller seller=Seller.findById(user.id);
		render(user,seller);
	}

	/**
	 * 保存个人信息配置
	 */
	public static void savebasesetting(String firstName, String lastName,@Valid Seller seller) {
		User user = Application.getSessionUser();
		user.firstName = firstName;
		user.lastName = lastName;
		user.save();

		Seller seller2=Seller.findById(user.id);
		seller2.companyAddress=seller.companyAddress;
		seller2.companyName=seller.companyName;
		seller2.category=seller.category;
		seller2.mobile=seller.mobile;
		seller2.save();
		flash.success("Save successfully");
		setting();
	}
	
	
	public static void resetpass(){
		render();
	}

	/**
	 * 修改密码信息
	 */
	public static void savepasssetting(@Required String oldpassword,
			@Required String password, @Required String repassword) {
		validation.equals(password, repassword)
				.message(Messages.get("user.passrepass.notequip"))
				.key("password");
		User user = Application.getSessionUser();
		validation.equals(user.password, oldpassword)
				.message(Messages.get("user.oldpassword.notcorrect"))
				.key("oldpassword");
		if (validation.hasErrors()) {
			validation.keep();
			resetpass();
		}
		user.password = password;
		user.save();

		flash.success(Messages.get("user.pass.reset"));
		resetpass();
	}
}
