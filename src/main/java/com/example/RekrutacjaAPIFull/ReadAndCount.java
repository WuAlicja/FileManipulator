package com.example.RekrutacjaAPIFull;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;
@AllArgsConstructor
@Data
@Service
public class ReadAndCount {


    public  List<String> getStringFile(String pathName) {
        List<String> stringFile = new ArrayList<>();
//        System.out.println("Enter input file location path:");
//        Scanner scanner1 = new Scanner(System.in);
//        String pathName = scanner1.next(); ///Users/alicja/Downloads/JavaSzkolenie/InputFile.txt

        try (Scanner scanner = new Scanner(new File(pathName));) {
            while (scanner.hasNextLine()) {
                stringFile.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found or wrong path");
        }
        return stringFile;
    }

    public  Map<String, Integer> getStringCount(List<String> inputFile) {
        Map<String, Integer> frequencyMap = new TreeMap<>();
        for (String s : inputFile
        ) {
            frequencyMap.put(s, Collections.frequency(inputFile, s));

        }
        return frequencyMap;
    }

    public  Map<String, Integer> getMapSortedByValue(Map<String, Integer> map, boolean reversed) {
        if (reversed) {
            Map<String, Integer> sortedByCount = map.entrySet()
                    .stream()
                    .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
            return sortedByCount;
        } else {
            Map<String, Integer> sortedByCount = map.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue())
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
            return sortedByCount;
        }

    }

    public  void printList(List<String> stringList) {
        for (String s : stringList
        ) {
            System.out.println(s);
        }
    }

    public  void printMap(Map<String, Integer> map) {
        for (Map.Entry<String, Integer> entry : map.entrySet())
            System.out.println(entry.getKey() +
                    ": " + entry.getValue());

    }
}
