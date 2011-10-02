package controllers;

import play.mvc.Controller;

public class Category extends Controller {

	public static void view(Long id) {
		models.Category category = models.Category.findById(id);

		render(category);
	}

}