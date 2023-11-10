package com.cih.shoppingmallcih.common.test.actuator.InfoContributor;

import com.cih.shoppingmallcih.domain.test.customRepository.Cource;
import com.cih.shoppingmallcih.service.test.CourceService;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CourceInfoContributor implements InfoContributor {

    private CourceService courceService;

    @Autowired
    public CourceInfoContributor(CourceService courceService){
        this.courceService = courceService;
    }

    @Override
    public void contribute(Info.Builder builder) {
        Map<String, Integer> courceNameRatingMap = new HashMap<>();
        List<CourceNameRating> courceNameRatingList = new ArrayList<>();

        for(Cource cource : courceService.getAvailableCources()){
            courceNameRatingList.add(CourceNameRating.builder().name(cource.getName())
                    .rating(cource.getRating()).build());
        }

        // Info.Builder는 이름 그대로 info 엔드포인트에 나타낼 데이터를 빌드하는 역할을 담당
        builder.withDetail("cources", courceNameRatingList);
    }

    @Builder
    @Data
    private static class CourceNameRating{
        String name;
        int rating;
    }
}
