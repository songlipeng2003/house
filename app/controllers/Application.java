package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Application extends Controller {
	public final static String SESSION_USER = "userId";

	public static void index() {
		render();
	}

}