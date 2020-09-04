package com.example.RekrutacjaAPIFull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/")
public class HomeController {


    private ReplaceEverySecond replaceEverySecond;
    private ReadAndCount readAndCount;

    @Autowired
    public HomeController(ReplaceEverySecond replaceEverySecond,ReadAndCount readAndCount) {
        this.replaceEverySecond = replaceEverySecond;
        this.readAndCount=readAndCount;
    }

    @PostMapping("readandcount")
    public Map<String,Integer> readAndCount(@RequestParam String pathName) {

        return readAndCount.getStringCount(readAndCount.getStringFile(pathName));
    }

    @PostMapping("replaceeverysecond")
    public List<String> replaceEverySecond(@RequestParam String pathName) {

        return replaceEverySecond.replace(replaceEverySecond.getStringFile(pathName));

    }

}
