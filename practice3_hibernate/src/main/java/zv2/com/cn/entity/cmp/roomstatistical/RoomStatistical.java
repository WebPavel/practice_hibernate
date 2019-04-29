package zv2.com.cn.entity.cmp.roomstatistical;

import java.math.BigDecimal;

/**
 * 房间流水
 * @author liubao
 * @date 2019/4/28 3:39
 */
public class RoomStatistical {
    // 注册用户
    private Long visitors;
    // 所有用户（游客+IP）
    private Long peoples;
    // 付费用户
    private Long members;
    // 每日收入
    private BigDecimal dailyRevenue;
    private Integer roomId;

    public Long getVisitors() {
        return visitors;
    }

    public void setVisitors(Long visitors) {
        this.visitors = visitors;
    }

    public Long getPeoples() {
        return peoples;
    }

    public void setPeoples(Long peoples) {
        this.peoples = peoples;
    }

    public Long getMembers() {
        return members;
    }

    public void setMembers(Long members) {
        this.members = members;
    }

    public BigDecimal getDailyRevenue() {
        return dailyRevenue;
    }

    public void setDailyRevenue(BigDecimal dailyRevenue) {
        this.dailyRevenue = dailyRevenue;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }
}
