package model;

public class ChatNews {
    private String nNum;
    private String startNum;
    private String endNum;
    private String news;
    private String newTime;
    private int state;

    public void setnNum(String nNum)
    {
        this.nNum=nNum;
    }

    public void setStartNum(String startNum){this.startNum=startNum;}

    public void setEndNum(String endNum){this.endNum=endNum;}

    public void setNews(String news){this.news=news;}

    public void setNewTime(String newTime){this.newTime=newTime;}

    public void setState(int state) {
        this.state = state;
    }

    public String getnNum(){return  nNum;}

    public String getStartNum() {
        return startNum;
    }

    public String getEndNum() {
        return endNum;
    }

    public String getNews() {
        return news;
    }

    public String getNewTime() {
        return newTime;
    }

    public int getState() {
        return state;
    }
}
