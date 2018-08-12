package btpEntity;

import java.util.Date;

public class ExpressTrace {
    private Date time;
    private String status;
    public ExpressTrace() {}

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
