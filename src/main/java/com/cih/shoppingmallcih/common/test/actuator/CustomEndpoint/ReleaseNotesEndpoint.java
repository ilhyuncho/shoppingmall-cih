package com.cih.shoppingmallcih.common.test.actuator.CustomEndpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.DeleteOperation;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

@Component
@Endpoint(id="releaseNotes")        // ReleaseNotesEndpoint 클래스가 액추에이터 엔드포인트로 사용 된다.
public class ReleaseNotesEndpoint {

    private final Collection<ReleaseNote> releaseNotes;

    @Autowired
    public ReleaseNotesEndpoint(Collection<ReleaseNote> releaseNotes) {
        this.releaseNotes = releaseNotes;
    }

    @ReadOperation
    public Iterable<ReleaseNote> releaseNotes(){
        // 모든 버전의 릴리스 상세 정보를 반환하는 @ReadOperation 메서드를 정의 함
        return releaseNotes;
    }

    @ReadOperation
    public Object selectCourse(@Selector String version){   // @Selector 를 붙이면 releaseNotes-version이라는 이름의 엔드포인트가 추가되고
                                                            // /releaseNotes/{version}을 통해 접근 가능

        // 호출 url : http://localhost:8090/actuator/releaseNotes/v1.2.1
        
        // 버전별 상세 정보 표시
        Optional<ReleaseNote> releaseNoteOptional = releaseNotes.stream()
                .filter(releaseNote -> version.equals(releaseNote.getVersion()))
                .findFirst();

        if(releaseNoteOptional.isPresent()){
            return releaseNoteOptional.get();
        }
        return String.format("No such release version exsists: %s", version);
    }

    @DeleteOperation
    public void removeReleaseVersion(@Selector String version){
        // 호출 url : httpL//localhost:8090/actuator/releaseNotes/v1.2.1

        Optional<ReleaseNote> releaseNoteOptional = releaseNotes.stream().filter(releaseNote -> version.equals(releaseNote.getVersion()))
                .findFirst();

        if(releaseNoteOptional.isPresent()){
            releaseNotes.remove(releaseNoteOptional.get());
        }
    }

}
