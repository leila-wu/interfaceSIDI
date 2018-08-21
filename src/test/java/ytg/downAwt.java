package ytg;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class downAwt {
    public static String endStr = "http://123.207.17.180:14005/nImgServlet?mobileKey=1534306707531&r=0.6517037763880074";

    public static void download(String urlString, String filename,String savePath) throws Exception {
        // 构造URL
        URL url = new URL(urlString);
        // 打开连接
        URLConnection con = url.openConnection();
        //设置请求超时为5s
        con.setConnectTimeout(5*1000);
        // 输入流
        InputStream is = con.getInputStream();

        // 1K的数据缓冲
        byte[] bs = new byte[1024];
        // 读取到的数据长度
        int len;
        // 输出的文件流
        File sf=new File(savePath);
        if(!sf.exists()){
            sf.mkdirs();
        }
        OutputStream os = new FileOutputStream(sf.getPath()+System.getProperty("file.separator") + filename);
        // 开始读取
        while ((len = is.read(bs)) != -1) {
            os.write(bs, 0, len);
        }
        // 完毕，关闭所有链接
        os.close();
        is.close();
    }

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        System.out.println("java.version "+System.getProperty("java.version"));
        System.out.println("java.vendor "+System.getProperty("java.vendor"));
        System.out.println("java.home "+ System.getProperty("java.home"));
        System.out.println("java.library.path "+ System.getProperty("java.library.path"));
        System.out.println("java.class.path "+ System.getProperty("java.class.path"));
        System.out.println("os.name  "+ System.getProperty("os.name"));
        System.out.println("user.dir  "+ System.getProperty("user.dir"));
        System.out.println("user.name  "+ System.getProperty("user.name"));
        System.out.println("user.home  "+ System.getProperty("user.home"));
        System.out.println("path.separator  "+ System.getProperty("path.separator"));
        System.out.println("fiie.separator  "+ System.getProperty("file.separator"));
        System.out.println("line.separator  "+ System.getProperty("line.separator"));
        System.out.println(Class.class.getClass().getResource("/").getPath());

        File directory = new File("");
        try{
            System.out.println(directory.getCanonicalPath());
            System.out.println(directory.getAbsoluteFile());
        }catch(Exception e){

        }
        String path = System.getProperty("user.dir") + System.getProperty("file.separator") + "src/test/java/ytg/verifyCode";
        // TODO Auto-generated method stub
        System.out.println(path);
//        download(endStr, "test.jpg",path);
    }
}

