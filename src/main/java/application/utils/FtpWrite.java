package application.utils;

import java.io.InputStream;

public interface FtpWrite {
    public boolean writeInHost(String albumGuid, String fileName, InputStream inputStream);
}
