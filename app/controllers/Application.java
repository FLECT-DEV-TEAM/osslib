package controllers;

import java.io.File;

import play.mvc.Controller;
import play.i18n.Lang;

public class Application extends Controller {
	
	public static void index() {
		render();
	}
	
	public static void toIndex() {
		index();
	}
	
	public static void changeLang(String lang) {
		Lang.change(lang);
		if (!Common.redirectToPrev()) {
			index();
		}
	}
	
	public static void view(String filename) {
		File f = new File("app/views/application/" + filename);
		if (!f.exists()) {
			filename = "underconstruction.html";
		}
		renderTemplate("application/" + filename);
	}

}