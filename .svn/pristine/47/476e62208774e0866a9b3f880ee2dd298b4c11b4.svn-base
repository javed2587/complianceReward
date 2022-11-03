package com.ssa.cms.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

public class FileUtil {

    public static File[] readIRFiles(String filePath) {
        File files[] = null;
        File file = new File(filePath);
        FilenameFilter filter = null;// = new PMSAurgusFileFilter();
        files = file.listFiles(filter);
        return files;
    }

    public static File[] readDRFiles(String filePath) {
        File files[] = null;
        File file = new File(filePath);
        FilenameFilter filter = null;// = new PMSAurgusFileFilter();
        files = file.listFiles(filter);
        return files;
    }

    public static boolean moveFile(File src, File dst) throws IOException {
        InputStream in = new FileInputStream(src);
        OutputStream out = new FileOutputStream(dst);
        byte[] buf = new byte[1024];
        int len;
        while ((len = in.read(buf)) > 0) {
            out.write(buf, 0, len);
        }
        in.close();
        out.close();

        return true;
    }

    public static String determineImageFormat(byte[] imageBytes) throws IOException {
        final ByteArrayInputStream bStream = new ByteArrayInputStream(imageBytes);
        final ImageInputStream imgStream = ImageIO.createImageInputStream(bStream);
        final Iterator<ImageReader> iter = ImageIO.getImageReaders(imgStream);
        final ImageReader imgReader = iter.next();
        return imgReader.getFormatName();
    }
}
