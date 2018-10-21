package application.utils;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.io.InputStream;

public class FtpWrite {
    @Value("${remote-connection-url}")
    private static String remoteConnectionUrl;
    @Value("${remote-connection-username}")
    private static String remoteConnectionUsername;
    @Value("${remote-connection-password}")
    private static String remoteConnectionPassword;

    public static boolean writeInHost(String albumGuid, String fileName, InputStream inputStream) {
        FTPClient ftpClient = new FTPClient();
        String fileInHost = "/public_html/" + albumGuid + "/" + fileName;
        try {
            ftpClient.connect(remoteConnectionUrl);
            ftpClient.login(remoteConnectionUsername, remoteConnectionPassword);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            if (!ftpClient.changeWorkingDirectory("/public_html/" + albumGuid)) {
                ftpClient.makeDirectory("/public_html/" + albumGuid);
            }
            ftpClient.storeFile(fileInHost, inputStream);
            ftpClient.logout();
            ftpClient.disconnect();
            return true;
        } catch (IOException e) {
            Logger.getLogger(FtpWrite.class).error("Can't write file " + fileName, e);
        }
        return false;
    }
}
