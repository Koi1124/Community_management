package model;

public class ActComment {
    private String acNum;
    private String aNum;
    private String stuNum;
    private String acDate;
    private String content;

    public void setaNum(String aNum) {
        this.aNum = aNum;
    }

    public void setAcDate(String acDate) {
        this.acDate = acDate;
    }

    public void setAcNum(String acNum) {
        this.acNum = acNum;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setStuNum(String stuNum) {
        this.stuNum = stuNum;
    }

    public String getaNum() {
        return aNum;
    }

    public String getAcDate() {
        return acDate;
    }

    public String getAcNum() {
        return acNum;
    }

    public String getContent() {
        return content;
    }

    public String getStuNum() {
        return stuNum;
    }
}
