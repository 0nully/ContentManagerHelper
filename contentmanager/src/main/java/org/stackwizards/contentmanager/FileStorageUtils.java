package org.stackwizards.contentmanager;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileStorageUtils {

    /***
     * Save data in external folder.
     * @param context - application context
     * @param fullFileName - eg: folder\\inner\\file.txt or file.json
     * @param data - "Some text that needs to be save somewhere"
     */
    public static void SaveDataToExternalDirectory(Context context, String fullFileName, String data) {
        FileParts fileParts = splitFullFileName(fullFileName);

        try {
            File myExternalFile = new File(context.getExternalFilesDir(fileParts.path), fileParts.name);
            FileOutputStream fos = new FileOutputStream(myExternalFile);
            fos.write(data.getBytes());
            fos.close();
        } catch (IOException e) {
            Log.d("HELPER_UTILS", e.getMessage());
        }
    }

    /**
     * Return string data store in file
     * @param context - application context
     * @param fullFileName - eg: folder\\inner\\file.txt or file.json
     * @return String data contain file
     */
    public static String GetStoredData(Context context, String fullFileName) {
        FileParts fileParts = splitFullFileName(fullFileName);
        String data = "";
        try {
            File tmpFile = new File(context.getExternalFilesDir(fileParts.path), fileParts.name);
            FileInputStream fis = new FileInputStream(tmpFile);
            DataInputStream in = new DataInputStream(fis);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            while ((strLine = br.readLine()) != null) {
                data += strLine;
            }

            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }

    private static FileParts splitFullFileName(String fullFileName) {
        String filename = fullFileName;
        String path = "";
        if (fullFileName.contains("\\")) {
            int i = fullFileName.lastIndexOf("\\");
            String[] fileParts = {fullFileName.substring(0, i), fullFileName.substring(i)};
            path = fileParts[0];
            filename = fileParts[1].replace("\\", "");
        }
        return new FileParts(path,filename);
    }

    private static class FileParts {
        String name;
        String path;

        public FileParts(String path, String name) {
            this.name = name;
            this.path = path;
        }
    }
}
