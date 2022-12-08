package test2;

import com.macrochina.net.controller.Pacs008Controller;
import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.ClassUtils;

import java.io.*;
import java.nio.file.Files;

public class UpdateTest {
    private static Log logger = LogFactory.getLog(UpdateTest.class);
    @Test
    public  void Upload() throws Exception {
        File beforefile = new File("C:/Users/13658/Desktop/autoInsert.sql");

        //这是你要保存之后的文件，是自定义的，本身不存在

        File afterfile = new File("src/main/resources/UploadsFile/autoInsert.sql");

//定义文件输入流，用来读取beforefile文件
        InputStream in = new FileInputStream(beforefile);

//定义文件输出流，用来把信息写入afterfile文件中
        OutputStream out = new FileOutputStream(afterfile);

//文件缓存区
        byte[] bytes= new byte[1024*128];
//将文件流信息读取文件缓存区，如果读取结果不为-1就代表文件没有读取完毕，反之已经读取完毕
        while(in.read( bytes )!=-1) {
//将缓存区中的内容写到afterfile文件中
            out.write(bytes);

        }
        out.flush();
        out.close();
        logger.info("上传成功");
    }
    @Test
    public  void copyFileUsingJava7Files()//目标路径不允许存在此文件
            throws IOException {
        File source = new File("C:/UploadsFile/autoInsert.sql");
        //这是你要保存之后的文件，是自定义的，本身不存在
        File dest = new File("src/main/resources/UploadsFile/autoInsert.sql");
        Files.copy(source.toPath(), dest.toPath());
    }

//    public static void copyFileUsingApacheCommonsIO()//Apache Commons IO提供拷贝文件方法在其FileUtils类,可用于复制一个文件到另一个地方
//            throws IOException {
//        logger.info("先复制");
//        File directory = new File("src/main/resources/autoInsert.sql");
//        String reportPath = directory.getCanonicalPath();
//        File source = new File("C:\\UploadsFile\\autoInsert.sql");
//        //这是你要保存之后的文件，是自定义的，本身不存在
//        File dest = new File(reportPath);
//        FileUtils.copyFile(source, dest);
//    }

//    public static void main(String[] args) {
//        try {
//            copyFileUsingApacheCommonsIO();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//    @Test
//    public void copyFileUsingApacheCommonsIO()//Apache Commons IO提供拷贝文件方法在其FileUtils类,可用于复制一个文件到另一个地方
//            throws IOException {
//        String txt = "";
//        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//        //Resource[] resources = resolver.getResources("/data/autoInsert.sql");
//        Resource[] resources = resolver.getResources("C:\\UploadsFile\\autoInsert.sql");
//        Resource resource = resources[0];
////获得文件流，因为在jar文件中，不能直接通过文件资源路径拿到文件，但是可以在jar包中拿到文件流
//        InputStream stream = resource.getInputStream();
//        StringBuilder buffer = new StringBuilder();
//        byte[] bytes = new byte[1024];
//        try {
//            for (int n; (n = stream.read(bytes)) != -1; ) {
//                buffer.append(new String(bytes, 0, n));
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        txt = buffer.toString();
//        logger.info(txt);
//    }
}
