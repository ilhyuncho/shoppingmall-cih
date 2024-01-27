package com.cih.shoppingmallcih.controller.restTemplateForServer;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vi/crud-api")
@Log4j2
public class CrudController {

    @GetMapping
    public String getName() {
        return "Flatuer";
    }

    @GetMapping(value = "/{variable}")
    public String getVariable(@PathVariable String variable) {
        return variable;
    }

    @GetMapping("/param")
    public String getNameWithParam(@RequestParam String name) {
        return "Hello. " + name + "!";
    }

    @PostMapping
    public ResponseEntity<MemberDTO> getMember(
            @RequestBody MemberDTO request,
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String organization) {

        log.info(request.getName());
        log.info(request.getEmail());
        log.info(request.getOrganization());

        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setName(name);
        memberDTO.setEmail(email);
        memberDTO.setOrganization(organization);

        return ResponseEntity.status(HttpStatus.OK).body(memberDTO);

    }

    @PostMapping(value = "/add-header")
    public ResponseEntity<MemberDTO> addHeader(@RequestHeader("my-header") String header,
                                               @RequestBody MemberDTO memberDTO){
        log.info(header);

        return ResponseEntity.status(HttpStatus.OK).body(memberDTO);
    }
}
