package org.codeskull.java_fiddle_exec.web;

import java.io.StringWriter;
import java.util.concurrent.Callable;

import org.codeskull.java_fiddle_exec.Compiler;
import org.codeskull.java_fiddle_exec.web.models.Response;

public class CompilerRunner implements Callable<Response> {
	
	private Compiler compiler;
	
	public CompilerRunner(String className, String code) {
		this.compiler = new Compiler(className, code);
	}
	
    @Override
    public Response call() throws Exception {
		compiler.compile();
		String result = (String) compiler.run();
		return new Response(result, "", compiler.getSourceCode(), "false");
    }
    
    public StringWriter  getCompilerStream() {
    	return (StringWriter) compiler.getWriter();

    }
    
}