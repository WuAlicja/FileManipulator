package com.example.RekrutacjaAPIFull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/")
public class HomeController {

    @Autowired
    private FileStorageService fileStorageService;

    private ReplaceEverySecond replaceEverySecond;
    private ReadAndCount readAndCount;

    @Autowired
    public HomeController(ReplaceEverySecond replaceEverySecond,ReadAndCount readAndCount) {
        this.replaceEverySecond = replaceEverySecond;
        this.readAndCount=readAndCount;
    }


    @PostMapping("/uploadFile")
    public Response uploadFile(@RequestParam("file") MultipartFile file) {
        String fileName = fileStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();

        return new Response(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
    }

    @PostMapping("/uploadMultipleFiles")
    public List < Response > uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        return Arrays.asList(files)
                .stream()
                .map(file -> uploadFile(file))
                .collect(Collectors.toList());
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
