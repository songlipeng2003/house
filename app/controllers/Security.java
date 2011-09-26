package controllers;

import java.util.Date;

import models.User;
import models.User.UserType;

public class Security extends Secure.Security {
	static boolean authenticate(String username, String password) {
		User user = User.find("byEmailAndPassword", username, password).first();

		if (user != null) {
			user.lastLogin = new Date();
			user.loginTimes = user.loginTimes + 1;
			user.save();
		}

		return true;
	}

	static boolean check(String profile) {
		User user = User.find("byEmail", connected()).first();
		if (profile.equals("admin")) {
			return user.userType == UserType.ADMIN;
		} else if (profile.equals("seller")) {
			return user.userType == UserType.SELLER;
		} else if (profile.equals("buyer")) {
			return user.userType == UserType.BUYER;
		} else {
			return false;
		}
	}

	static void onAuthenticated() {
	}

	static void onDisconnect() {
	}

	static void onDisconnected() {
	}

	static void onCheckFailed(String profile) {
		forbidden();
	}

}