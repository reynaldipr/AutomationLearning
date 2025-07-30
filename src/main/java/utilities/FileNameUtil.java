package utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FileNameUtil {

    public static String generateFileName(String methodName) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String timestamp = sdf.format(new Date());
        return "test-output/" + methodName + "_" + timestamp + ".mp4";
    }
}
