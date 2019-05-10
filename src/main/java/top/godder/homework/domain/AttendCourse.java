package top.godder.homework.domain;

public class AttendCourse {
	private String studentId;
	private Integer teachCourseId;
	private Integer usualScore;
	private Integer homeworkScore;
	private Integer finalScore;
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public Integer getTeachCourseId() {
		return teachCourseId;
	}
	public void setTeachCourseId(Integer teachCourseId) {
		this.teachCourseId = teachCourseId;
	}
	public Integer getUsualScore() {
		return usualScore;
	}
	public void setUsualScore(Integer usualScore) {
		this.usualScore = usualScore;
	}
	public Integer getHomeworkScore() {
		return homeworkScore;
	}
	public void setHomeworkScore(Integer homeworkScore) {
		this.homeworkScore = homeworkScore;
	}
	public Integer getFinalScore() {
		return finalScore;
	}
	public void setFinalScore(Integer finalScore) {
		this.finalScore = finalScore;
	}
}
