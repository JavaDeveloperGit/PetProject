package ua.com.vovacoffee.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ua.com.vovacoffee.dao.PhotoDAO;
import ua.com.vovacoffee.exception.BadRequestException;
import ua.com.vovacoffee.exception.WrongInformationException;
import ua.com.vovacoffee.model.Photo;
import ua.com.vovacoffee.service.PhotoService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@Service
public class PhotoServiceImpl extends MainServiceImpl<Photo> implements PhotoService {

    private static final String PATH = "c:/Server/apache-tomcat-8.0.33/webapps/ROOT/resources/img/";

    private PhotoDAO dao;

    @Autowired
    public PhotoServiceImpl(PhotoDAO dao) {
        super(dao);
        this.dao = dao;
    }

    @Override
    @Transactional(readOnly = true)
    public Photo get(String title) throws WrongInformationException, BadRequestException {
        if (title == null || title.isEmpty()) {
            throw new WrongInformationException("No photo title!");
        }

        Photo photo;

        photo = dao.get(title);
        if (photo == null) {
            throw new BadRequestException("Can't find photo by title " + title + "!");
        }

        return photo;
    }

    @Override
    @Transactional
    public void remove(String title) throws WrongInformationException {
        if (title == null || title.isEmpty()) {
            throw new WrongInformationException("No photo title!");
        }
        dao.remove(title);
    }

    @Override
    @Transactional
    public void saveFile(MultipartFile photo) {
        if (photo != null && !photo.isEmpty()) {
            try (OutputStream stream = new FileOutputStream(PATH + photo.getOriginalFilename())) {
                stream.write(photo.getBytes());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    @Transactional
    public void deleteFile(String url) {
        if (url != null && !url.isEmpty()) {
            File file = new File(PATH + url);
            if (file.exists() && file.isFile()) {
                file.delete();
            }
        }
    }
}
