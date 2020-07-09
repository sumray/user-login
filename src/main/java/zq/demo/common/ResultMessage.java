package zq.demo.common;

import com.fasterxml.jackson.annotation.JsonInclude;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultMessage {
	
	private static final Integer SUCCESS_CODE = 0;
	
	private static final Integer FAIL_CODE = 100;
	
	private Integer code;
	
	private String msg;
	
	private Object data;

	public ResultMessage() {
		
	}

	private ResultMessage(Integer code, String msg, Object data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
	
	public static ResultMessage newSuccessMessage(Object data) {
		return new ResultMessage(SUCCESS_CODE, null, data);
	}
	
	public static ResultMessage newFailMessage(String msg) {
		return new ResultMessage(FAIL_CODE, msg, null);
	}

	public static ResultMessage success() {
		return new ResultMessage(SUCCESS_CODE, null, null);
	}

	public Integer getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

	public Object getData() {
		return data;
	}
}
