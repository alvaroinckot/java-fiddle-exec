package org.codeskull.java_fiddle_exec;

import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;

public interface Compiler {
	public boolean compile(String className, String sourceCode)  throws UnsupportedEncodingException;
	public Object[] run(String className, String[] methods) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException;
	public Writer getCompilerWriter();
}
