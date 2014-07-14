package controllers;

import java.io.File;
import java.util.Map;
import java.util.HashMap;

import play.mvc.Controller;
import play.i18n.Lang;

public class Application extends Controller {
	
	private static final Map<String, ProjectInfo> PROJECTS = new HashMap<String, ProjectInfo>();
	
	private static void add(String name, boolean hasEnglish, boolean hasGithub) {
		PROJECTS.put(name, new ProjectInfo(name, hasEnglish, hasGithub));
	}
	
	static {
		add("excel2canvas", true, true);
		add("hypdf4j", true, true);
		add("jquery-formbuilder", true, true);
		add("sendgrid4j", true, true);
		add("papertrailTool", true, true);
		add("flectCommon", true, true);
		add("flectSoap", true, true);
		add("flectSalesforce", true, true);
		add("github-doclet", true, true);
		add("apex-google-api", true, true);
		add("heroku-platform-api", true, true);
		
		add("hypdf-excel", true, true);
		add("papertrail-log-analyze", true, true);
		add("sqlsync", false, true);
		add("sqltool", true, true);
		add("salesforce-fixture", true, true);
		
		add("heroku-buildpack-ffmpeg", true, true);
		
		add("excelReport", true, false);
		add("forceexplorer", true, false);
	}
	
	public static void index(String lang) {
		Lang.change(lang);
		render();
	}
	
	public static void toIndex() {
		String lang = Lang.get();
		index(lang);
	}
	
	public static void toContent(String filename) {
		String lang = Lang.get();
		String path = request.path;
		int idx = path.indexOf('/', 2);
		String newPath = path.substring(0, idx) + "/" + lang + path.substring(idx);
		redirect(newPath);
	}
	
	public static void view(String lang, String filename) {
		Lang.change(lang);
		ProjectInfo project = PROJECTS.get(filename);
		if (project == null) {
			notFound();
		}
		File f = new File("app/views/contents/" + filename + ".html");
		if (!f.exists()) {
			f = new File("app/views/contents/underconstruction.html");
		}
		renderTemplate("contents/" + f.getName(), filename, project);
	}
	
	public static class ProjectInfo {
		
		public String name;
		public boolean hasEnglish;
		public boolean hasGithub;
		
		public ProjectInfo(String name, boolean hasEnglish, boolean hasGithub) {
			this.name = name;
			this.hasEnglish = hasEnglish;
			this.hasGithub = hasGithub;
		}
	}

}