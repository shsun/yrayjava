package spring.cloud;

public class MessageException extends RuntimeException {

    private int code;

    public MessageException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
