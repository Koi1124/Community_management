package model;

public class Activity {
    private String aNum;
    private String aContent;
    private String aDate;
    private String aTitle;
    private String cNum;
    private int view;

    public void setaNum(String aNum) {
        this.aNum = aNum;
    }

    public void setaContent(String aContent) {
        this.aContent = aContent;
    }

    public void setaDate(String aDate) {
        this.aDate = aDate;
    }

    public void setaTitle(String aTitle) {
        this.aTitle = aTitle;
    }

    public void setcNum(String cNum) {
        this.cNum = cNum;
    }

    public void setView(int view) {
        this.view = view;
    }

    public String getaNum() {
        return aNum;
    }

    public String getaContent() {
        return aContent;
    }

    public String getaDate() {
        return aDate;
    }

    public String getaTitle() {
        return aTitle;
    }

    public String getcNum() {
        return cNum;
    }

    public int getView() {
        return view;
    }
}
