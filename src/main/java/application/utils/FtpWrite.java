package application.utils;

import application.domain.Album;

import java.io.InputStream;

public interface FtpWrite {
    public boolean writeInHost(Album albumGuid, String fileName, InputStream inputStream);

    public boolean createDIR(Album albumGuid);
}
