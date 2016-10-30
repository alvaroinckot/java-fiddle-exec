package org.codeskull.java_fiddle_exec;

import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.ToolProvider;

public class Compiler {

	private JavaFileManager fileManager;
	private String fullName;
	private String sourceCode;
	private Writer writer;

	public Compiler(String fullName, String sourceCode) {
		this.fullName = fullName;
		this.sourceCode = sourceCode;
		this.fileManager = initFileManager();
		this.writer = new StringWriter();
		
	}

	private JavaFileManager initFileManager() {
		if (fileManager != null)
			return fileManager;
		else {
			JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
			fileManager = new ClassFileManager(compiler.getStandardFileManager(null, null, null));
			return fileManager;
		}

	}

	public boolean compile() throws UnsupportedEncodingException {
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

		List<JavaFileObject> jfiles = new ArrayList<JavaFileObject>();
		jfiles.add(new CharSequenceJavaFileObject(fullName, sourceCode));
		return compiler.getTask(writer, fileManager, null, null, null, jfiles).call();
	}

	public Object run() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		return fileManager.getClassLoader(null).loadClass(fullName).newInstance().toString();
	}

	public Writer getWriter() {
		return writer;
	}

	public String getSourceCode() {
		return sourceCode;
	}
	
	
	
}