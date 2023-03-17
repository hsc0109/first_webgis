package cn.yls.metaluccs.server.controller;

import cn.yls.metaluccs.server.pojo.entity.DataSet;
import cn.yls.metaluccs.server.pojo.param.AreaSimpleInfo;
import cn.yls.metaluccs.server.service.DataSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;

@RestController
@RequestMapping("/dataset")
public class DataSetController {
    @Autowired
    private DataSetService dataSetServiceImpl;

    @PostMapping("/download/zip")
    public void downloadDatasetZip(@RequestBody AreaSimpleInfo areaSimpleInfo, HttpServletResponse response) throws Exception {
        String path = dataSetServiceImpl.downloadDatasetZip(areaSimpleInfo);
        //String path = "/home/hanl/Data/yls/lulc2020/zip/1.png";
//        InputStream inputStream = new FileInputStream(path);// 文件的存放路径
//        String filename = new File(path).getName();
//        response.reset();
//        response.setContentType("application/octet-stream;charset=UTF-8");
//        response.setHeader("Pragma", "no-cache");
//        response.setHeader("Cache-Control", "no-cache");
//        response.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(filename, "UTF-8"));
//        ServletOutputStream outputStream = response.getOutputStream();
//        byte[] b = new byte[1024];
//        int len;
//        //从输入流中读取一定数量的字节，并将其存储在缓冲区字节数组中，读到末尾返回-1
//        while ((len = inputStream.read(b)) > 0) {
//            outputStream.write(b, 0, len);
//        }
//        inputStream.close();

        String filename = new File(path).getName();
        response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=" + filename);
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(new File(path)));
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("success");

//        if (path != null) {
//            //1、获取要下载文件的绝对路径
//            //获取项目相对路径,上传文件到项目资源目录的upload下
//            File directory = new File(path);
//            String realPath = null;
//            FileInputStream fis = null;
//            ServletOutputStream sot = null;
//            String filename = directory.getName();
//            try {
//                realPath = directory.getCanonicalPath() + File.separator + filename;
//                System.out.println("下载的文件是：" + realPath);
//                //2、获取要下载文件的文件名
//                String realName = filename;
//                //3、让浏览器能够支持我们下载的文件[如果文件名是中文就需要转码]
//                response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(realName, "UTF-8"));
//                //4、获取下载文件的输入流
//                fis = new FileInputStream(realPath);
//                //5、创建缓冲区
//                int len = 0;
//                byte[] bytes = new byte[1024];
//                //6、创建输出流
//                sot = response.getOutputStream();
//                //7、写出文件
//                while ((len = fis.read(bytes)) != -1) {
//                    sot.write(bytes, 0, len);
//                    sot.flush();
//                }
//                return "upload";
//            } catch (IOException e) {
//                e.printStackTrace();
//            }finally {
//                if (fis != null) {
//                    try {
//                        fis.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//                if (sot != null) {
//                    try {
//                        sot.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }
//
//        return "upload";

    }
}
