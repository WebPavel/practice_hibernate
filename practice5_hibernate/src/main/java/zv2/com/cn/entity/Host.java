package zv2.com.cn.entity;

import javax.persistence.*;

/**
 * 主持人
 * @author lb
 * @date 2019/5/10
 */
@Entity
@Table(name = "biz_host", schema = "hibernate_practice1", catalog = "")
public class Host {
    private String id;
    private String username;
    private String nickname;
    private String gender;
    private Integer age;
    private Integer hostLevel;
    private Integer state;

    @Id
    @Column(name = "id", nullable = false, length = 255)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "username", nullable = true, length = 255)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "nickname", nullable = true, length = 255)
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Basic
    @Column(name = "gender", nullable = true, length = 255)
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Basic
    @Column(name = "age", nullable = true)
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Basic
    @Column(name = "hostLevel", nullable = true)
    public Integer getHostLevel() {
        return hostLevel;
    }

    public void setHostLevel(Integer hostLevel) {
        this.hostLevel = hostLevel;
    }

    @Basic
    @Column(name = "state", nullable = true)
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Host host = (Host) o;

        if (id != null ? !id.equals(host.id) : host.id != null) return false;
        if (username != null ? !username.equals(host.username) : host.username != null) return false;
        if (nickname != null ? !nickname.equals(host.nickname) : host.nickname != null) return false;
        if (gender != null ? !gender.equals(host.gender) : host.gender != null) return false;
        if (age != null ? !age.equals(host.age) : host.age != null) return false;
        if (hostLevel != null ? !hostLevel.equals(host.hostLevel) : host.hostLevel != null) return false;
        if (state != null ? !state.equals(host.state) : host.state != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (nickname != null ? nickname.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (age != null ? age.hashCode() : 0);
        result = 31 * result + (hostLevel != null ? hostLevel.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        return result;
    }
}
