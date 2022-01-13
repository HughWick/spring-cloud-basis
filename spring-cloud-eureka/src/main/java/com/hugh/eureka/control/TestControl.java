//package com.hugh.eureka.control;
//
//import com.google.common.base.Stopwatch;
//import com.hugh.eureka.config.CamStatusSdk;
//import com.hugh.eureka.config.CamStatusSdkSo;
//import com.sun.jna.Pointer;
//import lombok.extern.slf4j.Slf4j;
//import org.opencv.core.Mat;
//import org.opencv.core.Size;
//import org.opencv.imgcodecs.Imgcodecs;
//import org.opencv.imgproc.Imgproc;
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.concurrent.TimeUnit;
//
///**
// * @author AS
// * @date 2020/10/16 17:18
// */
//@Slf4j
//@RestController
//public class TestControl {
//    CamStatusSdkSo INSTANCE_LINUX = CamStatusSdkSo.INSTANCE;
//
//    @RequestMapping(value = "/test01", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public void test01(String path, @RequestParam(defaultValue = "-1") int checkItemOn) {
//        linuxTestAddr(path, checkItemOn);
//        System.out.println("--test01--检测方法结束-----");
//    }
//
//    //测试传入图片内存地址方法
//    private void linuxTestAddr(String path, int checkItemOn) {
//        if (path == null || "".equals(path)) {
//            path = "/home/test01.jpg";
//        }
//        if (checkItemOn == -1) {
//            checkItemOn = 4095;
//        }
//        Mat source = Imgcodecs.imread(path);//读取文件 、默认B、G、R顺序
//        log.info("读取图片成功,图片地址：" + path);
//        Mat dst = new Mat();
//        Stopwatch stopwatch = Stopwatch.createStarted();
//        // 2020-09-28 暂时 图片分辨率为：2560*1440时宽度除以3时DLL库运算无法接结果，故而这里进行强制转换为1250宽度
//        double width = source.cols() > 1920 ? 1250 : (source.cols() / 3);
//        Imgproc.resize(source, dst, new Size(width, source.rows()), 0, 0, Imgproc.INTER_AREA);//将图片转换成宽
//        log.info("初始化CamStatusSdk");
//        Pointer handle = INSTANCE_LINUX.CamStateInit(CamStatusSdk.setParams(checkItemOn));//初始化
//        log.info("初始化CamStatusSdk结束,结果集：" + handle);
//        log.info("开始调用CamStateProcess方法");
//        int i3 = INSTANCE_LINUX.CamStateProcess(handle, dst.dataAddr(), dst.rows(), dst.cols());  // 分析
//        log.info("调用CamStateProcess方法结束");
//        INSTANCE_LINUX.CamStateRelease(handle);//释放资源
//        long milli = stopwatch.elapsed(TimeUnit.MILLISECONDS);//耗时、毫秒
//        System.out.println("耗时：" + milli + "ms;图片路径：" + path + ";-linuxTestPath检测结果-->>" + i3);
//    }
//
//    @RequestMapping(value = "/test02", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public void test02(String path, @RequestParam(defaultValue = "-1") int checkItemOn) {
//        linuxTestPath(path, checkItemOn);
//        System.out.println("-test02---检测方法结束-----");
//    }
//
//
//    //linux测试图片路径传参方式
//    private void linuxTestPath(String path, int checkItemOn) {
//        Stopwatch stopwatch = Stopwatch.createStarted();
////        String SAVE_PATH = "/home/DaHua/capture/2C05938PAA00665/2020/10/13/0abca571d0c1444d8a0f8eaa24cf77c7.jpg";
////        String SAVE_PATH = "/home/test01.jpg";
//        if (path == null || "".equals(path)) {
//            path = "/home/test01.jpg";
//        }
//        if (checkItemOn == -1) {
//            checkItemOn = 4095;
//        }
//        log.info("初始化CamStatusSdk");
//        Pointer handle = INSTANCE_LINUX.CamStateInit(CamStatusSdk.setParams(checkItemOn));//初始化
//        log.info("初始化CamStatusSdk结束,结果集：" + handle);
//        log.info("开始调用CamStateProcess2方法");
//        int i3 = INSTANCE_LINUX.CamStateProcess2(handle, path);  // 分析
//        log.info("调用CamStateProcess2方法结束");
//        INSTANCE_LINUX.CamStateRelease(handle);//释放资源
//        long milli = stopwatch.elapsed(TimeUnit.MILLISECONDS);//耗时、毫秒
//        System.out.println("耗时：" + milli + "ms;图片路径：" + path + ";-linuxTestPath检测结果-->>" + i3);
//    }
//}
