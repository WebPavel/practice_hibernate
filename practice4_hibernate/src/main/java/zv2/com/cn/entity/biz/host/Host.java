package zv2.com.cn.entity.biz.host;

import zv2.com.cn.entity.pub.radio.Radio;

import java.util.HashSet;
import java.util.Set;

/**
 * 主持人
 * @author lb
 * @date 2019/5/8
 */
public class Host {
    private String id;
    private String username;
    private String nickname;
    private String gender;
    private Integer age;
    private Integer hostLevel;
    private Integer state;
    private Set<Radio> radios = new HashSet<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getHostLevel() {
        return hostLevel;
    }

    public void setHostLevel(Integer hostLevel) {
        this.hostLevel = hostLevel;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Set<Radio> getRadios() {
        return radios;
    }

    public void setRadios(Set<Radio> radios) {
        this.radios = radios;
    }
}
