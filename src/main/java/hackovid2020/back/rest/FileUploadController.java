package hackovid2020.back.rest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

//@Controller
public class FileUploadController {
    public static final String uploadingDir = System.getProperty("user.dir") + "/uploadingDir/";

    static {
        boolean mkdirs = (new File(uploadingDir)).mkdirs();
        assert mkdirs; // Y a tomar por culo
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String uploadingPost(@RequestParam("uploadingFiles") MultipartFile uploadingFile) throws IOException {
        File file = new File(uploadingDir + uploadingFile.getOriginalFilename());
        uploadingFile.transferTo(file);
        // TODO Add to database
        // TODO Get DTO of resulting file and send back

        return "fileObject";
    }
}