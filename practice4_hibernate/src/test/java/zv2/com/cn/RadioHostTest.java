package zv2.com.cn;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;
import zv2.com.cn.entity.biz.host.Host;
import zv2.com.cn.utils.HibernateUtils;

import java.util.Iterator;
import java.util.List;

/**
 * @author lb
 * @date 2019/5/8
 */
public class RadioHostTest {
    @Test
    public void testClassCacheRegion() {
        Session session = HibernateUtils.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Host host = (Host) session.get(Host.class, "402881e66a98a750016a98a752a70000");
        Host host2 = (Host) session.get(Host.class, "402881e66a98a750016a98a752a70000");
        System.out.println(host == host2);
        transaction.commit();
        session = HibernateUtils.getCurrentSession();
        transaction = session.beginTransaction();
        Host host3 = (Host) session.get(Host.class, "402881e66a98a750016a98a752a70000");
        Host host4 = (Host) session.get(Host.class, "402881e66a98a750016a98a752a70000");
        System.out.println(host3 == host4);
        System.out.println(host == host3);
        transaction.commit();
    }

    /**
     * 由于集合缓存区保存的是对象的ID，所以
     * 集合缓存区依赖类缓存区
     */
    @Test
    public void testCollectionCacheRegion() {
        Session session = HibernateUtils.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Host host = (Host) session.get(Host.class, "402881e66a98a750016a98a752a70000");
        System.out.println("current host's radio-size:" + host.getRadios().size());
        transaction.commit();
        session = HibernateUtils.getCurrentSession();
        transaction = session.beginTransaction();
        Host host1 = (Host) session.get(Host.class, "402881e66a98a750016a98a752a70000");
        System.out.println("current host's radio-size:" + host1.getRadios().size());
        transaction.commit();
    }

    /**
     * list会向二级缓存放入数据，但是不会从二级缓存中取数据
     */
    @Test
    public void testList() {
        Session session = HibernateUtils.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        List<Host> hosts = session.createQuery("from Host").list();
        for (Host host : hosts) {
            System.out.println(host.getNickname()+"-"+host.getGender()+"-"+host.getAge());
        }
        transaction.commit();
        session = HibernateUtils.getCurrentSession();
        transaction = session.beginTransaction();
        Host host = (Host) session.get(Host.class, "402881e66a98a750016a98a752a70000");
        System.out.println(host.getNickname()+"-"+host.getGender()+"-"+host.getAge());
        transaction.commit();
        session = HibernateUtils.getCurrentSession();
        transaction = session.beginTransaction();
        List<Host> hosts1 = session.createQuery("from Host").list();
        for (Host hst : hosts1) {
            System.out.println(hst.getNickname()+"-"+hst.getGender()+"-"+hst.getAge());
        }
        transaction.commit();
    }

    /**
     * iterate()发送N+1条SQL，使用了二级缓存
     * 首先检索所有记录ID，再根据ID去一条一条查询记录
     */
    @Test
    public void testIterator() {
        Session session = HibernateUtils.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Iterator<Host> hosts = session.createQuery("from Host").iterate();
        while (hosts.hasNext()) {
            Host host = hosts.next();
            System.out.println(host.getNickname()+"-"+host.getGender()+"-"+host.getAge());
        }
        transaction.commit();
        session = HibernateUtils.getCurrentSession();
        transaction = session.beginTransaction();
        Iterator<Host> hosts1 = session.createQuery("from Host").iterate();
        while (hosts.hasNext()) {
            Host host = hosts.next();
            System.out.println(host.getNickname()+"-"+host.getGender()+"-"+host.getAge());
        }
        transaction.commit();
    }

    /**
     * 一级缓存的数据会同步更新到二级缓存
     */
    @Test
    public void testSync() {
        Session session = HibernateUtils.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Host host = (Host) session.get(Host.class, "402881e66a98a750016a98a752a70000");
        host.setUsername("张靓颖");
        transaction.commit();
        session = HibernateUtils.getCurrentSession();
        transaction = session.beginTransaction();
        Host host1 = (Host) session.get(Host.class, "402881e66a98a750016a98a752a70000");
        System.out.println(host1.getUsername()+"-"+host1.getNickname());
        transaction.commit();
    }

    /**
     * 更新时间戳区
     */
    @Test
    public void testUpdateTimestamps() {
        Session session = HibernateUtils.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Host host = (Host) session.get(Host.class, "402881e66a98a750016a98a752a70000");
        session.createQuery("update Host set username = '超级女声' where id = '402881e66a98a750016a98a752a70000'").executeUpdate();
        transaction.commit();
        session = HibernateUtils.getCurrentSession();
        transaction = session.beginTransaction();
        Host host1 = (Host) session.get(Host.class, "402881e66a98a750016a98a752a70000");
        System.out.println(host1.getUsername()+"-"+host1.getNickname());
        transaction.commit();
    }

    /**
     * 查询缓存区依赖二级缓存
     * 查询缓存区缓存的是类的属性
     */
    @Test
    public void testQueryCacheRegion() {
        Session session = HibernateUtils.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("select h.username from Host h");
        // 使用查询缓存
        query.setCacheable(true);
        query.list();
        transaction.commit();

        session = HibernateUtils.getCurrentSession();
        transaction = session.beginTransaction();
        query = session.createQuery("select h.username from Host h");
        // 使用查询缓存
        query.setCacheable(true);
        query.list();
        transaction.commit();
    }
}
