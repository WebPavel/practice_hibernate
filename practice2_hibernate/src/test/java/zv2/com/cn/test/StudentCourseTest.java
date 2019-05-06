package zv2.com.cn.test;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;
import zv2.com.cn.entity.biz.course.Course;
import zv2.com.cn.entity.usr.student.Student;
import zv2.com.cn.utils.HibernateUtils;

/**
 * @author liubao
 * @date 2019/4/28 2:00
 */
public class StudentCourseTest {
    /**
     * 级联添加、级联删除
     */
    @Test
    public void testCascade() {
        Session session = HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();
        Student student = (Student) session.get(Student.class, 1);
        Course course = (Course) session.get(Course.class, 2);
        // 1号学生撤销选课2
        student.getCourses().remove(course); // 持久态
        transaction.commit();
        session.close();
    }
    @Test
    public void testDeleteCascade() {
        Session session = HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();
        Student student = (Student) session.get(Student.class, 3);
        session.delete(student);
        transaction.commit();
        session.close();
    }
    @Test
    public void testAddCascade() {
        Session session = HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();
        Student student = new Student();
        student.setName("ww");
        Course course = new Course();
        course.setName("Ruby");
        student.getCourses().add(course);
        course.getStudents().add(student);
        session.save(student);
        transaction.commit();
        session.close();
    }
    @Test
    public void testAdd() {
        Session session = HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();
        Student student = new Student();
        student.setName("zs");
        Student student1 = new Student();
        student1.setName("ls");
        Course course = new Course();
        course.setName("Java");
        Course course1 = new Course();
        course1.setName("Python");
        student.getCourses().add(course);
        student.getCourses().add(course1);
        // 课程关联学生
        course.getStudents().add(student);
        course1.getStudents().add(student);
        student1.getCourses().add(course);
        course.getStudents().add(student1);
        // 保存,此时必须有一方放弃维护外键(一般被动方放弃)否则报batchUpdateException
        session.save(student);
        session.save(student1);
        session.save(course);
        session.save(course1);
        transaction.commit();
        session.close();
    }
}
