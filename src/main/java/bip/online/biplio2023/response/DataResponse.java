package bip.online.biplio2023.response;

public class DataResponse<T> extends BaseResponse{

    private T data;

    public DataResponse(boolean success, String message, T data) {
        super(success, message);
        this.data = data;
    }

    public DataResponse(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
