package controllers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import play.mvc.Controller;
import play.i18n.Lang;

public class Application extends Controller {
	
	private static final List<String> PROJECT_LIST = new ArrayList<String>();
	
	static {
		PROJECT_LIST.add("sendgrid4j");
		PROJECT_LIST.add("papertrailTool");
		PROJECT_LIST.add("excel2canvas");
		PROJECT_LIST.add("flectCommon");
		PROJECT_LIST.add("flectSoap");
		PROJECT_LIST.add("flectSalesforce");
		PROJECT_LIST.add("papertrail-log-analyze");
	}
	
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
		if (!PROJECT_LIST.contains(filename)) {
			notFound();
		}
		File f = new File("app/views/contents/" + filename + ".html");
		if (!f.exists()) {
			f = new File("app/views/contents/underconstruction.html");
		}
		renderTemplate("contents/" + f.getName(), filename);
	}

}