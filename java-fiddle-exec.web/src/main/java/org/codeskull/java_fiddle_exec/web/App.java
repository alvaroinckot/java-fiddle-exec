package org.codeskull.java_fiddle_exec.web;

import org.codeskull.java_fiddle_exec.Compiler;

import static spark.Spark.*;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.codeskull.java_fiddle_exec.web.models.*;

public class App {
	
	public static final int SERVICE_PORT = 4568;

	public static final int MAX_THREADS = 10;
	
	public static final int MIN_THREADS = 1;
	
	public static final int TIMEOUT = 8000;
	
	public static void main(String[] args) {

		port(SERVICE_PORT);
		
		threadPool(MAX_THREADS, MIN_THREADS, TIMEOUT);
		
		after((req, res) -> {
			res.type("application/json");
		});
		
		post("/compile", (req, res) -> {
			
			try {
				CompilerRunner runner = new CompilerRunner(req.queryParams("class_name"), req.queryParams("code"));
				ExecutorService executor = Executors.newSingleThreadExecutor();
		        Future<Response> future = executor.submit(runner);
		        return future.get(TIMEOUT, TimeUnit.MILLISECONDS);
			} catch (IllegalArgumentException | SecurityException  e) {
				return new Response("fail");
			} catch (TimeoutException e) {
				return new Response("timeout");
			}

		}, org.codeskull.java_fiddle_exec.web.utils.JsonUtil.json());


	}
}
