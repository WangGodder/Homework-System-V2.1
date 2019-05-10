package top.godder.homework.domain;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class Course {
	private Integer id;
	private String name;
	private String info;
	private Integer type;
	
	public static Integer GENERAL_COMPULSORY = 0;
	public static Integer GENERAL_OPTIONAL = 1;
	public static Integer MAJOR_COMPULSORY = 2;
	public static Integer MAJOR_OPTIONAL = 3;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String printType() {
		try {
			switch (type) {
				case(0) : return new String("通识必修".getBytes("gbk"), StandardCharsets.UTF_8);
				case(1) : return new String("通识选修".getBytes("gbk"), StandardCharsets.UTF_8);
				case(2) : return new String("专业必修".getBytes("gbk"), StandardCharsets.UTF_8);
				case(3) : return new String("专业选修".getBytes("gbk"), StandardCharsets.UTF_8);
				default: break;
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}


		return null;
	}
}
