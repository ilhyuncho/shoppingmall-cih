package com.cih.shoppingmallcih.common.test.actuator.CustomEndpoint;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Builder
@Getter
@Setter
public class ReleaseNote {
    private String version;
    private LocalDate releaseDate;
    private String commitTag;
    private Set<ReleaseItem> newReleases;
    private Set<ReleaseItem> bugFixes;
}
