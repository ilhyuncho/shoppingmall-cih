package com.cih.shoppingmallcih.common.test.actuator.InfoContributor;


import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CustomInfoContributor implements InfoContributor {


    @Override
    public void contribute(Info.Builder builder) {
        //  출력할 내용 셋팅
        Map<String, Object> content = new HashMap<>();

        content.put("code-info", "infoContributor 구현체에서 정의한 정보");
        builder.withDetail("custom-info", content);
    }
}
