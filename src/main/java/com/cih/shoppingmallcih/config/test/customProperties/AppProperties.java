package com.cih.shoppingmallcih.config.test.customProperties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.util.List;


@ConstructorBinding     // 생성자를 통해 프로퍼티 정보값이 설정 됨
@ConfigurationProperties("app.sbip.ct")
public class AppProperties {

    // 프로퍼티를 정의하고 이를 담는 클래스

    private final String name;
    private final String ip;
    private final int port;
    private final Security security;

    public String getName() {
        return name;
    }

    public String getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }

    public Security getSecurity() {
        return security;
    }

    public AppProperties(String name, String ip, @DefaultValue("8080") int port, Security security) {
        this.name = name;
        this.ip = ip;
        this.port = port;
        this.security = security;
    }
    @Override
    public String toString() {
        return "AppProperties{" +
                "name='" + name + '\'' +
                ", ip='" + ip + '\'' +
                ", port=" + port +
                ", security=" + security +
                '}';
    }

    public static class Security{
        private boolean enabled;
        private final String token;
        private final List<String> roles;

        public Security(boolean enabled, String token, List<String> roles) {
            this.enabled = enabled;
            this.token = token;
            this.roles = roles;
        }

        public boolean isEnabled(){
            return enabled;
        }

        public String getToken(){
            return token;
        }
        public List<String> getRoles(){
            return roles;
        }

        @Override
        public String toString() {
            return "Security{" +
                    "enabled=" + enabled +
                    ", token='" + token + '\'' +
                    ", roles=" + roles +
                    '}';
        }
    }



}
