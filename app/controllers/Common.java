package controllers;

import play.Logger;	
import java.util.Locale;

import play.mvc.Scope.Session;
import play.mvc.Http.Request;
import play.mvc.Http.Header;
import play.mvc.results.Unauthorized;
import play.mvc.results.BadRequest;
import play.mvc.results.Redirect;

public class Common {
	
	public static boolean redirectToPrev() {
		String referer = getRequestReferer();
		if (referer != null) {
			throw new Redirect(referer);
		}
		//正常系は例外となるのでtrueが変返ることはない
		return false;
	}
	
	public static boolean isIE() {
		Request request = Request.current();
		if (request == null) {
			throw new IllegalStateException();
		}
		Header h = request.headers.get("user-agent");
		return h != null && h.value().indexOf("MSIE") != -1;
	}
	
	public static String getRequestReferer() {
		Request request = Request.current();
		if (request == null) {
			throw new IllegalStateException();
		}
		Header h = request.headers.get("referer");
		if (h == null) {
			h = request.headers.get("referrer");
		}
		return h == null ? null : h.value();
	}
	
	private static Locale getUserLocale() {
		Request request = Request.current();
		if (request == null) {
			return Locale.getDefault();
		}
		Header acceptLanguage = request.headers.get("accept-language");
		if (acceptLanguage == null) {
			return Locale.getDefault();
		}
		for (String value : acceptLanguage.values) {
			if (value.indexOf("ja") != -1) {
				return new Locale("ja", "JP", "JP");
			}
		}
		return Locale.getDefault();
	}
	
}
