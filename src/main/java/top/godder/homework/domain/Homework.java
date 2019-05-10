package top.godder.homework.domain;

import javax.persistence.Id;
import java.util.Date;

public class Homework {
    @Id
    private Integer id;

    private Integer teachcourseid;

    private String name;

    private Date deadline;

    private String info;

    private Integer proportion;

    private String format;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
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
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * @return deadline
     */
    public Date getDeadline() {
        return deadline;
    }

    /**
     * @param deadline
     */
    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    /**
     * @return info
     */
    public String getInfo() {
        return info;
    }

    /**
     * @param info
     */
    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }

    /**
     * @return proportion
     */
    public Integer getProportion() {
        return proportion;
    }

    /**
     * @param proportion
     */
    public void setProportion(Integer proportion) {
        this.proportion = proportion;
    }

    /**
     * @return format
     */
    public String getFormat() {
        return format;
    }

    /**
     * @param format
     */
    public void setFormat(String format) {
        this.format = format == null ? null : format.trim();
    }
}