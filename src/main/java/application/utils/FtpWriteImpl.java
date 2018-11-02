package application.utils;

import application.domain.Album;
import lombok.extern.log4j.Log4j;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

import static application.consts.Consts.PUBLIC_HTML;

@Service
@Log4j
public class FtpWriteImpl implements FtpWrite {
    @Value("${remote-connection-url}")
    private String remoteConnectionUrl;
    @Value("${remote-connection-username}")
    private String remoteConnectionUsername;
    @Value("${remote-connection-password}")
    private String remoteConnectionPassword;

    @Override
    public boolean writeInHost(Album albumGuid, String fileName, InputStream inputStream) {
        FTPClient ftpClient = new FTPClient();
        String fileInHost = PUBLIC_HTML + albumGuid.getName() + "/" + fileName;
        try {
            ftpClient.connect(remoteConnectionUrl);
            ftpClient.login(remoteConnectionUsername, remoteConnectionPassword);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            if (!ftpClient.changeWorkingDirectory(PUBLIC_HTML + albumGuid.getName())) {
                ftpClient.makeDirectory(PUBLIC_HTML + albumGuid.getName());
            }
            ftpClient.storeFile(fileInHost, inputStream);
            ftpClient.logout();
            ftpClient.disconnect();
            return true;
        } catch (IOException e) {
            log.error("Can't write file " + fileName, e);
        }
        return false;
    }

    @Override
    public boolean createDIR(Album albumGuid) {
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect(remoteConnectionUrl);
            ftpClient.login(remoteConnectionUsername, remoteConnectionPassword);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            if (!ftpClient.changeWorkingDirectory("/public_html/" + albumGuid.getName())) {
                ftpClient.makeDirectory("/public_html/" + albumGuid.getName());
            }
            ftpClient.logout();
            ftpClient.disconnect();
            return true;
        } catch (IOException e) {
            log.error("Can't crete DIR " + albumGuid.getName(), e);
        }
        return false;
    }
}
