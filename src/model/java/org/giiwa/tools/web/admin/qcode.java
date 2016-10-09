package org.giiwa.tools.web.admin;

import javax.servlet.http.HttpServletResponse;

import org.giiwa.core.base.GImage;
import org.giiwa.core.bean.X;
import org.giiwa.core.json.JSON;
import org.giiwa.framework.bean.Temp;
import org.giiwa.framework.web.Model;
import org.giiwa.framework.web.Path;

public class qcode extends Model {

  @Path(login = true)
  public void onGet() {
    this.show("/admin/qcode.html");
  }

  @Path(login = true, path = "create")
  public void create() {
    String url = this.getHtml("content");
    int w = this.getInt("w");
    int h = this.getInt("h");
    Temp t = Temp.create("qcode.jpg");
    JSON jo = JSON.create();
    try {
      GImage.QRCode(t.getFile(), url, w, h);
      jo.put(X.STATE, HttpServletResponse.SC_OK);
      jo.put(X.URI, t.getUri());
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      jo.put(X.MESSAGE, e.getMessage());
    }
    this.response(jo);
  }

  @Path(path = "show")
  public void show() {
    String url = this.getString("content");
    Temp t = Temp.create("qcode.jpg");
    JSON jo = JSON.create();
    try {
      GImage.QRCode(t.getFile(), url, 120, 120);
      this.set("uri", t.getUri());
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      jo.put(X.MESSAGE, e.getMessage());
    }
    this.show("/admin/qcode.image.html");
  }
}
