package org.giiwa.tools.web.admin;

import java.util.TreeMap;

import org.giiwa.framework.web.Model;
import org.giiwa.framework.web.Path;

/**
 * some setting of the module
 * 
 * @author joe
 *
 */
public class language extends Model {

	public void onGet() {
		TreeMap<String, String> d = new TreeMap<String, String>();
		d.putAll(lang.getData());
		StringBuilder sb = new StringBuilder();
		for (String n : d.keySet()) {
			sb.append(n).append("=").append(d.get(n)).append("\r\n");
		}
		this.set("s", sb.toString());
		this.show("/admin/language.html");

	}

	@Path(path = "missed")
	public void missed() {
		TreeMap<String, String> d = new TreeMap<String, String>();
		d.putAll(lang.getMissed());
		StringBuilder sb = new StringBuilder();
		for (String n : d.keySet()) {
			sb.append(n).append("=").append(d.get(n)).append("\r\n");
		}
		this.set("s", sb.toString());
		this.show("/admin/language.missed.html");

	}

}
