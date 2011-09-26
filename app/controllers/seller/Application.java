package controllers.seller;

import models.User;
import models.User.UserType;
import play.data.validation.Email;
import play.data.validation.Required;
import play.mvc.Before;

import com.mysql.jdbc.Messages;

public class Application extends controllers.Application {
	@Before(unless = { "register", "reg", "login", "authenticate" })
	static void checkUser() {
		if (!session.contains(Application.SESSION_USER)) {
			flash.error(Messages.getString("secure.mustLogin"));
			login();
		}
	}

	public final static void authenticate(@Required @Email String email,
			@Required String password) {
		if (validation.hasErrors()) {
			params.flash();
			validation.keep();
			login();
		}
		User user = User.login(email, password, UserType.SELLER, request.host);

		if (user == null) {
			flash.error(Messages.getString("secure.loginFail"));
			params.flash();
			login();
		}

		session.put(Application.SESSION_USER, user.getId());
		flash.success(Messages.getString("secure.loginSuccess"));
		index();
	}

	public final static void login() {
		render();
	}

	public final static void logout() {
		session.clear();
		login();
	}
	
	public static void index(){
		render();
	}
}