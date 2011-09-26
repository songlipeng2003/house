package controllers.seller;

import controllers.Security;
import models.Seller;
import models.User;
import models.User.UserType;
import play.data.validation.Valid;
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
}
