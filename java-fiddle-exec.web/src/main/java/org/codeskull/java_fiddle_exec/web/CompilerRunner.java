package org.codeskull.java_fiddle_exec.web;

import java.util.concurrent.Callable;

import org.codeskull.java_fiddle_exec.Compiler;
import org.codeskull.java_fiddle_exec.web.models.Response;

public class CompilerRunner implements Callable<Response> {
	
	private String className;
	private String code;
	
	public CompilerRunner(String className, String code) {
		this.className = className;
		this.code = code;
	}
	
    @Override
    public Response call() throws Exception {
		Compiler compiler = new Compiler(className, code);
		compiler.compile();
		String result = (String) compiler.run();
		return new Response(result);
    }
    
}