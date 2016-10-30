package org.codeskull.java_fiddle_exec;

import java.io.StringWriter;
import java.util.concurrent.Callable;

import org.codeskull.java_fiddle_exec.web.models.Response;

public class CompilerRunner implements Callable<Response> {
	
	private Compiler compiler;
	private String sourceCode;
	private String className;
	private String[] methods;
	
	public CompilerRunner(Compiler compiler, String className, String sourceCode, String[] methods) {
		this.compiler = compiler;
		this.className = className;
		this.sourceCode = sourceCode;
		this.methods = methods;
	}
	
    @Override
    public Response call() throws Exception {
		compiler.compile(className, sourceCode);
		Object[] result = compiler.run(className, methods);
		return new Response(result, "", sourceCode, true, null);
    }
    
    public StringWriter  getCompilerStream() {
    	return (StringWriter) compiler.getCompilerWriter();

    }
    
}