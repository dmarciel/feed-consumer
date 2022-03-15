package com.sparta.controller;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sparta.classes.Reader;
import com.sparta.classes.Reader2;
import com.sparta.domain.SpecialString;

@RestController
public class MainController {


  int position = 0;

  static byte[] allBytes;

//	static boolean firstTime = true;

//		String fileName = "file3.txt";
//		dumpToFile(content, fileName);

  @PostMapping(path = "/load/{provider}", consumes = {MediaType.APPLICATION_OCTET_STREAM_VALUE})
  public int load(@PathVariable("provider") String provider, @RequestBody byte[] content)
          throws IOException, ClassNotFoundException, InterruptedException {

    position = 0;
    allBytes = content;

    System.out.println();
    System.out.println();

    long numberOfRecords = readLong();

    System.out.println("numberOfRecords: '" + numberOfRecords + "'");

    for(long recordNumber = 0; recordNumber < numberOfRecords; recordNumber ++) {

      System.out.println();
      System.out.print("recordIndex: '" + readLong() + "'"); //en la documentacion viene como int pero es long
//				System.out.println("feed: '" + readInt() + "'");	//this I dont undesrtand
      System.out.print(" timeStamp: '" + new Date(readLong()) + "'");
      System.out.print(" city: '" + readString() + "'");
      System.out.println(" numberBytesSensorData: '" + readInt() + "'");

      {//esta seccion no se si habrá que contra los bytes, en principio no lo voy a hacer si no es necesario, lo mismo con el crc32

        int numberOfSensors = readInt();
        System.out.println("numberOfSensors: '" + numberOfSensors + "'");
        for (int sernsorId = 0; sernsorId < numberOfSensors; sernsorId++) {
          System.out.println("id: '" + readString() + "', measure: '" + readInt() + "'");
        }

        System.out.println("crc32SensorsData: '" + readLong() + "'");

      }

      System.out.println();
    }


    return (int) numberOfRecords; //I can´t change the signature so I have to truncate it

  }



  private long readLong() {

    byte[] bytes = loadNextBytes(Long.BYTES);

    long long_ = bytesToLong(bytes);

    return long_;
  }

  private int readInt() {

    byte[] bytes = loadNextBytes(Integer.BYTES);

    int int_ = bytesToInt(bytes);

    return int_;
  }

  private String readString() {
    int strLenght = (int) readInt();

    byte[] bytes = loadNextBytes(strLenght);

    return new String( bytes );
  }

  private byte[] loadNextBytes(int length) {

    byte[] bytes = new byte[length];

    for (int i = 0; i < length; i++) {
      bytes[i] = allBytes[position + i];
    }

    position += length;

    return bytes;
  }





  private void printNext8Bytes(byte[] allBytes, int realativePosition) {

    byte[] bytes = new byte[8];

    for (int i = 0; i < bytes.length; i++) {
      bytes[i] = allBytes[position + realativePosition + i];
    }

    byte[] first2Bytes = new byte[] {bytes[0], bytes[1], bytes[2], bytes[3]};
    byte[] second2Bytes = new byte[] {bytes[4], bytes[5], bytes[6], bytes[7]};

    int integer1_ = bytesToInt(first2Bytes);
    int integer2_ = bytesToInt(second2Bytes);
    long long_ = bytesToLong(bytes);

    System.out.print("i1= '" + integer1_ + "' ");
    System.out.print("i2= '" + integer2_ + "' ");
    System.out.print("l= '" + long_ + "' ");
    System.out.print("s= '" + new String(bytes) + "' ");

//		System.out.println("next as string = '" + new String(bytes) + "'");
//		System.out.println("next as integer 1 = '" + integer1_ + "'");
//		System.out.println("next as integer 2 = '" + integer2_ + "'");
//		System.out.println("next as long = '" + long_ + "'");
  }

  public long bytesToLong(byte[] bytes) {
    ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
    buffer.put(bytes);
    buffer.flip();//need flip
    return buffer.getLong();
  }

  public int bytesToInt(byte[] bytes) {
    ByteBuffer buffer = ByteBuffer.allocate(Integer.BYTES);
    buffer.put(bytes);
    buffer.flip();//need flip
    return buffer.getInt();
  }


//	private void dumpToFile(byte[] content, String fileName) throws IOException {
//
//		if(firstTime == false) return;
//		firstTime = false;
//
//		BufferedWriter bw = Files.newBufferedWriter(Path.of(fileName), StandardCharsets.UTF_8);
//
//		for (int i = 0; i < content.length; i++) {
//
//			bw.write(content[i]);
//		}
//
//		bw.close();
//	}



  private void parseLoadBatch(Reader2 r) throws IOException {

    System.out.println("LoadBatch");
    long numberOfRecords = r.nextLong();
    System.out.println("numberOfRecords " + numberOfRecords);
    System.out.println("" + r.nextInt());	//este no se lo que es
    System.out.println();

    for (long i = 0; i < numberOfRecords; i++) {
      getRecord(r);
    }
    System.out.println();
  }



