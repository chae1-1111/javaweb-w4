package org.zerock.b01.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
public class SampleJSONController {

    @GetMapping("/helloArr")
    public String[] helloArr() {
        log.info("GET /helloArr");

        return new String[]{"aaa","bbb","ccc"};
    }
}
