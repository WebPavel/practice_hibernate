package zv2.com.cn.entity.biz.course;

import zv2.com.cn.entity.usr.student.Student;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author liubao
 * @date 2019/4/28 0:58
 */
public class Course {
    private Integer id;
    private String name;
    private Date startTime;
    private String address;
    private Double score;
    private String teacherName;

    private Set<Student> students = new HashSet<Student>();

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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }
}
