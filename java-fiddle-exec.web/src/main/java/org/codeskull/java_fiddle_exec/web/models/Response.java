package org.codeskull.java_fiddle_exec.web.models;

public class Response {

	private Object[] output;
	private String code;
	private String exception;
	private Object result;
	
	public Response () { }

	public Response(Object[] output, String code, String exception, Object result) {
		this.setCode(code);
		this.setOutput(output);
		this.setException(exception);
		this.setResult(result);
	}
	
	public Object getResult() {
		return result;
	}


	public void setResult(Object result) {
		this.result = result;
	}

	public Object[] getOutput() {
		return output;
	}


	public void setOutput(Object[] output) {
		this.output = output;
	}


	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}


	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

}
