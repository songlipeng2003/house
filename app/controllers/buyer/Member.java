package controllers.buyer;

import java.util.Date;
import java.util.List;

import models.Country;
import models.User;
import play.data.validation.Email;
import play.data.validation.Required;
import play.data.validation.Valid;
import play.mvc.Before;
import play.mvc.Controller;
import validation.Unique;
import controllers.Application;

public class Member extends Controller {
	@Before(unless = { "register", "reg", "login", "signin" })
	static void checkUser() {
		if (!session.contains("userid")) {
			flash.error("Please log in first");
			login();
		}
	}

	static User getSessionUser() {
		return User.findById(Long.parseLong(session.get("userid")));
	}

	public static void index() {
		render();
	}

	/**
	 * 订单管理
	 */
	public static void orderlist() {
		render();
	}

	/**
	 * My favorites
	 */
	public static void favoritelist() {
		render();
	}

	/**
	 * 地址信息管理
	 */
	public static void addresslist() {
		render();
	}

	/**
	 * 个人信息配置
	 */
	public static void setting() {
		User user = getSessionUser();
		List<Country> countrys = Country.findAll();
		render(user, countrys);
	}

	/**
	 * 保存个人信息配置
	 */
	public static void savebasesetting(String firstName, String lastName,
			Country country) {
		User user = getSessionUser();
		user.firstName = firstName;
		user.lastName = lastName;
		if (country.getId() != null) {
			Country c = Country.findById(country.getId());
			user.country = c;
		}
		user.save();
		flash.success("Save successfully");
		setting();
	}

	/**
	 * 修改密码信息
	 */
	public static void savepasssetting(@Required String oldpassword,
			@Required String password, @Required String repassword) {
		validation.equals(password, repassword)
				.message("password and re-password is not equil")
				.key("password");
		User user = getSessionUser();
		validation.equals(user.password, oldpassword)
				.message("Old password are not correct").key("oldpassword")
				.key("oldpassword");
		renderArgs.put("show", "changepass");
		if (validation.hasErrors()) {
			render("@setting", user);
		}
		user.password = password;
		user.save();

		flash.success("Password updated");
		setting();
	}

	/**
	 * 网站消息管理
	 */
	public static void messagelist() {
		render();
	}

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
		validation.equals(user.password, repassword)
				.message("password and re-password is not equil")
				.key("user.password");

		if (validation.hasErrors()) {
			params.flash(); // add http parameters to the flash scope
			validation.keep(); // keep the errors for the next request
			register();
		}
		user.isAdmin = false;
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

	/**
	 * 登录处理
	 * 
	 * @param email
	 * @param password
	 */
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
		user.lastIp = request.host;
		user.lastLogin = new Date();
		user.loginTimes = user.loginTimes + 1;
		user.save();

		session.put("username", user.username);
		session.put("useremail", user.email);
		session.put("userid", user.getId());
		flash.success("Login success,Welcome %s", user.username);
		render();
	}

	/**
	 * 注销系统
	 */
	public static void logout() {
		session.clear();
		Application.index();
	}
}
