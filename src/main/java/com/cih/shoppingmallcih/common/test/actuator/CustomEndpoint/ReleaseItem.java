package com.cih.shoppingmallcih.common.test.actuator.CustomEndpoint;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReleaseItem {
    private String itemId;
    private String itemDescription;
}
