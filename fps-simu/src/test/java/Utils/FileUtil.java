package Utils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import java.io.File;
import java.io.IOException;

// 使用OpenCV实现目标
// 引入OpenCV
//import org.opencv.core.Core;
//import org.opencv.core.Mat;
//import org.opencv.imgcodecs.Imgcodecs;
import java.io.*;
import java.util.Objects;
/**
 * @auther Yoko
 */
public class FileUtil {

    /**
     * MultipartFile类型转File类型，文件名同被转换文件名称一致，如有需要可以拓展方法。
     */
    public static File MultipartFileToFile(MultipartFile multipartFile) throws IOException {
        if (multipartFile.isEmpty()) {
            return null;
        }
        // 获取InoutString
        InputStream inputStream = multipartFile.getInputStream();
        // 创建文件
        File toFile = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        // 写入文件
        OutputStream outputStream = new FileOutputStream(toFile);
        byte[] buffer = new byte[8192];
        int bytesRead = 0;
        while ((bytesRead = inputStream.read(buffer, 0, buffer.length)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }
        outputStream.close();
        inputStream.close();
        return toFile;
    }

    /**
     * File类型转MultipartFile类型，文件名同被转换文件名称一致，如有需要可以拓展方法。
     */
    public static MultipartFile FileToMultipartFile(File file) throws IOException {
        FileItemFactory factory = new DiskFileItemFactory(16, null);
        FileItem item = factory.createItem("textField", "text/plain", true, file.getName());
        int bytesRead = 0;
        byte[] buffer = new byte[8192];
        FileInputStream fis = new FileInputStream(file);
        OutputStream os = item.getOutputStream();
        while ((bytesRead = fis.read(buffer, 0, 8192)) != -1) {
            os.write(buffer, 0, bytesRead);
        }
        os.close();
        fis.close();
        return new CommonsMultipartFile(item);

    }

    /**
     * 将File文件转化为Base64字节码
     */
    public static String encodeBase64File(File file) throws IOException {
        FileInputStream inputFile = new FileInputStream(file);
        byte[] buffer = new byte[(int) file.length()];
        int read = inputFile.read(buffer);
        inputFile.close();
        return new BASE64Encoder().encode(buffer);
    }

    /**
     * 将base64字符保存文本文件
     */
    public static void decoderBase64File(String base64Code, String targetPath) throws IOException {
        byte[] buffer = new BASE64Decoder().decodeBuffer(base64Code);
        FileOutputStream out = new FileOutputStream(targetPath);
        out.write(buffer);
        out.close();
    }

    /**
     * 下载文件至浏览器默认位置
     */
    public static ResponseEntity<InputStreamResource> downLoadFile(InputStream in, String fileName) throws IOException {
        byte[] testBytes = new byte[in.available()];
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", fileName));
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentLength(testBytes.length)
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new InputStreamResource(in));
    }


    public static void main(String[] args) {
//        // 主函数
//
//            // 加载本地图片
//            Mat src = Imgcodecs.imread("test.jpg");
//            if (src.empty()) {
//                System.out.println("图片加载失败");
//                return;
//            }
//            // 对图片进行模糊处理
//            Mat dst = new Mat();
//            Imgproc.GaussianBlur(src, dst, new Size(3, 3), 0, 0);
//            // 查找图片中所有相似元素的位置和坐标范围
//            Mat result = new Mat();
//            Imgproc.findContours(dst, result, new Mat(), Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_NONE);
//            // 对所有元素进行标号
//            int index = 1;
//            for (int i = 0; i < result.rows(); i++) {
//                Imgproc.putText(dst, String.valueOf(index++), new Point(result.get(i, 0)), Core.FONT_HERSHEY_SIMPLEX, 0.5, new Scalar(255, 0, 0));
//            }
//            // 显示结果
//            Imgcodecs.imshow("Result", dst);
//            Imgcodecs.waitKey(0);

    }
}
