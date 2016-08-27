/*
 *   WebGiisoo, a java web foramewrok.
 *   Copyright (C) <2014>  <giisoo inc.>
 *
 */
package org.giiwa.tools.web.admin;

import org.giiwa.core.bean.Beans;
import org.giiwa.core.bean.Helper.V;
import org.giiwa.core.bean.X;
import org.giiwa.core.json.JSON;
import org.giiwa.framework.web.Model;
import org.giiwa.tools.bean.Rsakey;

/**
 * web api: /admin/rsakey, used to manage RSA keypair
 * 
 * @author joe
 *
 */
public class rsakey extends Model {

  /*
   * (non-Javadoc)
   * 
   * @see com.giisoo.framework.web.Model#onGet()
   */
  @Override
  public void onGet() {
    String method = this.path;
    if ("add".equals(method)) {
      this.show("/admin/rsakey.add.html");
      return;
    } else if ("edit".equals(method)) {
      long created = this.getLong("created");
      Rsakey k = Rsakey.load(created);
      JSON jo = k.getJSON();
      k.toJSON(jo);
      this.set(jo);
      this.show("/admin/rsakey.edit.html");
      return;
    } else if ("detail".equals(method)) {
      long created = this.getLong("created");
      Rsakey k = Rsakey.load(created);
      JSON jo = k.getJSON();
      this.set(jo);
      this.show("/admin/rsakey.detail.html");
      return;
    }

    int s = this.getInt("s");
    int n = this.getInt("n", 10, "number.per.page");
    Beans<Rsakey> bs = Rsakey.load(s, n);
    this.set(bs, s, n);

    this.query.path("/admin/rsakey");

    this.show("/admin/rsakey.index.html");
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.giisoo.framework.web.Model#onPost()
   */
  @Override
  public void onPost() {
    String method = this.path;
    if ("add".equals(method)) {
      int length = this.getInt("length");
      String memo = this.getString("memo");
      long created = Rsakey.create(length, memo);
      if (created > 0) {
        Rsakey k = Rsakey.load(created);
        JSON jo = k.getJSON();
        this.set(jo);
        this.show("/admin/rsakey.detail.html");
        return;
      } else {
        this.set(X.MESSAGE, lang.get("create_error"));
        this.set("length", length);
        this.set("memo", memo);
      }
    } else if ("edit".equals(method)) {
      long created = this.getLong("createc");
      String memo = this.getString("memo");

      if (Rsakey.update(created, V.create("memo", memo)) > 0) {
        Rsakey k = Rsakey.load(created);
        JSON jo = k.getJSON();
        this.set(jo);
        this.show("/admin/rsakey.detail.html");
        return;
      } else {
        this.set(X.MESSAGE, lang.get("edit_error"));
      }
    }

    onGet();
  }

}
