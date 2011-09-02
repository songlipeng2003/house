package controllers.admin;

import models.Category;
import controllers.CRUD;
import play.*;
import play.mvc.*;

@CRUD.For(Category.class)
public class Categories extends CRUD {
}
