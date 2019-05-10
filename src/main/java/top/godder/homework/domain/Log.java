package top.godder.homework.domain;

import java.util.Date;

public class Log {
    private String ip;

    private String content;

    private Date time;

    private String operatorid;

    private Integer type;

    /**
     * @return ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * @param ip
     */
    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
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
     * @return time
     */
    public Date getTime() {
        return time;
    }

    /**
     * @param time
     */
    public void setTime(Date time) {
        this.time = time;
    }

    /**
     * @return operatorid
     */
    public String getOperatorid() {
        return operatorid;
    }

    /**
     * @param operatorid
     */
    public void setOperatorid(String operatorid) {
        this.operatorid = operatorid == null ? null : operatorid.trim();
    }

    /**
     * @return type
     */
    public Integer getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(Integer type) {
        this.type = type;
    }

    public static Integer CREATE_COURSE = 1;
    public static Integer EDITOR_COURSE = 2;
    public static Integer DELETE_COURSE = 3;
    public static Integer EDITOR_HOMEWORK = 4;
    public static Integer DELETE_HOMEWORK = 5;
    public static Integer LOGOUT_STUDENT = 6;
    public static Integer LOGOUT_COURSE = 7;
    public static Integer CREATE_GRADE = 8;
    public static Integer EDITOR_GRADE = 9;
    public static Integer DELETE_GRADE = 10;
    public static Integer CREATE_TEACHER = 11;
    public static Integer EDITOR_TEACHER = 12;
    public static Integer SAVE_HOMEWORK_SCORE = 13;
    public static Integer DELETE_MESSAGE = 14;
    public static Integer CREATE_NOTICE = 15;
    public static Integer DELETE_NOTICE = 16;

    public String printType() {
        if (type == null) {
            return null;
        }
        switch (type) {
            case 1:
                return "CREATE_COURSE";
            case 2:
                return "EDITOR_COURSE";
            case 3:
                return "DELETE_COURSE";
            case 4:
                return "EDITOR_HOMEWORK";
            case 5:
                return "DELETE_HOMEWORK";
            case 6:
                return "LOGOUT_STUDENT";
            case 7:
                return "LOGOUT_COURSE";
            case 8:
                return "CREATE_GRADE";
            case 9:
                return "EDITOR_GRADE";
            case 10:
                return "DELETE_GRADE";
            case 11:
                return "CREATE_TEACHER";
            case 12:
                return "EDITOR_TEACHER";
            case 13:
                return "SAVE_HOMEWORK_SCORE";
            case 14:
                return "DELETE_MESSAGE";
            case 15:
                return "CREATE_NOTICE";
            case 16:
                return "DELETE_NOTICE";
            default:
                break;
        }
        return null;
    }
}