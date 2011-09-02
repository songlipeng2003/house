package controllers.admin;

import models.Country;
import controllers.CRUD;
import play.*;
import play.mvc.*;

@CRUD.For(Country.class)
public class Countries extends CRUD {
}
