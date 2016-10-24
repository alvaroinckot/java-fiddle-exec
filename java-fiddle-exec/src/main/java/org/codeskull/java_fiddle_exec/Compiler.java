package org.codeskull.java_fiddle_exec;

import java.io.UnsupportedEncodingException;
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

	public Compiler(String fullName_, String SrcCode_) {
		fullName = fullName_;
		sourceCode = SrcCode_;
		fileManager = initFileManager();

	}

	public JavaFileManager initFileManager() {
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

		return compiler.getTask(null, fileManager, null, null, null, jfiles).call();
	}

	public Object run() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		return fileManager.getClassLoader(null).loadClass(fullName).newInstance().toString();
	}
}