package sample;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Controller {

    public Controller(){
//        TargetDataLine line;
//        DataLine.Info info = new DataLine.Info(TargetDataLine.class, format); // format is an AudioFormat object
//        if (!AudioSystem.isLineSupported(info)) {
//            // Handle the error.
//        }
//        // Obtain and open the line.
//        try {
//            line = (TargetDataLine) AudioSystem.getLine(info);
//            line.open(format);
//        } catch (LineUnavailableException ex) {
//            // Handle the error.
//            //...
//        }



        final JavaSoundRecorder recorder = new JavaSoundRecorder();

        // creates a new thread that waits for a specified
        // of time before stopping
        Thread stopper = new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(JavaSoundRecorder.RECORD_TIME);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                recorder.finish();
            }
        });

        stopper.start();

        // start recording
        recorder.start();
    }


}
