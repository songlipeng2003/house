package controllers.seller;

import java.lang.reflect.Constructor;
import java.util.List;

import models.Address;
import models.Product;
import play.data.binding.Binder;
import play.db.Model;
import play.exceptions.TemplateNotFoundException;
import play.i18n.Messages;
import controllers.CRUD.ObjectType;

public class Products extends Application {

	public static void save(Product product) throws Exception {
		if (product.validateAndCreate()) {
			params.flash();
			validation.keep();
			login();
		}

		flash.success("save successfully!");
	}

	public static void blank() throws Exception {
		ObjectType type = ObjectType.forClass("models.Product");
		notFoundIfNull(type);
		Constructor<?> constructor = type.entityClass.getDeclaredConstructor();
		constructor.setAccessible(true);
		Model object = (Model) constructor.newInstance();
		try {
			render(type, object);
		} catch (TemplateNotFoundException e) {
			render("CRUD/blank.html", type, object);
		}
	}

	public static void create() throws Exception {
		ObjectType type = ObjectType.forClass("models.Product");
		notFoundIfNull(type);
		Constructor<?> constructor = type.entityClass.getDeclaredConstructor();
		constructor.setAccessible(true);
		Model object = (Model) constructor.newInstance();
		Binder.bind(object, "object", params.all());
		validation.valid(object);
		if (validation.hasErrors()) {
			renderArgs.put("error", Messages.get("crud.hasErrors"));
			try {
				render(request.controller.replace(".", "/") + "/blank.html",
						type, object);
			} catch (TemplateNotFoundException e) {
				render("CRUD/blank.html", type, object);
			}
		}
		object._save();
		flash.success(Messages.get("crud.created", type.modelName));
		if (params.get("_save") != null) {
			redirect(request.controller + ".list");
		}
		if (params.get("_saveAndAddAnother") != null) {
			redirect(request.controller + ".blank");
		}
		redirect(request.controller + ".show", object._key());
	}

	public static void list(int page, String search, String searchFields,
			String orderBy, String order) throws Exception {
		ObjectType type = ObjectType.forClass("models.Product");
		notFoundIfNull(type);
		if (page < 1) {
			page = 1;
		}
		List<Model> objects = type.findPage(page, search, searchFields,
				orderBy, order, (String) request.args.get("where"));
		Long count = type.count(search, searchFields,
				(String) request.args.get("where"));
		Long totalCount = type.count(null, null,
				(String) request.args.get("where"));
		try {
			render(type, objects, count, totalCount, page, orderBy, order);
		} catch (TemplateNotFoundException e) {
			render("CRUD/list.html", type, objects, count, totalCount, page,
					orderBy, order);
		}
	}
}
