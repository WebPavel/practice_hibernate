package zv2.com.cn.test;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import zv2.com.cn.entity.pub.room.Room;
import zv2.com.cn.utils.HibernateUtils;

import java.util.Arrays;
import java.util.List;

/**
 * 多表联合查询
 * @author lb
 * @date 2019/5/8
 */
public class RoomVisitorTest2 {
    /**
     * 内连接
     */
    @Test
    public void test1() {
        Session session = HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();
        List<Object[]> list = session.createQuery("from Room r inner join r.visitors").list();
        for (Object[] os : list) {
            System.out.println(Arrays.toString(os));
        }
        // 迫切内连接
        List<Room> rooms = session.createQuery("select distinct r from Room r inner join fetch r.visitors").list();
        for (Room room : rooms) {
            System.out.println("6-"+room.getName()+","+room.getOwner()+","+room.getAddress()+","+room.getVisitors());
        }
        transaction.commit();
        session.close();
    }

    /**
     * 聚集函数
     */
    @Test
    public void test2() {
        Session session = HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();
        Long count = (Long) session.createQuery("select count(*) from Room r").uniqueResult();
        System.out.println(count);
        transaction.commit();
        session.close();
    }

    /**
     * 命名查询
     */
    @Test
    public void test3() {
        Session session = HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();
        List<Room> rooms = session.getNamedQuery("findAll").list();
        for (Room room : rooms) {
            System.out.println("7-"+room.getName()+","+room.getOwner()+","+room.getAddress());
        }
        transaction.commit();
        session.close();
    }
    /**
     * 离线条件查询(*****)
     */
    @Test
    public void test4() {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Room.class);
        detachedCriteria.add(Restrictions.eq("owner", "冯提莫"));
        detachedCriteria.add(Restrictions.eq("rank", 1));
        Session session = HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();
        List<Room> rooms = detachedCriteria.getExecutableCriteria(session).list();
        for (Room room : rooms) {
            System.out.println("8-"+room.getName()+","+room.getOwner()+","+room.getAddress());
        }
        transaction.commit();
        session.close();
    }
}
