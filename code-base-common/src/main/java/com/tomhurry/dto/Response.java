package com.tomhurry.dto;

/**
 * 调用请求返回
 *
 * @author taozhi
 * @date 2022/5/19 13:39
 * @since 1.0.0
 */
public class Response extends DTO {

    private static final long serialVersionUID = 1L;

    private boolean success;

    private String code;

    private String message;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Response{" +
                "success=" + success +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

    public static Response buildResponse(boolean success, String code, String message) {
        Response response = new Response();
        response.setSuccess(success);
        response.setCode(code);
        response.setMessage(message);
        return response;
    }
}
