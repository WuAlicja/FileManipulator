package com.example.RekrutacjaAPIFull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@AllArgsConstructor
@Data
@Service
public class ReplaceEverySecond {

    // private String pathName;

    public String getStringFile(String pathName) {
        StringBuilder stringFile = new StringBuilder();
//        System.out.println("Enter input file location path:");
//        Scanner scanner1 = new Scanner(System.in);
//        String pathName = scanner1.next(); ///Users/alicja/Downloads/JavaSzkolenie/StringInputFile.txt

        try (Scanner scanner = new Scanner(new File(pathName));) {
            while (scanner.hasNextLine()) {
                stringFile.append(scanner.nextLine());
                stringFile.append("koniec linii");
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found or wrong path");
        }
        return stringFile.toString();
    }

    public List<String> replace(String input) {

        String[] array = input.split("<br>");
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            if (i % 2 == 0) {
                stringBuilder.append(array[i]);
                stringBuilder.append("<br>");
            } else if (i % 2 == 1) {
                stringBuilder.append(array[i]);
                stringBuilder.append("</br></br>");
            }
        }
        String[] newArray = stringBuilder.toString().split("koniec linii");
        List<String> stringList = new ArrayList<>();
        for (String s : newArray
        ) {
            stringList.add(s);
        }
        return stringList;

    }
    public  void printList(List<String> stringList) {
        for (String s : stringList
        ) {
            System.out.println(s);
        }
    }
}
