package Base;

public class Teacher {

    // 教师类
    private String idteacher;
    private String nameteacher;
    private String college;
    private String course;
    private String sumgrade;
    private Integer average;

    // 构造函数、getter和setter方法
    public String getIdteacher() {
        return idteacher;
    }
    public void setIdteacher(String idteacher) {
        this.idteacher = idteacher;
    }

    public String getNameteacher() {
        return nameteacher;
    }
    public void setNameteacher(String nameteacher) {
        this.nameteacher = nameteacher;
    }
    public String getCollege() {
        return college;
    }
    public void setCollege(String college) {
        this.college = college;
    }
    public String getCourse() {
        return course;
    }
    public void setCourse(String course) {
        this.course = course;
    }
    public String getSumgrade() {
        return sumgrade;
    }
    public void setSumgrade(String sumgrade) {

        this.sumgrade = sumgrade;
    }
    public Integer getAverage() {

        return average;
    }
    public void setAverage(Integer average) {

        this.average = average;
    }
    // 重写toString方法
    @Override
    public String toString() {

        return "Teacher [idteacher=" + idteacher + ", nameteacher=" + nameteacher + ", college=" + college
                + ", course=" + course + ", sumgrade=" + sumgrade + ", average=" + average + "]";
    }


}

