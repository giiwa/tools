/*
 *   WebGiisoo, a java web foramewrok.
 *   Copyright (C) <2014>  <giisoo inc.>
 *
 */
package org.giiwa.tools.bean;

import org.giiwa.core.base.RSA;
import org.giiwa.core.base.RSA.Key;
import org.giiwa.core.bean.Bean;
import org.giiwa.core.bean.Beans;
import org.giiwa.core.bean.Helper;
import org.giiwa.core.bean.Helper.V;
import org.giiwa.core.bean.Helper.W;
import org.giiwa.core.bean.Table;
import org.giiwa.core.bean.X;

/**
 * RSA key pair
 * 
 * @author joe
 *
 */
@Table(name = "gi_rsakey")
public class Rsakey extends Bean {

  /**
  * 
  */
  private static final long serialVersionUID = 1L;

  /**
   * Creates the.
   * 
   * @param length
   *          the length
   * @param memo
   *          the memo
   * @return the long
   */
  public static long create(int length, String memo) {

    Key k = RSA.generate(length);
    if (k != null) {
      long created = System.currentTimeMillis();
      if (Helper.insert(V.create(X.ID, created).set("created", created).set("length", length).set("memo", memo)
          .set("pubkey", k.pub_key).set("prikey", k.pri_key), Rsakey.class) > 0) {
        return created;
      }
    }

    return 0;
  }

  /**
   * Load.
   * 
   * @param s
   *          the s
   * @param n
   *          the n
   * @return the beans
   */
  public static Beans<Rsakey> load(int s, int n) {
    return Helper.load(W.create().sort(X.ID, -1), s, n, Rsakey.class);
  }

  /**
   * Update.
   * 
   * @param created
   *          the created
   * @param v
   *          the v
   * @return the int
   */
  public static int update(long created, V v) {
    return Helper.update(created, v, Rsakey.class);
  }

  public long getCreated() {
    return getLong("created");
  }

  public String getMemo() {
    return getString("memo");
  }

  public int getLength() {
    return getInt("length");
  }

  public String getPubkey() {
    return getString("pubkey");
  }

  public String getPrikey() {
    return getString("prikey");
  }

  /**
   * Load.
   * 
   * @param created
   *          the created
   * @return the keypair
   */
  public static Rsakey load(long created) {
    return Helper.load(created, Rsakey.class);
  }

}
