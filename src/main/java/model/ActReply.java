package model;

public class ActReply {
    private String acrNum;
    private String acNum;
    private String fromID;
    private String toID;
    private String content;
    private String acrDate;

    public String getContent() {
        return content;
    }

    public String getAcNum() {
        return acNum;
    }

    public String getAcrDate() {
        return acrDate;
    }

    public String getAcrNum() {
        return acrNum;
    }

    public String getFromID() {
        return fromID;
    }

    public String getToID() {
        return toID;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAcNum(String acNum) {
        this.acNum = acNum;
    }

    public void setAcrDate(String acrDate) {
        this.acrDate = acrDate;
    }

    public void setAcrNum(String acrNum) {
        this.acrNum = acrNum;
    }

    public void setFromID(String fromID) {
        this.fromID = fromID;
    }

    public void setToID(String toID) {
        this.toID = toID;
    }
}
