//package com.hugh.eureka.config;
//
//import com.sun.jna.Library;
//import com.sun.jna.Native;
//import com.sun.jna.Pointer;
//
///**
// * 图片监测
// *
// * @author AS
// * @date 2020/8/19 9:30
// */
//
//public interface CamStatusSdkSo extends Library {
//
//    CamStatusSdkSo INSTANCE = (CamStatusSdkSo) Native.loadLibrary(CamStatusSdk.fileName(), CamStatusSdkSo.class);
//
//    /**
//     * 初始化 图片校验参数
//     *
//     * @return
//     */
//    public static Pointer ini() {
//        return INSTANCE.CamStateInit(CamStatusSdk.setParams());
//    }
//
//    public static Pointer ini(int CheckItemOn) {
//        return INSTANCE.CamStateInit(CamStatusSdk.setParams(CheckItemOn));
//    }
//
//    //初始化SDK
//    Pointer CamStateInit(CamStatusSdk.runParamsCpp paramsCpp);
//
//    int CamStateProcess2(Pointer handle, String imagePath);
//
//    //图片质量检测
//    int CamStateProcess(Pointer handle, long images, int height, int width);
//
//    //释放
//    void CamStateRelease(Pointer handle);
//}
