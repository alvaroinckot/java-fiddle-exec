package org.codeskull.java_fiddle_exec;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.ToolProvider;

public class FiddleCompiler implements Compiler {

	private JavaFileManager fileManager;
	private Writer compilerWriter;
	private PrintStream consoleStream;
	private ByteArrayOutputStream byteConsoleStream;

	public FiddleCompiler() {
		this.fileManager = initFileManager();
		this.compilerWriter = new StringWriter();
		this.byteConsoleStream = new ByteArrayOutputStream();
		this.consoleStream = new PrintStream(this.byteConsoleStream);
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

	public boolean compile(String className, String sourceCode) throws UnsupportedEncodingException {
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

		List<JavaFileObject> jfiles = new ArrayList<JavaFileObject>();
		jfiles.add(new CharSequenceJavaFileObject(className, sourceCode));
		return compiler.getTask(compilerWriter, fileManager, null, null, null, jfiles).call();
	}

	public Object[] run(String className, String[] methods) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		@SuppressWarnings("rawtypes")
		Class noparams[] = {};
		
		System.setOut(this.consoleStream);
		
		Object instance = fileManager.getClassLoader(null).loadClass(className).newInstance();
		List<Object> result = new LinkedList<Object>();
		for (String method : methods) {
			result.add(instance.getClass().getDeclaredMethod(method, noparams).invoke(instance));
		}
		return result.toArray();
		
	}

	public Writer getCompilerWriter() {
		return compilerWriter;
	}
	
	public ByteArrayOutputStream getConsoleStream() {
		return this.byteConsoleStream;
	}
	
}