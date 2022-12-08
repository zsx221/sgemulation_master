import Utils.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Objects;

/**
 * 测试文件下载、上传、解析
 * @auther Yoko
 */
@RestController
@Slf4j
@RequestMapping("/file")
public class FileTestController {
    /**
     * @description 文件上传，入参可以根据具体业务进行添加
     */
    @RequestMapping(value = "/upLoadFile", method = RequestMethod.POST)
    public void upLoadFile(@RequestBody MultipartFile file) {
        log.info("测试MultipartFile实现文件上传");
        // 获取文件的完整名称，文件名+后缀名
        System.out.println(file.getOriginalFilename());
        // 文件传参的参数名称
        System.out.println(file.getName());
        // 文件大小，单位：字节
        System.out.println(file.getSize());
        // 获取文件类型，并非文件后缀名
        System.out.println(file.getContentType());
        try {
            // MultipartFile 转 File
            File resultFile = FileUtil.MultipartFileToFile(file);
            System.out.println(resultFile.getName());

            // File 转 MultipartFile
            MultipartFile resultMultipartFile = FileUtil.FileToMultipartFile(resultFile);
            System.out.println(resultMultipartFile.getSize());

        } catch (IOException e) {
            log.info("文件转换异常");
        }

    }
    /**
     * @description 文件下载，入参可以根据具体业务进行添加，比如下载具体编码的文件
     */
    public void downLoadFile() throws IOException {
        log.info("测试文件下载至浏览器默认地址");
        File file = new File("测试文件.xlsx");
        FileSystemResource fileSource = new FileSystemResource(file);
        FileUtil.downLoadFile(fileSource.getInputStream(), URLEncoder.encode(Objects.requireNonNull(fileSource.getFilename()), "UTF-8"));
    }

}
