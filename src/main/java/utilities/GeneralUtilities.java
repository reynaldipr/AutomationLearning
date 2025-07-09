package utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GeneralUtilities {
    public static String generateTimeNow(int format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();

        if (format == 1) {
            simpleDateFormat.applyPattern("yyyy.MM.dd.HH.mm.ss");
        } else if (format == 2) {
            simpleDateFormat.applyPattern("HH.mm.ss");
        } else if (format == 3) {
            simpleDateFormat.applyPattern("ddMMyyyy");
        } else if (format == 4) {
            simpleDateFormat.applyPattern("MM");
        } else if (format == 5) {
            simpleDateFormat.applyPattern("yyyy-MM-dd");
        } else if (format == 6) {
            simpleDateFormat.applyPattern("d");
        }

        return simpleDateFormat.format(new Date());
    }

    public static boolean validateArraysContainsString(String[] array, String expectedContainsString) {
        boolean statusArray = true;
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]+" = "+expectedContainsString);
            if (!array[i].contains(expectedContainsString)) {
                statusArray = false;
                break;
            }
        }

        return statusArray;
    }

    public static boolean validateArrayAllMatchString(String[] array, String expectedContainsString) {
        for (int i = 0; i < array.length; i++) {
            if (!array[i].equalsIgnoreCase(expectedContainsString)) {
                return false;
            }
        }
        return true;
    }

    public static boolean validateArrayAllMatchAnyOf(String[] array, String... allowedValues) {
        for (String item : array) {
            if (item == null) continue; // Optional: allow nulls
            boolean match = false;
            for (String allowed : allowedValues) {
                if (item.equalsIgnoreCase(allowed)) {
                    match = true;
                    break;
                }
            }
            if (!match) {
                return false;
            }
        }
        return true;
    }
}
