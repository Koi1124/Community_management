package model;

public class Remark {
    private String rNum;
    private String rContent;
    private String stuNum;
    private String rDate;
    private String cNum;

    public String getcNum() {
        return cNum;
    }

    public String getrContent() {
        return rContent;
    }

    public String getrDate() {
        return rDate;
    }

    public String getrNum() {
        return rNum;
    }

    public String getStuNum() {
        return stuNum;
    }

    public void setcNum(String cNum) {
        this.cNum = cNum;
    }

    public void setrContent(String rContent) {
        this.rContent = rContent;
    }

    public void setrDate(String rDate) {
        this.rDate = rDate;
    }

    public void setrNum(String rNum) {
        this.rNum = rNum;
    }

    public void setStuNum(String stuNum) {
        this.stuNum = stuNum;
    }
}
