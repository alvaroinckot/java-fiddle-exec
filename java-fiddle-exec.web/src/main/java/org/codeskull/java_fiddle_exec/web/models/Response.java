package org.codeskull.java_fiddle_exec.web.models;

public class Response {

	private String log;
	private String response;
	
	public Response () { }
	
	public Response(String response){
		this.setResponse(response);
	}

	public Response(String response, String log){
		this.setResponse(response);
	}
	
	public String getLog() {
		return log;
	}

	public void setLog(String log) {
		this.log = log;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

}
