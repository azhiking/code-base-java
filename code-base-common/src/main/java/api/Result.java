package api;

/**
 * θΏεη»ζ
 *
 * @author taozhi
 * @date 2020/10/9
 * @since 1.0.0
 */
public class Result<T> {
    private Integer code;

    private String message;

    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
