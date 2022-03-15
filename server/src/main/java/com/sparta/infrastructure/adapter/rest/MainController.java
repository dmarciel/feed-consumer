package com.sparta.infrastructure.adapter.rest;

import com.sparta.infrastructure.adapter.parser.LoadProvidersParser;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
public class MainController {


  @PostMapping("/load/{provider}")
  public int load(@PathVariable("provider") String provider, @RequestBody byte[] content){

      dumpToFile(content);


    LoadProvidersParser loadProvidersParser = new LoadProvidersParser();
    int numberOfRecords = loadProvidersParser.parseRequestContent(content);

    return numberOfRecords;

//    return 360;

  }

  static int iteration = 0;

  private void dumpToFile(byte[] content) {

    try {
      String fileName = "file" + iteration + ".txt";

//      BufferedWriter bw = null;
//      bw = Files.newBufferedWriter(Path.of(fileName), StandardCharsets.UTF_8);
//
//      for (int i = 0; i < content.length; i++) {
//        bw.write(content[i]);
//      }
//      bw.close();

      try (FileOutputStream fos = new FileOutputStream(fileName)) {
//        fos.write(bytes);

        for (int i = 0; i < content.length; i++) {
          fos.write(content[i]);
        }

      }

      iteration++;

    } catch (IOException exception) {
      exception.printStackTrace();
    }
  }

  @GetMapping("/data/{provider}/total")
  public int total(@PathVariable("provider") String provider) {
    return 100;
  }

}
