package org.codeskull.java_fiddle_exec.web;

import static spark.Spark.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import org.codeskull.java_fiddle_exec.web.models.*;

import static org.codeskull.java_fiddle_exec.web.utils.JsonUtil.json;

public class App {
	
	public static int SERVICE_PORT = 4568;

	public static int MAX_THREADS = 10;
	
	public static int MIN_THREADS = 1;
	
	public static int TIMEOUT = 8000;
	
	public static void main(String[] args) {

		port(SERVICE_PORT);
		
		threadPool(MAX_THREADS, MIN_THREADS, TIMEOUT);
		
		after((req, res) -> {
			res.type("application/json");
		});
		
		post("/compile", (req, res) -> {
			
			CompilerRunner runner = new CompilerRunner(req.queryParams("class_name"), req.queryParams("code"));
			ExecutorService executor = Executors.newSingleThreadExecutor();
	        Future<Response> future = executor.submit(runner);

			try {
				return future.get(TIMEOUT, TimeUnit.MILLISECONDS);
			} catch (Exception e) {
				return new Response(runner.getCompilerStream().toString(), e.getMessage(), req.queryParams("code"),  "true");
			} 
			
		}, json());


	}
}
