package ua.com.vovacoffee.service;

import org.springframework.web.multipart.MultipartFile;
import ua.com.vovacoffee.model.Photo;

public interface PhotoService extends MainService<Photo> {

    Photo get(String title);

    void remove(String title);

    void saveFile(MultipartFile photo);

    void deleteFile(String url);
}
