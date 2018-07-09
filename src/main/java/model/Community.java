package model;

public class Community {
    private String cNum;
    private String cName;
    private String cType;
    private String cSrc;
    private String Syn;
    private String cStartTime;
    private String cStuNum;
    private int state;

    public String getcName() {
        return cName;
    }

    public String getcNum() {
        return cNum;
    }

    public String getcSrc() {
        return cSrc;
    }

    public String getcStartTime() {
        return cStartTime;
    }

    public String getcStuNum() {
        return cStuNum;
    }

    public String getcType() {
        return cType;
    }

    public String getSyn() {
        return Syn;
    }

    public int getState() {
        return state;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public void setcNum(String cNum) {
        this.cNum = cNum;
    }

    public void setcSrc(String cSrc) {
        this.cSrc = cSrc;
    }

    public void setcStartTime(String cStartTime) {
        this.cStartTime = cStartTime;
    }

    public void setcStuNum(String cStuNum) {
        this.cStuNum = cStuNum;
    }

    public void setcType(String cType) {
        this.cType = cType;
    }

    public void setSyn(String syn) {
        Syn = syn;
    }

    public void setState(int state) {
        this.state = state;
    }
}
