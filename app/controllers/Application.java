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
		add("sendgrid4j", true, true);
		add("papertrailTool", true, true);
		add("flectCommon", true, true);
		add("flectSoap", true, true);
		add("flectSalesforce", true, true);
		
		add("papertrail-log-analyze", true, true);
		add("sqlsync", false, true);
		
		add("heroku-buildpack-ffmpeg", true, true);
		
		add("excelReport", true, false);
		add("forceexplorer", true, false);
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