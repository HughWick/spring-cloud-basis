package com.hugh.minio.control;

import com.alibaba.fastjson.JSON;
import com.github.hugh.bean.dto.ResultDTO;
import com.hugh.minio.model.Res;
import io.minio.MinioClient;
import io.minio.PutObjectOptions;
import io.minio.Result;
import io.minio.messages.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.*;

/**
 * minio control
 */
@Slf4j
@RestController
public class MinioController {

    @Resource
    private MinioClient minioClient;

    /**
     * minio 对应的库名称
     */
    private static final String MINIO_BUCKET = "data1";

    /**
     * 查询对应库下所有文件集合
     *
     * @param map
     * @return
     * @throws Exception
     */
    @GetMapping("/list")
    public List<Object> list(ModelMap map) throws Exception {
        Iterable<Result<Item>> myObjects = minioClient.listObjects(MINIO_BUCKET);
        Iterator<Result<Item>> iterator = myObjects.iterator();
        List<Object> items = new ArrayList<>();
        String format = "{'fileName':'%s','fileSize':'%s'}";
        while (iterator.hasNext()) {
            Item item = iterator.next().get();
            items.add(JSON.parse(String.format(format, item.objectName(), formatFileSize(item.size()))));
        }
        return items;
    }

    /**
     * 上传 文件
     *
     * @param file
     * @return ResultDTO
     */
    @PostMapping("/upload")
    public ResultDTO upload(@RequestParam(name = "file", required = false) MultipartFile[] file) {
        if (file == null || file.length == 0) {
            return new ResultDTO<>("0001", "上传文件不能为空");
        }
        List<String> orgfileNameList = new ArrayList<>(file.length);
        for (MultipartFile multipartFile : file) {
            String orgfileName = multipartFile.getOriginalFilename();
            orgfileNameList.add(orgfileName);
            try {
                InputStream in = multipartFile.getInputStream();
                minioClient.putObject(MINIO_BUCKET, orgfileName, in, new PutObjectOptions(in.available(), -1));
                in.close();
            } catch (Exception e) {
                log.error(e.getMessage());
                return new ResultDTO<>("0001", "上传失败");
            }
        }
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("bucketName", MINIO_BUCKET);
        data.put("fileName", orgfileNameList);
        return new ResultDTO<>("0000", "上传成功", data);
    }

//    @RequestMapping("/download/{fileName}")
//    public void download(HttpServletResponse response, @PathVariable("fileName") String fileName) {
//        InputStream in = null;
//        try {
//            ObjectStat stat = minioClient.statObject(MINIO_BUCKET, fileName);
//            response.setContentType(stat.contentType());
//            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, StandardCharsets.UTF_8));
//            in = minioClient.getObject(MINIO_BUCKET, fileName);
//            IOUtils.copy(in, response.getOutputStream());
//        } catch (Exception e) {
//            log.error(e.getMessage());
//        } finally {
//            if (in != null) {
//                try {
//                    in.close();
//                } catch (IOException e) {
//                    log.error(e.getMessage());
//                }
//            }
//        }
//    }

    @DeleteMapping("/delete/{fileName}")
    public Res delete(@PathVariable("fileName") String fileName) {
        Res res = new Res();
        res.setCode(200);
        try {
            minioClient.removeObject(MINIO_BUCKET, fileName);
        } catch (Exception e) {
            res.setCode(500);
            log.error(e.getMessage());
        }
        return res;
    }

    /**
     * 文件大小计算
     * @param fileS
     * @return
     */
    private static String formatFileSize(long fileS) {
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        String wrongSize = "0B";
        if (fileS == 0) {
            return wrongSize;
        }
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + " B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + " KB";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + " MB";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + " GB";
        }
        return fileSizeString;
    }
}
