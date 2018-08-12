package btpEntity;
import java.util.List;


public class WuliuDetail {
    private  int number;
    private  int type;
    private  List<Wuliulist> list;

    public WuliuDetail() { }


    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<Wuliulist> getList() {
        return list;
    }

    public void setList(List<Wuliulist> list) {
        this.list = list;
    }
}
