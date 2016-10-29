package org.codeskull.java_fiddle_exec.web;

import static spark.Spark.*;

import java.util.Map;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.codeskull.java_fiddle_exec.web.models.*;

import static org.codeskull.java_fiddle_exec.web.utils.JsonUtil.json;
import static org.codeskull.java_fiddle_exec.web.utils.CoalesceUtil.coalesce;

public class App {
	
	public static int SERVICE_PORT = 4568;

	public static int MAX_THREADS = 10;
	
	public static int MIN_THREADS = 1;
	
	public static int TIMEOUT = 8000;
	
	/*
	static  {
		Map<String, String> environment = System.getenv();
		SERVICE_PORT = coalesce(4568, Integer.parseInt(environment.get("SERVICE_PORT")));
		MAX_THREADS = coalesce(10, Integer.parseInt(environment.get("MAX_THREADS")));
		MIN_THREADS = coalesce(1, Integer.parseInt(environment.get("MIN_THREADS")));
		TIMEOUT = coalesce(8000, Integer.parseInt(environment.get("TIMEOUT")));
	}
	*/

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

		}, json());


	}
}
