package controllers.buyer;

import java.util.List;

import models.Address;
import models.Favorite;
import play.mvc.Controller;

public class Favorites extends Controller {

	/**
	 * 收藏夹列表
	 */
	public static void index(int type) {
		List<Favorite> favorites = Favorite.find("user=? order by id desc",
				Member.getSessionUser()).fetch();
		render(favorites);
	}

	public static void addnote(Long id) {
		Favorite favorite = Favorite.findById(id);
		notFoundIfNull(favorite);
		flash.success("Note saved successfully!");
		index(0);
	}

	public static void delete(Long id) {
		Favorite favorite = Favorite.findById(id);
		notFoundIfNull(favorite);
		favorite.delete();

		flash.success("This favorite Deleted  successfully!");
		index(0);
	}
}
