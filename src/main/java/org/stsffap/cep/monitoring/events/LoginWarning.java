package org.stsffap.cep.monitoring.events;

import java.util.Objects;

/**
 * @author
 * @version V1.0
 * @Title:
 * @Package
 * @Description:
 * @date 2020-12-03 10:46
 */
public class LoginWarning {
    private String userId;
    private String ip;
    private String type;
    public LoginWarning() {
    }

    public LoginWarning(String userId, String type, String ip) {
        this.userId = userId;
        this.type = type;
        this.ip = ip;
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

    @Override
    public String toString() {
        return "LoginWarning{" +
            "userId='" + userId + '\'' +
            ", type='" + type + '\'' +
            ", ip='" + ip + '\'' +
            '}';
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof LoginWarning))
            return false;
        LoginWarning that = (LoginWarning) o;
        return Objects.equals(getUserId(), that.getUserId()) && Objects
            .equals(getIp(), that.getIp()) && Objects.equals(getType(), that.getType());
    }

    @Override public int hashCode() {
        return Objects.hash(getUserId(), getIp(), getType());
    }
}
