package zv2.com.cn.entity.usr.student;

import zv2.com.cn.entity.biz.course.Course;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author liubao
 * @date 2019/4/28 0:58
 */
public class Student {
    private Integer id;
    private String name;
    private String className;
    private String gender;
    private String nation;
    private Date birthday;
    private String political;
    private String idCard;

    private Set<Course> courses = new HashSet<Course>();

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

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPolitical() {
        return political;
    }

    public void setPolitical(String political) {
        this.political = political;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}
