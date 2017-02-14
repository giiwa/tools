/*
 * Copyright 2015 JIHU, Inc. and/or its affiliates.
 *
*/
package org.giiwa.tools.bean;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.giiwa.core.base.Host;
import org.giiwa.core.bean.TimeStamp;
import org.giiwa.core.cache.Cache;

public class memtest {

  /**
   * The main method.
   *
   * @param args
   *          the arguments
   */
  public static void main(String[] args) {
    Configuration conf = new PropertiesConfiguration();

    conf.setProperty("cache.url", "memcached://127.0.0.1:11211");
    Cache.init(conf);

    int size = 1024 * 10 * 1;
    Map<String, byte[]> m = new HashMap<String, byte[]>();
    for (int i = 0; i < 102400; i++) {
      m.put("k_" + i, new byte[size]);
    }

    TimeStamp t = TimeStamp.create();

    // t.reset();
    // Cache2.set("id2", c.m);
    // System.out.println(t.past());
    //
    // t.reset();
    // Object t2 = Cache2.get("id2");
    // System.out.println(t.past());

    t.reset();
    Cache.set("id", "1");
    System.out.println(t.past());

    t.reset();
    String m1 = Cache.get("id");
    System.out.println(t.past());

    t.reset();
    Cache.set("id2", m);
    System.out.println(t.past());

    m.put("k_1.1", new byte[size]);
    t.reset();
    Cache.set("id3", m);
    System.out.println(t.past());

    System.out.println(Host.getLocalip());
  }

}
