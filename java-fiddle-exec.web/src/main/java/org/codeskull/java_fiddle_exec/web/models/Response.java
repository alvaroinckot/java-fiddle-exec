package org.codeskull.java_fiddle_exec.web.models;

public class Response {

	private String log;
	private String response;
	private String source;
	private String error;
	
	public Response () { }
	
	public Response(String response){
		this.setResponse(response);
	}

	public Response(String response, String log, String source, String error){
		this.setResponse(response);
		this.setLog(log);
		this.setSource(source);
		this.setError(error);
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
	
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

}
