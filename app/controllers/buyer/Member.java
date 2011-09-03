package controllers.buyer;

import controllers.Application;
import models.User;
import play.data.validation.Email;
import play.data.validation.Required;
import play.data.validation.Valid;
import play.mvc.Controller;

public class Member extends Controller {

	/**
	 * 进入注册页面
	 */
	public static void register() {
		render();
	}

	/**
	 * 用户注册
	 */
	public static void reg(@Valid User user, @Required String repassword) {
		validation.equals(user.password, repassword).message(
				"password and re-password is not equil");
		;
		if (validation.hasErrors()) {
			params.flash(); // add http parameters to the flash scope
			validation.keep(); // keep the errors for the next request
			register();
		}
		user.create();
		flash.success("Thanks for registation %s", user.username);
		render();
	}

	/**
	 * 进入登录页面
	 */
	public static void login() {
		render();
	}

	public static void signin(@Required @Email String email,
			@Required String password) {
		if (validation.hasErrors()) {
			params.flash(); // add http parameters to the flash scope
			validation.keep(); // keep the errors for the next request
			login();
		}
		User user = User.find("byEmailAndPassword", email, password).first();
		if (user == null) {
			flash.error("sign in failed,please check your email or password",
					"");
			params.flash(); // add http parameters to the flash scope
			login();
		}
		session.put("username", user.username);
		session.put("user", user);
		flash.success("Login success,Welcome %s", user.username);
		render();
	}

	public static void logout() {
		session.clear();
		Application.index();
	}
}
