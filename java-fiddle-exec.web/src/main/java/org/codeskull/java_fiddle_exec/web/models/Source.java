package org.codeskull.java_fiddle_exec.web.models;

public class Source {

	private String code;
	
	private String className;
	
	public Source() {}
	
	public Source(String code) { 
		this.code = code;
	}
	
	public Source(String code, String className) { 
		this.code = code;
		this.className = className;
	}
	
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
