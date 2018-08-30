package btpEntity;


public  class  ResultDTO {

    private   boolean isSuccess ;

    private int resultCode ;

    private String msg ;

    public ResultDTO() {}

    public ResultDTO(boolean isSuccess, int resultCode, String msg) {
        this.isSuccess = isSuccess;
        this.resultCode = resultCode;
        this.msg = msg;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}


