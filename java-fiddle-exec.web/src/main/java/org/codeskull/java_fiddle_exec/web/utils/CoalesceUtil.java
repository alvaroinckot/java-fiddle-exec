package org.codeskull.java_fiddle_exec.web.utils;

public class CoalesceUtil {
	
	public static <T> T coalesce(T a, T b) {
	    return a == null ? b : a;
	}
}
