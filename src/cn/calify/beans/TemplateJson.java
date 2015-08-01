package cn.calify.beans;

import java.util.List;

public class TemplateJson {
	private String result = null;
	private String error = null;
	private List returnlist = null;
	private Object obj = null;
	
	public TemplateJson(){}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public List getReturnlist() {
		return returnlist;
	}

	public void setReturnlist(List returnlist) {
		this.returnlist = returnlist;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}
	
}
