package vo;

/**
 * Created by 李浩 on 2018/5/10
 */
public class labBan {
    private String laboratoryID = null;
    private int startweek;
    private int endweek;
    private int whichday;
    private int section;

    public int getSection() {
        return section;
    }

    public void setSection(int section) {
        this.section = section;
    }

    public int getWhichday() {

        return whichday;
    }

    public void setWhichday(int whichday) {
        this.whichday = whichday;
    }

    public int getEndweek() {

        return endweek;
    }

    public void setEndweek(int endweek) {
        this.endweek = endweek;
    }

    public int getStartweek() {

        return startweek;
    }

    public void setStartweek(int startweek) {
        this.startweek = startweek;
    }

    public String getLaboratoryID() {
        return laboratoryID;
    }

    public void setLaboratoryID(String laboratoryID) {
        this.laboratoryID = laboratoryID;
    }
}
