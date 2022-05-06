package main;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DataProcessing {
    byte[] voiceRawData;
    boolean locked = false;
    List<Double> result;

    public boolean receiveRawData(byte[] rawData){
        if(locked) return false;
        rawDataProcessing();
        return true;
    }

    public void rawDataProcessing(){
        File voiceFile = new File("voice.mp4");
        try {
            FileInputStream fis = new FileInputStream(voiceFile);
            ByteArrayOutputStream baos = new ByteArrayOutputStream(1024);
            baos.writeBytes(voiceRawData);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        this.result = ScriptRunner.runScript((InputStream stream)->{
            var br = new BufferedReader(new InputStreamReader(stream));
            try {
                return Arrays.stream(br.readLine().split(" ")).map((s)->Double.valueOf(s)).collect(Collectors.toList());
            } catch (IOException e) { }
            return new ArrayList<>();
        }, "raw_data_processing.py", voiceFile.getAbsolutePath());
        locked = false;
    }

    public List<Double> returnProcessedData(){
        return result;
    }

    public boolean status(){
        return !locked;
    }
}