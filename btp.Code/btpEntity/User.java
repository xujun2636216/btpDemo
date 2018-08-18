package btpEntity;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {
    private int id;
    private String name;
    private String dept;
    private String phone;
    private String website;
    private List<Cores> cores;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public List<Cores> getCores() {
        return cores;
    }

    public void setCores(List<Cores> cores) {
        this.cores = cores;
    }
}
