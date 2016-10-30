package org.codeskull.java_fiddle_exec.web;

import static spark.Spark.*;

import org.codeskull.java_fiddle_exec.FiddleCompiler;

public class App {

	public static void main(String[] args) {

		port(Config.SERVICE_PORT);
		
		threadPool(Config.MAX_THREADS, Config.MIN_THREADS, Config.TIMEOUT);
		
		after((req, res) -> {
			res.type("application/json");
		});
		
		new CompileController(new FiddleCompiler());


	}
}
