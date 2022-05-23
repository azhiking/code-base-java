package exception;


import enums.ApiResponseEnum;

/**
 * 自定义异常基类
 *
 * @author taozhi
 * @date 2020/10/9
 * @since 1.0.0
 */
public class BaseException extends RuntimeException {
    private Integer code;
    private String message;

    public BaseException(ApiResponseEnum apiResponseEnum) {
        this.code = apiResponseEnum.getCode();
        this.message = apiResponseEnum.getMessage();
    }

    public BaseException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
