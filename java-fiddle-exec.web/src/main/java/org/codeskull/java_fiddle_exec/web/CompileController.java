package org.codeskull.java_fiddle_exec.web;

import static org.codeskull.java_fiddle_exec.web.utils.JsonUtil.json;
import static spark.Spark.*;

import org.codeskull.java_fiddle_exec.Compiler;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.codeskull.java_fiddle_exec.CompilerRunner;
import org.codeskull.java_fiddle_exec.FiddleCompiler;
import org.codeskull.java_fiddle_exec.web.models.Response;

public class CompileController {
	
	public CompileController() {
		
		post("/compile", (req, res) -> {
			
			Compiler compiler = new FiddleCompiler();
			CompilerRunner runner = new CompilerRunner(compiler, 
					req.queryParams("class"),
					req.queryParams("code"),
					req.queryParams("method").split(","));
			ExecutorService executor = Executors.newSingleThreadExecutor();
	        Future<Response> future = executor.submit(runner);

			try {
				return future.get(Config.TIMEOUT, TimeUnit.MILLISECONDS);
			} catch (Exception e) {
				return new Response(runner.getCompilerStream().toString().split("\n"), 
						req.queryParams("code"), 
						e.getMessage(),
						"");
			} 
			
		}, json());
		
	}
}
