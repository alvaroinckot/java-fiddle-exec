package org.codeskull.java_fiddle_exec;

import java.io.UnsupportedEncodingException;
import java.io.Writer;

public interface Compiler {
	public boolean compile()  throws UnsupportedEncodingException;
	public Object run() throws InstantiationException, IllegalAccessException, ClassNotFoundException;
	public Writer getCompilerWriter();
	public String getSourceCode();
}
