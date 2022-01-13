//package com.hugh.eureka.config;
//
//import com.sun.jna.Library;
//import com.sun.jna.Native;
//import com.sun.jna.Structure;
//
///**
// * 图片监测
// *
// * @author AS
// * @date 2020/8/19 9:30
// */
//
//public interface CamStatusSdk extends Library {
//
//    static void loadOpencv() {
//        String path;
//        if (isWindows()) {
//            path = "D:\\Works\\opencv\\opencv_java340-x64.dll";
//        } else {
//            path = "/home/LibCamStatus/libopencv_java344.so";
//        }
//        System.load(path);
//    }
//
//    public static boolean isWindows() {
//        String os = System.getProperty("os.name");
//        return os.toLowerCase().startsWith("win");
//    }
//
//    static String fileName() {
//        loadOpencv();
//        if (isWindows()) {
//            //        return "D:\\Works\\摄像机体检\\算法库应用Demo\\CS_CV\\bin\\Release\\CamState";
////        return "D:\\Works\\摄像机状态检测20200903\\算法库应用Demo\\CS_CV\\bin\\Release\\CamState";//2020-09-04 第二版
////        return "D:\\Works\\摄像机体检20200908\\算法库应用Demo\\CS_CV\\bin\\Release\\CamState";//2020-09-09 第三版
////        return "D:\\Tencent Files\\WeChat Files\\xiaozhi10201\\FileStorage\\File\\2020-09\\Release\\CamState";//2020-09-09 第三版
////        return "D:\\Works\\摄像机体检20200913\\算法库应用demo\\CS_CV\\bin\\Release\\CamState";//2020-09-13 第三版
////        return "D:\\Works\\摄像机状态检测20200917\\算法库应用demo\\CS_CV\\bin\\Release\\CamState";//2020-09-17 第四版
//            return "D:\\Works\\摄像机状态检测20200925\\CS_CV\\bin\\Release\\CamState";//2020-09-25
//        } else {
//            return "/home/LibCamStatus/capdetect.so";
//        }
//    }
//    CamStatusSdk INSTANCE = (CamStatusSdk) Native.loadLibrary(fileName(), CamStatusSdk.class);
//
//    int detect_color = 1;
//    int detect_brightness = 2;
//    int detect_hs = 4;
//    int detect_snow = 8;
//    int detect_lose = 16;
//    int detect_obscure = 32;
//    int detect_blocking = 64;
//
//    int detect_partialocclusion = 128;
//    int detect_totalocclusion = 256;
//
//    int detect_freeze = 512;
//    int detect_contrast = 1024;
//    int detect_turnround = 2048;
//
//
//
//
//    class runParamsCpp extends Structure {    //通道录像参数配置(9000扩展)
//        public int CheckItem_On; //检测项开关，按位表示，1为需要检测
//        public float ColorExcep_Cast;    //颜色异常，偏色的阈值，越小越严苛
//        public float ColorExcep_Da; //颜色异常，偏红色或者偏绿色的阈值
//        public float ColorExcep_Db;        //颜色异常，偏黄色或者偏蓝色的阈值
//
//        public float BrightExcep_Cast;    //亮度异常，亮度异常的阈值，越小越严苛
//        public float BrightExcep_Da;    //亮度异常，偏亮或者偏暗的阈值
//
//        public float HorizonStripe_BinaryTh;    //水平条纹，二值化参数，越大越宽泛
//        public int HorizonStripe_Min;      //水平条纹，最小条纹数目，越小越严苛
//        public float HorizonStripe_Expth1;    //水平条纹，查找参数，越大越严苛
//        public float HorizonStripe_Expth2;    //水平条纹，判定参数，越小越严苛
//
//        public int SnowFlake_NoiseTh;        //雪花，判定噪点的阈值，越小越严苛
//        public float SnowFlake_Expth;        //雪花，判定参数，越小越严苛
//
//        public int SignalLoss_Sample;    //信号丢失，采样大小，越大越精确，越耗时
//        public float SignalLoss_Expth;    //信号丢失，判断参数，越小越严苛
//
//        public float Obscure_Expth;        //虚焦，判断参数，越大越严苛
//
//        public float CamBlockEffect_Expth1;    //块效应，单个块的判断阈值，越小越严苛
//        public float CamBlockEffect_Expth2; //块效应，判断参数，越小越严苛
//
//        public float Occlusion_BoxSizeRate;            //半遮挡检测,选取盒子的大小,与图像大小相关
//        public float Occlusion_MinAreaRate;            //半遮挡检测,遮挡最小检测面积,与图像大小相关
//        public int Occlusion_Threshold;                //全遮挡检测,二值化阈值
//        public float Occlusion_Expth;                    //全遮挡检测,最终判断参数,越大越严苛
//
//        public float CamFreeze_Expth;        //冻结，判断参数，越小越严苛
//        public int CamContrast_Expth;        //低对比度，判断参数，越小越严苛
//        public int CamTurnRound_Expth;    //转向，判断参数，越大越严苛
//    }
//
//
//    public static runParamsCpp setParams() {
//        int i = detect_color | detect_brightness | detect_hs | detect_snow | detect_lose | detect_obscure
//                | detect_blocking
//                | detect_partialocclusion
//                | detect_totalocclusion
//                | detect_freeze
//                | detect_contrast | detect_turnround;
//        return setParams(i);
//    }
//
//    /**
//     * 初始化 图片校验参数
//     *
//     * @return
//     */
//    public static runParamsCpp setParams(int CheckItemOn) {
//        runParamsCpp params = new runParamsCpp();
//
//        System.out.println("==初始化CheckItem_On值==>>" + CheckItemOn);
//        params.CheckItem_On = CheckItemOn;
//
//        params.ColorExcep_Cast = 2.2f;  //颜色异常，偏色的阈值，越小越严苛
//        params.ColorExcep_Da = 0; //颜色异常，偏红色或者偏绿色的阈值
//        params.ColorExcep_Db = 0;  //颜色异常，偏黄色或者偏蓝色的阈值
//
//        params.BrightExcep_Cast = 1f;  //亮度异常，亮度异常的阈值，越小越严苛
//        params.BrightExcep_Da = 0; //亮度异常，偏亮或者偏暗的阈值
//
//        params.HorizonStripe_BinaryTh = 1.7f; //水平条纹，二值化参数，越大越宽泛
//        params.HorizonStripe_Min = 10;  //水平条纹，最小条纹数目，越小越严苛
//        params.HorizonStripe_Expth1 = 0.4f;  //水平条纹，查找参数，越大越严苛
//        params.HorizonStripe_Expth2 = 0.1f;  //水平条纹，判定参数，越小越严苛
//
//        params.SnowFlake_NoiseTh = 10;  //雪花，判定参数，越小越严苛
//        params.SnowFlake_Expth = 0.01f;  //雪花，判定噪点的阈值，越小越严苛
//
//        params.SignalLoss_Sample = 10;  //信号丢失，采样大小，越大越精确，越耗时
//        params.SignalLoss_Expth = 0.9f;  //信号丢失，判断参数，越小越严苛
//
//        params.Obscure_Expth = 15; // 虚焦，判断参数，越大越严苛
//
//        params.CamBlockEffect_Expth1 = 0.3f; // 块效应，单个块的判断阈值，越小越严苛
//        params.CamBlockEffect_Expth2 = 0.5f; //块效应，判断参数，越小越严苛
//
//        params.Occlusion_Threshold = 20; //遮挡，二值化参数
//        params.Occlusion_Expth = 0.0008f; //遮挡，判断参数，越大越严苛
//
//        params.Occlusion_BoxSizeRate = 0.0007f; //遮挡，选取盒子的大小
//        params.Occlusion_MinAreaRate = 0.23f; //遮挡，最小检测面积
//
//        params.CamFreeze_Expth = 0.05f; //冻结，判断参数，越小越严苛
//
//        params.CamContrast_Expth = 50;   //低对比度，判断参数，越小越严苛
//
//        params.CamTurnRound_Expth = 20; //转向，判断参数，越大越严苛
//        return params;
//    }
//
//    //初始化SDK
//    int CamStateInit(runParamsCpp paramsCpp);
//
//    int CamStateProcess2(int handle, String images);
//
//
//    //图片质量检测
//    int CamStateProcess(int handle, long images, int height, int width);
//
//    //释放
//    void CamStateRelease(int handle);
//}
