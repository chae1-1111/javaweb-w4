package org.zerock.b01.controller;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@Log4j2
public class SampleController {

    @Getter
    class SampleDTO {
        private String p1, p2, p3;
    }

    @GetMapping("/hello")
    public void hello(Model model) {

        log.info("GET /hello...");

        model.addAttribute("msg", "HELLO WORLD");
    }

    @GetMapping("/ex/ex1")
    public void ex1(Model model) {

        log.info("GET /ex/ex1");

        List<String> list = Arrays.asList("AAA","BBB","CCC","DDD");

        model.addAttribute("list", list);
    }

    @GetMapping("/ex/ex2")
    public void ex2(Model model){

        log.info("GET /ex/ex2");

        List<String> strList = IntStream.range(1,10)
                .mapToObj(i -> "Data" + i)
                .collect(Collectors.toList());

        model.addAttribute("list", strList);

        Map<String, String> map = new HashMap<>();

        map.put("A","AAAA");
        map.put("B","BBBB");

        model.addAttribute("map", map);

        SampleDTO dto = new SampleDTO();

        dto.p1 = "dto : p1";
        dto.p2 = "dto : p2";
        dto.p3 = "dto : p3";

        model.addAttribute("dto", dto);

    }

    @GetMapping("/ex/ex3")
    public void ex3(Model model) {
         log.info("GET /ex/ex3");

         model.addAttribute("arr", new String[]{"AAA","BBB","CCC"});
    }
}
