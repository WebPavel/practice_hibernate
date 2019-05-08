package zv2.com.cn.test;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import zv2.com.cn.entity.pub.room.Room;
import zv2.com.cn.entity.usr.visitor.Visitor;
import zv2.com.cn.utils.HibernateUtils;

import java.util.List;

/**
 * @author liubao
 * @date 2019/4/29 0:49
 */
public class RoomVisitorTest {
    /**
     * 条件查询
     */
    @Test
    public void testCondition() {
        Session session = HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();
//        List<Room> rooms = session.createQuery("from Room where name like ?").setParameter(0, "%提莫%").list();
        List<Room> rooms = session.createCriteria(Room.class).add(Restrictions.like("name", "%提莫%")).list();
        for (Room room : rooms) {
            System.out.println("5-"+room.getName()+","+room.getOwner()+","+room.getAddress());
        }
        transaction.commit();
        session.close();
    }

    /**
     * 绑定参数是实体的情况
     */
    @Test
    public void testRelated() {
        Session session = HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();
        Room room = new Room();
        room.setId(1);
        List<Visitor> visitors = session.createQuery("from Visitor v where v.room = ?").setEntity(0, room).list();
        for (Visitor visitor: visitors) {
            System.out.println("4-"+visitor.getName()+","+visitor.getVipLevel()+","+visitor.getRoom());
        }
        transaction.commit();
        session.close();
    }

    /**
     * 投影查询
     */
    @Test
    public void testSelectField() {
        Session session = HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();
//        List<Object[]> attrs = session.createQuery("select r.name,r.address from Room r").list();
//        for (Object[] attr : attrs) {
//            System.out.println(Arrays.toString(attr));
//        }
        List<Room> rooms = session.createQuery("select new Room(name,owner,address) from Room").list();
        for (Room room : rooms) {
            System.out.println("3-"+room.getName()+","+room.getOwner()+","+room.getAddress());
        }
        transaction.commit();
        session.close();
    }

    /**
     * 参数绑定
     */
    @Test
    public void testParameter() {
        Session session = HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();
//        List<Room> rooms = session.createQuery("from Room where owner = ?").setString(0, "周二珂").list();
//        List<Room> rooms = session.createQuery("from Room where owner = ? and rank = ?").setString(0, "周二珂").setInteger(1, 2).list();
        List<Room> rooms = session.createQuery("from Room where owner = :owner and rank = :rank").setString("owner", "周二珂").setInteger("rank", 2).list();
        for (Room room : rooms) {
            System.out.println(room.getName()+","+room.getOwner()+","+room.getAddress());
        }
        transaction.commit();
        session.close();
    }

    /**
     * 单条记录
     */
    @Test
    public void testUniqueResult() {
        Session session = HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();
//        Room room = (Room) session.createQuery("from Room where owner = ?").setString(0, "大司马").uniqueResult();
        Room room = (Room) session.createCriteria(Room.class).add(Restrictions.eq("owner", "大司马")).uniqueResult();
        System.out.println(room.getName()+","+room.getOwner()+","+room.getAddress());
        transaction.commit();
        session.close();
    }

    /**
     * 分页查询
     */
    @Test
    public void testLimit() {
        Session session = HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();
//        List<Visitor> visitors = session.createQuery("from Visitor").setFirstResult(0).setMaxResults(10).list();
        List<Visitor> visitors = session.createCriteria(Visitor.class).setFirstResult(10).setMaxResults(10).list();
        for (Visitor visitor : visitors) {
            System.out.println(visitor.getName()+","+visitor.getVipLevel()+","+visitor.getRoom());
        }
        transaction.commit();
        session.close();
    }

    /**
     * 按列排序
     */
    @Test
    public void testOrderBy() {
        Session session = HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();
//        List<Room> rooms = session.createQuery("from Room r order by r.rank desc ").list();
        List<Room> rooms = session.createCriteria(Room.class).addOrder(Order.asc("rank")).list();
        for (Room room : rooms) {
            System.out.println(room.getName()+","+room.getOwner()+","+room.getAddress());
        }
        transaction.commit();
        session.close();
    }
    /**
     * 多态查询
     */
    @Test
    public void testPolymorphismQuery() {
        Session session = HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();
        List<Object> objects = session.createQuery("from java.lang.Object").list();
        System.out.println(objects);
        transaction.commit();
        session.close();
    }
    @Test
    public void testQuery() {
        Session session = HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();
//        List<Room> rooms = session.createQuery("from Room").list();
//        List<Room> rooms = session.createCriteria(Room.class).list();
//        for (Room room : rooms) {
//            System.out.println(room.getName()+","+room.getOwner()+","+room.getAddress());
//        }
//        List<Object[]> rooms = session.createSQLQuery("SELECT * FROM pub_room").list();
//        for (Object[] room : rooms) {
//            System.out.println(Arrays.toString(room));
//        }
//        List<Room> rooms = session.createSQLQuery("SELECT * FROM pub_room").addEntity(Room.class).list();
//        for (Room room : rooms) {
//            System.out.println(room.getName()+","+room.getOwner()+","+room.getAddress());
//        }
//        List<Room> rooms = session.createQuery("FROM Room as r").list();
//        List<Room> rooms = session.createQuery("FROM Room r").list();
//        List<Room> rooms = session.createQuery("FROM Room r where r.owner = ?").setString(0, "冯提莫").list();
        List<Room> rooms = session.createQuery("select r FROM Room r").list();
        for (Room room : rooms) {
            System.out.println(room.getName()+","+room.getOwner()+","+room.getAddress());
        }
        transaction.commit();
        session.close();
    }
    @Test
    public void testAdd() {
        Session session = HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();
        Room room = new Room();
        room.setName("冯提莫的房间");
        room.setOwner("冯提莫");
        room.setAddress("https://www.douyu.com/520520");
        room.setRank(1);
        // 添加机器人
        for (int i = 0; i < 10; i++) {
            Visitor visitor = new Visitor();
            visitor.setName(room.getName()+"机器人"+i+"号");
            visitor.setVipLevel(3);
            visitor.setRoom(room);
            room.getVisitors().add(visitor);
        }
        session.save(room);
        Room room2 = new Room();
        room2.setName("周二珂的房间");
        room2.setOwner("周二珂");
        room2.setAddress("https://www.douyu.com/5201314");
        room2.setRank(2);
        // 添加机器人
        for (int i = 0; i < 10; i++) {
            Visitor visitor = new Visitor();
            visitor.setName(room2.getName()+"机器人"+i+"号");
            visitor.setVipLevel(2);
            visitor.setRoom(room2);
            room2.getVisitors().add(visitor);
        }
        session.save(room2);
        Room room3 = new Room();
        room3.setName("大司马的房间");
        room3.setOwner("大司马");
        room3.setAddress("https://www.douyu.com/168168");
        room3.setRank(2);
        // 添加机器人
        for (int i = 0; i < 10; i++) {
            Visitor visitor = new Visitor();
            visitor.setName(room3.getName()+"机器人"+i+"号");
            visitor.setVipLevel(1);
            visitor.setRoom(room3);
            room3.getVisitors().add(visitor);
        }
        session.save(room3);
        transaction.commit();
        session.close();
    }
}
