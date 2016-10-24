package org.codeskull.java_fiddle_exec;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;

import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class CompilerTest extends TestCase {

	public CompilerTest(String testName) {
		super(testName);
	}

	public static Test suite() {
		return new TestSuite(CompilerTest.class);
	}

	public void testCompileCorrectCode() throws UnsupportedEncodingException {

		StringBuilder src = new StringBuilder();
		src.append("public class MyClass {\n");
		src.append("    public String toString() {\n");
		src.append("        return \"Hello, I am \" + ");
		src.append("this.getClass();\n");
		src.append("    }\n");
		src.append("}\n");

		String fullName = "MyClass";

		Compiler compiler = new Compiler(fullName, src.toString());

		Assert.assertTrue(compiler.compile());

	}

	public void testCompileWrongCode() throws UnsupportedEncodingException {

		StringBuilder src = new StringBuilder();
		src.append("public class MyClass {\n");
		src.append("    public Strong toString() {\n");
		src.append("        return \"Hello, I am \" + ");
		src.append("this.getClass();\n");
		src.append("    }\n");
		src.append("}\n");

		String fullName = "MyClass";

		Compiler compiler = new Compiler(fullName, src.toString());
		Assert.assertFalse(compiler.compile());

	}
	
	public void testRunCorrectCode() throws UnsupportedEncodingException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {

		StringBuilder src = new StringBuilder();
		src.append("public class MyClass {\n");
		src.append("    public String toString() {\n");
		src.append("        return \"Hello, I am \" + ");
		src.append("this.getClass();\n");
		src.append("    }\n");
		src.append("}\n");

		String fullName = "MyClass";

		Compiler compiler = new Compiler(fullName, src.toString());

		Assert.assertTrue(compiler.compile());
		
		try {
			Assert.assertNotNull(compiler.run());
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			Assert.fail();
		}

	}


}
