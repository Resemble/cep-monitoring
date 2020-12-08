package org.stsffap.cep.monitoring.events;

import java.util.Objects;

/**
 * @author
 * @version V1.0
 * @Title:
 * @Package
 * @Description:
 * @date 2020-12-03 10:45
 */
public class LoginEvent {
    private String userId;
    private String ip;
    private String type;
    public LoginEvent() {
    }

    public LoginEvent(String userId, String ip, String type) {
        this.userId = userId;
        this.ip = ip;
        this.type = type;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof LoginEvent))
            return false;
        LoginEvent that = (LoginEvent) o;
        return getUserId().equals(that.getUserId()) && getIp().equals(that.getIp()) && getType()
            .equals(that.getType());
    }

    @Override public int hashCode() {
        return Objects.hash(getUserId(), getIp(), getType());
    }

    @Override public String toString() {
        return "LoginEvent{" + "userId='" + userId + '\'' + ", ip='" + ip + '\'' + ", type='" + type
            + '\'' + '}';
    }
}
