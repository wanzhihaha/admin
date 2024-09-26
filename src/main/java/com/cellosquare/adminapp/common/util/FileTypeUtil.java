package main.java.com.cellosquare.adminapp.common.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class FileTypeUtil {
    public final static Map<String,String> FILE_TYPE_MAP = new HashMap<>();

    static{
        getAllFileType();
    }

    private static void getAllFileType(){
        FILE_TYPE_MAP.put("jpg","FFD8FF");
        FILE_TYPE_MAP.put("png","89504E47");
        FILE_TYPE_MAP.put("gif","47494638");
        FILE_TYPE_MAP.put("bmp","424D");
    }

    public static boolean getImage(InputStream is){
        boolean isImageFile = false;
        byte[] b = new byte[50];
        try {
            int len = is.read(b);
            if (len != -1) {
                isImageFile = getFileTypeByStream(b);
            }
            is.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return isImageFile;
    }

    public static boolean getFileTypeByStream(byte[] b){

        String fileTypeHex = String.valueOf(getFileHexString(b));
        Iterator<Map.Entry<String,String>> entryItrator = FILE_TYPE_MAP.entrySet().iterator();

        while (entryItrator.hasNext()){
            Map.Entry<String,String> entry = entryItrator.next();
            String fileTypeHexValue = entry.getValue();
            if (fileTypeHex.toUpperCase().startsWith(fileTypeHexValue)) {
                System.out.println(entry.getKey());
                return true;
            }
        }
        return false;
    }

    public static String getFileHexString(byte[] b){
        StringBuilder sb = new StringBuilder();
        if (b == null || b.length <= 0){
            return null;
        }
        for (int i=0; i<b.length; i++){
            int v = b[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length()<2){
                sb.append(0);
            }
            sb.append(hv);
        }
        return sb.toString();
    }
}
