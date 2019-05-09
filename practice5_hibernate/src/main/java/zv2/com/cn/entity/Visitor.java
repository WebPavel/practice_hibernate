package zv2.com.cn.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * 游客
 * @author lb
 * @date 2019/5/10
 */
@Entity
@Table(name = "usr_visitor", schema = "hibernate_practice1", catalog = "")
public class Visitor {
    private int id;
    private String name;
    private String ip;
    private Integer vipLevel;
    private Timestamp gmtCreate;
    private Timestamp gmtModified;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "ip", nullable = true, length = 255)
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Basic
    @Column(name = "vipLevel", nullable = true)
    public Integer getVipLevel() {
        return vipLevel;
    }

    public void setVipLevel(Integer vipLevel) {
        this.vipLevel = vipLevel;
    }

    @Basic
    @Column(name = "gmtCreate", nullable = true)
    public Timestamp getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Timestamp gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    @Basic
    @Column(name = "gmtModified", nullable = true)
    public Timestamp getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Timestamp gmtModified) {
        this.gmtModified = gmtModified;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Visitor visitor = (Visitor) o;

        if (id != visitor.id) return false;
        if (name != null ? !name.equals(visitor.name) : visitor.name != null) return false;
        if (ip != null ? !ip.equals(visitor.ip) : visitor.ip != null) return false;
        if (vipLevel != null ? !vipLevel.equals(visitor.vipLevel) : visitor.vipLevel != null) return false;
        if (gmtCreate != null ? !gmtCreate.equals(visitor.gmtCreate) : visitor.gmtCreate != null) return false;
        if (gmtModified != null ? !gmtModified.equals(visitor.gmtModified) : visitor.gmtModified != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (ip != null ? ip.hashCode() : 0);
        result = 31 * result + (vipLevel != null ? vipLevel.hashCode() : 0);
        result = 31 * result + (gmtCreate != null ? gmtCreate.hashCode() : 0);
        result = 31 * result + (gmtModified != null ? gmtModified.hashCode() : 0);
        return result;
    }
}
