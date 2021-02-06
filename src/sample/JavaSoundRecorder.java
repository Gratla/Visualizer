package sample;

import javax.sound.sampled.*;
import java.io.*;
import java.util.Arrays;

/**
 * A sample program is to demonstrate how to record sound in Java
 * author: www.codejava.net
 */
public class JavaSoundRecorder {
    // record duration, in milliseconds
    public static final long RECORD_TIME = 5000;  // 1 minute

    // path of the wav file
    File wavFile = new File("D:/IDEAProjects/Visualizer/out/SystemSound.wav");

    // format of audio file
    AudioFileFormat.Type fileType = AudioFileFormat.Type.WAVE;

    // the line from which audio data is captured
    TargetDataLine line;

    /**
     * Defines an audio format
     */
    AudioFormat getAudioFormat() {
        float sampleRate = 44100;
        int sampleSizeInBits = 16;
        int channels = 2;
        boolean signed = true;
        boolean bigEndian = true;
        AudioFormat format = new AudioFormat(sampleRate, sampleSizeInBits,
                channels, signed, bigEndian);
        return format;
    }

    /**
     * Captures the sound and record into a WAV file
     */
    void start() {
        try {
            AudioFormat format = getAudioFormat();
            DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
            Mixer mixer = AudioSystem.getMixer(Arrays.stream(AudioSystem.getMixerInfo()).findFirst().get());

            // checks if system supports the data line
//            if (!AudioSystem.isLineSupported(Port.Info.HEADPHONE)) {
//                System.out.println("Line not supported");
//                System.exit(0);
//            }

//            Port portLine = (Port) AudioSystem.getLine(Port.Info.HEADPHONE);
//            line = (TargetDataLine) Arrays.stream(mixer.getTargetLines()).findFirst().get();
            AudioSystem.get
            line = (TargetDataLine) AudioSystem.getLine(info);
            line.open(format);
            line.start();   // start capturing

            System.out.println("Start capturing...");

            AudioInputStream ais = new AudioInputStream(line);

            System.out.println("Start recording...");

            // start recording
            AudioSystem.write(ais, fileType, wavFile);

        } catch (LineUnavailableException ex) {
            ex.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Closes the target data line to finish capturing and recording
     */
    void finish() {
        line.stop();
        line.close();
        System.out.println("Finished");
    }
}