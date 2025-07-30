package utilities;

import java.io.*;

public class FFmpegScreenRecorder {
    private Process ffmpegProcess;

    public void startRecording(String outputFilePath) throws IOException {
        // Buat folder jika belum ada
        File outputFile = new File(outputFilePath);
        File parentDir = outputFile.getParentFile();
        if (!parentDir.exists()) {
            parentDir.mkdirs();
            System.out.println("Membuat folder: " + parentDir.getAbsolutePath());
        }

        String[] command = {
                "ffmpeg",
                "-y",
                "-f", "avfoundation",   // Mac screen capture
                "-framerate", "15",
                "-i", "1:none",
                "-vcodec", "libx264",
                "-preset", "ultrafast",
                outputFilePath
        };

        ProcessBuilder pb = new ProcessBuilder(command);
        pb.redirectErrorStream(true);
        ffmpegProcess = pb.start();
        System.out.println("Recording started... Output: " + outputFilePath);

        // Print log ffmpeg agar bisa debug jika ada error
        InputStream inputStream = ffmpegProcess.getInputStream();
        new Thread(() -> {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println("[FFmpeg] " + line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void stopRecording() {
        if (ffmpegProcess != null) {
            try {
                // Kirim 'q' agar ffmpeg berhenti dengan aman (graceful stop)
                OutputStream os = ffmpegProcess.getOutputStream();
                os.write('q');
                os.flush();
                ffmpegProcess.waitFor();
                System.out.println("Recording stopped gracefully.");
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
