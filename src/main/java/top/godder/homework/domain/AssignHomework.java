package top.godder.homework.domain;

import java.util.Date;

public class AssignHomework {
    private String studentid;

    private Integer teachcourseid;

    private Integer homeworkid;

    private Date submittime;

    private Integer score;

    private String remark;

    /**
     * @return studentid
     */
    public String getStudentid() {
        return studentid;
    }

    /**
     * @param studentid
     */
    public void setStudentid(String studentid) {
        this.studentid = studentid == null ? null : studentid.trim();
    }

    /**
     * @return teachcourseid
     */
    public Integer getTeachcourseid() {
        return teachcourseid;
    }

    /**
     * @param teachcourseid
     */
    public void setTeachcourseid(Integer teachcourseid) {
        this.teachcourseid = teachcourseid;
    }

    /**
     * @return homeworkid
     */
    public Integer getHomeworkid() {
        return homeworkid;
    }

    /**
     * @param homeworkid
     */
    public void setHomeworkid(Integer homeworkid) {
        this.homeworkid = homeworkid;
    }

    /**
     * @return submittime
     */
    public Date getSubmittime() {
        return submittime;
    }

    /**
     * @param submittime
     */
    public void setSubmittime(Date submittime) {
        this.submittime = submittime;
    }

    /**
     * @return score
     */
    public Integer getScore() {
        return score;
    }

    /**
     * @param score
     */
    public void setScore(Integer score) {
        this.score = score;
    }

    /**
     * @return remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}