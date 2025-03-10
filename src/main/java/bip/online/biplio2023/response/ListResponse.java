package bip.online.biplio2023.response;


import java.util.List;

public class ListResponse<T> extends BaseResponse{

    private List<T> data;

    public ListResponse(boolean success, String message, List<T> data) {
        super(success, message);
        this.data = data;
    }

    public ListResponse(List<T> data) {
        this.data = data;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
