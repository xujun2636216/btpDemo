package btpEntity;

import java.util.List;

public class ListResultDTO<T> extends ResultDTO {

    private int count;

    private List<T> datalist;

    public ListResultDTO() {
    }

    public ListResultDTO(boolean isSuccess, int resultCode, String msg, int count, List<T> datalist) {
        super(isSuccess, resultCode, msg);
        this.count = count;
        this.datalist = datalist;
    }


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<T> getDatalist() {
        return datalist;
    }

    public void setDatalist(List<T> datalist) {
        this.datalist = datalist;
    }
}
