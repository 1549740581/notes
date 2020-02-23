package nwpu.sherman.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传的Controller
 *
 * @author sherman
 */
@Controller
public class FileUploadController {
    @RequestMapping(value = "/file_upload", method = RequestMethod.POST)
    public String fileUpload(@RequestParam("file") MultipartFile mf, @RequestParam("desc") String desc) {
        System.out.println("desc: " + desc);
        System.out.println("originFileName：" + mf.getOriginalFilename());
        return "success";
    }
}
