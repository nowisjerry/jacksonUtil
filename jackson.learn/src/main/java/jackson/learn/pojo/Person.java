package jackson.learn.pojo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jackson.learn.enums.SexEnum;

public class Person implements Serializable {
	/**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    @JsonProperty("nameInfo")//����������������
    private String name;
    
    private Date workFromDay;
    
    private SexEnum sex;
    
    @JsonIgnore//��������
    private int age;

    public String getName() {
        return name;
    }

    
    public void setName(String name) {
        this.name = name;
    }

    public Date getWorkFromDay() {	
        return workFromDay;
    }

    public void setWorkFromDay(Date workFromDay) {
        this.workFromDay = workFromDay;
    }
    
    
    public int getAge() {
        return age;
    }

    
    public void setAge(int age) {
        this.age = age;
    }

	public SexEnum getSex() {
		return sex;
	}

	public void setSex(SexEnum sex) {
		this.sex = sex;
	}

	public String toString(){
		return "name: " + name + ";age: " + age + ";workFromDay: " + workFromDay + ";sex: " + sex.toString();
	}
	
	
}
