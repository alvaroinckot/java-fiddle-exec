package org.codeskull.java_fiddle_exec.web;

import org.codeskull.java_fiddle_exec.Compiler;

import static spark.Spark.*;

import java.io.UnsupportedEncodingException;

import org.codeskull.java_fiddle_exec.web.models.*;

public class App {
	
	public static final int SERVICE_PORT = 4568;

	public static void main(String[] args) {

		setPort(SERVICE_PORT);
		
		post("/compile", (req, res) -> {

			try {
				Compiler compiler = new Compiler(req.queryParams("class_name"), req.queryParams("code"));
				compiler.compile();
				String result = (String) compiler.run();
				return new Response(result);
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
					| UnsupportedEncodingException | IllegalArgumentException | SecurityException e) {
				e.printStackTrace();
				return new Response("fail");
			}

		}, org.codeskull.java_fiddle_exec.web.utils.JsonUtil.json());

		after((req, res) -> {
			res.type("application/json");
		});

	}
}