  private void getRecord(Reader2 r) throws IOException {
    //Record
    System.out.println("Record");
    System.out.println("recordIndex " + r.nextInt());
    System.out.println("timestamp " + new Date(r.nextLong()));

    getSpecialString(r);	//city

    long numberBytesSensorData = r.nextLong();
    System.out.println("numberBytesSensorData " + numberBytesSensorData);

//		System.out.println("   numberBytesSensorData '" + new String(r.nextBytes((int) numberBytesSensorData))+ "'");

    for (int i = 0; i < numberBytesSensorData; i++) {
      System.out.println("numberBytesSensorData " + i + "  " + r.nextInt());
//			System.out.println("numberBytesSensorData " + i);
//			getSensorCollection(r);
    }

    long crc32SensorsData = r.nextLong();
    System.out.println("crc32SensorsData " + crc32SensorsData);

    System.out.println();
//		getSensorCollection(r);
  }



  private void getSensorCollection(Reader2 r) throws IOException {

    System.out.println("SensorCollection");
    int numberOfSensors = r.nextInt();
    System.out.println("sensor collection " + numberOfSensors);
    System.out.println();

    for (int i = 0; i < numberOfSensors; i++) {

      getSensor(r);
    }
  }

  private void getSensor(Reader2 r) throws IOException {
    //Sensor
    System.out.println("Sensor");
    getSpecialString(r);

    System.out.println("measure " + r.nextInt());
    System.out.println();
  }



  private void getSpecialString(Reader2 r) throws IOException {
    //String
    System.out.println("   String");
    int strLenght = r.nextInt();
    System.out.println("   city lenght: " + strLenght);
    System.out.println("   city name '" + new String(r.nextBytes(strLenght))+ "'");
  }



  private void m1Working(byte[] content) {
    Reader reader = new Reader(content);

    long numberOfRecords = reader.readLong();
    System.out.println("numberOfRecords " + numberOfRecords);

    int l = reader.readInt();
    System.out.println("l " + l);

    int index = reader.readInt();
    System.out.println("index " + index);

    long timestamp = reader.readLong();
    System.out.println("timestamp " + timestamp);

    SpecialString ss;


    int stringLenght = reader.readInt();
    System.out.println("stringLenght " + stringLenght);

    byte[] city = reader.readbytes(stringLenght);
    System.out.println("city " + String.valueOf(city));
  }



  public static byte[] serialize(Object obj) throws IOException {
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    ObjectOutputStream os = new ObjectOutputStream(out);
    os.writeObject(obj);
    return out.toByteArray();
  }

  public static Object deserialize(byte[] data) throws IOException, ClassNotFoundException {
    ByteArrayInputStream in = new ByteArrayInputStream(data);
    ObjectInputStream is = new ObjectInputStream(in);
    return is.readObject();
  }



  @GetMapping("/data/{provider}/total")
  public int total(@PathVariable("provider") String provider) {
    return 100;
  }

//	long
//	int
//	long
//	string
//
//	Reader2 r = new Reader2(content);
//
//	System.out.println("enteros: ");
//	for (int i = 0; i < 1000; i++) {
//		System.out.print( r.nextInt() + " ");
//	}
//	System.out.println();
//
//	Reader2 r2 = new Reader2(content);
//	r2.nextInt();
//	System.out.println("largos: ");
//	for (int i = 0; i < 1000; i++) {
//		System.out.print( r2.nextLong() + " ");
//	}
//	System.out.println();
//
//	Reader2 r3 = new Reader2(content);
//	System.out.println("string: " + new String( r3.nextBytes(content.length)));



//	r.close();











//	System.out.println("LoadBatch");
//	long numberOfRecords = r.nextLong();
//	System.out.println("numberOfRecords " + numberOfRecords);
//
//	System.out.println("" + r.nextInt());	//este no se lo que es
//	System.out.println();
//
//	for (long i = 0; i < numberOfRecords; i++) {
//
//
//		//Record
//		System.out.println(" Record");
//		System.out.println(" recordIndex " + r.nextInt());
////		System.out.println(" recordIndex " + r.nextLong());
//		System.out.println(" timestamp " + new Date(r.nextLong()));
//		getSpecialString(r);	//city
//
//		int numberBytesSensorData = r.nextInt();
//		System.out.println(" numberBytesSensorData " + numberBytesSensorData);
////		System.out.println( r.nextInt());
//
//		for (int j = 0; j < numberBytesSensorData; j++) {
//			System.out.println("  numberBytesSensorData " + j);
//
//			System.out.println("  SensorCollection");
//			int numberOfSensors = r.nextInt();
//			System.out.println("  sensor collection " + numberOfSensors);
//
//			for (int k = 0; k < numberOfSensors; k++) {
//
//				System.out.println("   Sensor");
//				getSpecialString(r);
//
//				System.out.println("   measure " + r.nextInt());
//				System.out.println();
//			}
//
//
//		}
//
//		long crc32SensorsData = r.nextLong();
//		System.out.println("  crc32SensorsData " + crc32SensorsData);
//
//		System.out.println();
//
//	}
//	System.out.println();
//
//
//
//
//
//



//	Reader2 r = new Reader2(content);
//
//	parseLoadBatch(r);
//	r.close();
}
