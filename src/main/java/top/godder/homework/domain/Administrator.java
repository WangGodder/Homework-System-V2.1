package top.godder.homework.domain;

/**
 * @author Godder
 */
public class Administrator {
	public static int TOP_AMINISTRATOR = 1;
	public static int STUDENT_ADMINISTRATOR = 2;
	public static int TEACHER_ADMINISTRATOR = 3;
	public static int COURSE_ADMINISTRATOR = 4;
	private String id;
	private String name;
	private String password;
	private Integer type;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
}
