package com.cih.shoppingmallcih.common.test.actuator.CustomEndpoint;

import org.springframework.boot.actuate.endpoint.annotation.DeleteOperation;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Endpoint(id="note", enableByDefault=true)    // 자동으로 액추에이터에 엔드포인트로 등록 됨
public class NoteEndpoint {

    private Map<String, Object> noteContent = new HashMap<>();

    @ReadOperation
    public Map<String, Object> getNote(){
        return noteContent;
    }

    @WriteOperation
    public Map<String, Object> writeNote(String key, Object value) {
        noteContent.put(key, value);
        return noteContent;
    }

    @DeleteOperation
    public Map<String, Object> deleteNode(String key) {
        noteContent.remove(key);
        return noteContent;
    }
}
