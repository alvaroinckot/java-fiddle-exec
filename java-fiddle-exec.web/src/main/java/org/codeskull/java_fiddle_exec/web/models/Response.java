package org.codeskull.java_fiddle_exec.web.models;

public class Response {

	private String log;
	private Object[] response;
	private String source;
	private Boolean error;
	private String exception;
	
	public Response () { }

	public Response(String[] response){
		this.setResponse(response);
	}

	public Response(Object[] response, String log, String source, Boolean error, String exception){
		this.setResponse(response);
		this.setLog(log);
		this.setSource(source);
		this.setError(error);
		this.setException(exception);
	}
	
	public String getLog() {
		return log;
	}

	public void setLog(String log) {
		this.log = log;
	}

	public Object[] getResponse() {
		return response;
	}

	public void setResponse(Object[] response) {
		this.response = response;
	}
	
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Boolean getError() {
		return error;
	}

	public void setError(Boolean error) {
		this.error = error;
	}
	
	
	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

}
