package vo;

public class LabCloseSetting {
    private String labName;
    private Integer startWeek;
    private Integer endWeek;
    private Integer keSum;
    private Integer dayOfWeekSum;

    public String getLabName() {
        return labName;
    }

    public void setLabName(String labName) {
        this.labName = labName;
    }

    public Integer getStartWeek() {
        return startWeek;
    }

    public void setStartWeek(Integer startWeek) {
        this.startWeek = startWeek;
    }

    public Integer getEndWeek() {
        return endWeek;
    }

    public void setEndWeek(Integer endWeek) {
        this.endWeek = endWeek;
    }

    public Integer getKeSum() {
        return keSum;
    }

    public void setKeSum(Integer keSum) {
        this.keSum = keSum;
    }

    public Integer getDayOfWeekSum() {
        return dayOfWeekSum;
    }

    public void setDayOfWeekSum(Integer dayOfWeekSum) {
        this.dayOfWeekSum = dayOfWeekSum;
    }
}
