package top.godder.homework.domain;

import javax.persistence.Id;
import java.util.Date;

public class Message {
    @Id
    private Integer id;

    private Integer teachcourseid;

    private Date date;

    private String name;

    private String info;

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
     * @return date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date
     */
    public void setDate(Date date) {
        this.date = date;
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
}