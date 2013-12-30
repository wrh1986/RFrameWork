package com.RFramework.utils.db;

import java.util.concurrent.atomic.AtomicLong;

public class UidHelper {

  private static AtomicLong uid;
  private static int DEFAULT_STEP = 1;
  static {
    uid = new AtomicLong(System.currentTimeMillis());
  }
  
  public static long get() {
    return uid.addAndGet(DEFAULT_STEP);
  }
  
  public static long[] getN(int n) {
    long[] result = new long[n];
    for(int index = 0; index < n; index ++){
      result[index] = get();
    }
    return result;
  }
}
