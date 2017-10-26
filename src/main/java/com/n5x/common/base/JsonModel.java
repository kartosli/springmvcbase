package com.n5x.common.base;

import java.util.List;
import java.util.Map;

public class JsonModel {

	private String message;
	private String error;
	private String rcode;
	private Object data;
	private List<Map> datalist;
//	private String debug;
	private String result;


	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

//	public String getDebug() {
//		return debug;
//	}
//
//	public void setDebug(String debug) {
//		this.debug = debug;
//	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getRcode() {
		return rcode;
	}

	public void setRcode(String rcode) {
		this.rcode = rcode;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public List<Map> getDatalist() {
		return datalist;
	}

	public void setDatalist(List<Map> datalist) {
		this.datalist = datalist;
	}
}