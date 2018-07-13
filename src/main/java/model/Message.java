package model;

public class Message {

    private String mNum;
    private String mContent;
    private String stuNum;
    private int isRead;
    private String mSrc;
    private String mTime;

    public String getmNum() {
        return mNum;
    }

    public String getmContent() {
        return mContent;
    }

    public String getStuNum() {
        return stuNum;
    }

    public int getIsRead() {
        return isRead;
    }

    public String getmSrc() {
        return mSrc;
    }

    public String getmTime() {
        return mTime;
    }

    public void setmContent(String mContent) {
        this.mContent = mContent;
    }

    public void setStuNum(String stuNum) {
        this.stuNum = stuNum;
    }

    public void setIsRead(int isRead) {
        this.isRead = isRead;
    }

    public void setmNum(String mNum) {
        this.mNum = mNum;
    }

    public void setmSrc(String mSrc) {
        this.mSrc = mSrc;
    }

    public void setmTime(String mTime) {
        this.mTime = mTime;
    }
}
