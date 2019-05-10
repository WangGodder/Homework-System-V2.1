package top.godder.homework.domain;

import java.util.Date;

public class TeachCourse {
	private Integer id;
	private Integer courseId;
	private Integer gradeId;
	private String teacherId;
	private Date semestor;
	private Boolean isPublic;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	public Integer getGradeId() {
		return gradeId;
	}
	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}
	public String getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}
	public Date getSemestor() {
		return semestor;
	}
	public void setSemestor(Date semestor) {
		this.semestor = semestor;
	}
	public Boolean getIsPublic() {
		return isPublic;
	}
	public void setIsPublic(Boolean isPublic) {
		this.isPublic = isPublic;
	}
	public String printSemestor() {
		return this.semestor.toLocaleString();
	}
}	
