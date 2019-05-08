package zv2.com.cn.entity.pub.room;

import zv2.com.cn.entity.usr.visitor.Visitor;

import java.util.HashSet;
import java.util.Set;

/**
 * 聊天室
 * @author liubao
 * @date 2019/4/28 3:24
 */
public class Room {
    private Integer id;
    private String name;
    private String owner;
    private String address;
    private Integer rank;

    private Set<Visitor> visitors = new HashSet<Visitor>();

    private Integer version;

    public Room() {
    }

    public Room(String name, String owner, String address) {
        this.name = name;
        this.owner = owner;
        this.address = address;
    }

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

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Set<Visitor> getVisitors() {
        return visitors;
    }

    public void setVisitors(Set<Visitor> visitors) {
        this.visitors = visitors;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
