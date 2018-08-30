package btpEntity;

public class TResultDTO<T> extends ResultDTO {

    private T Data;

    public TResultDTO() {}

    public TResultDTO(boolean isSuccess, int resultCode, String msg, T data) {
        super(isSuccess, resultCode, msg);
        this.Data = data;
    }

    public T getData() {
        return Data;
    }

    public void setData(T data) {
        this.Data = data;
    }
}
