package top.godder.homework.domain;

import javax.persistence.Id;
import java.util.Date;

public class Discussion {
    @Id
    private Integer id;

    private String publisherid;

    private Date datetime;

    private String content;

    private Integer teachcourseid;

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
     * @return publisherid
     */
    public String getPublisherid() {
        return publisherid;
    }

    /**
     * @param publisherid
     */
    public void setPublisherid(String publisherid) {
        this.publisherid = publisherid == null ? null : publisherid.trim();
    }

    /**
     * @return datetime
     */
    public Date getDatetime() {
        return datetime;
    }

    /**
     * @param datetime
     */
    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    /**
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
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
}