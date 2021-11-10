package com.example.baselibrary.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import top.zibin.luban.CompressionPredicate;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;
import top.zibin.luban.OnRenameListener;

/**
 * @author David
 * @description:
 * @date :2020/8/27 14:09
 */
public class ImageUtil {
    /**
     * 获取网络图片
     *
     * @param url
     * @return
     */
    public static Bitmap getBitmap(String url) {
        Bitmap bm = null;
        try {
            URL iconUrl = new URL(url);
            URLConnection conn = iconUrl.openConnection();
            HttpURLConnection http = (HttpURLConnection) conn;

            int length = http.getContentLength();

            conn.connect();
            // 获得图像的字符流
            InputStream is = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is, length);
            bm = BitmapFactory.decodeStream(bis);
            bis.close();
            is.close();// 关闭流
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bm;
    }

    /**
     * 压缩图片
     *
     * @param context
     * @param file             文件地址
     * @param url              预签名地址
     * @param keyName          重命名文件
     * @param compressListener 压缩监听
     * @author lee
     * @date 2021/7/27 16:26
     */
    public static void CutDownPicture(Context context, File file, String url, String keyName, CompressListener compressListener) {
        boolean picture = MFileUtils.createFolder("picture", context);
        Luban.with(context)
                .load(file)//加载压缩文件
                .ignoreBy(100)//单位K，小于100k不进行压缩
                .setFocusAlpha(false)
                .setTargetDir(picture ? context.getExternalCacheDir().getPath() + "/picture" : context.getExternalCacheDir().getPath())//设置压缩后的保存路径
                .filter(new CompressionPredicate() {
                    @Override
                    public boolean apply(String path) {
                        return !(TextUtils.isEmpty(path));
                    }
                })
                .setCompressListener(new OnCompressListener() {
                    @Override
                    public void onStart() {
                        if (compressListener != null) {
                            compressListener.start();
                        }
                    }

                    @Override
                    public void onSuccess(File file) {
//                        MLog.e("TAG", "名字:" + file.getName() + "路径:" + file.getAbsolutePath() + "大小:" + file.length());
//                        uploadFile(file.getAbsolutePath(), url);
                        if (compressListener != null) {
                            compressListener.success(file, url);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
//                        ToastUtil.show("压缩失败");
                        if (compressListener != null) {
                            compressListener.error(e);
                        }
//                        WeiboDialogUtils.closeDialog(dialog);
                    }
                }).setRenameListener(new OnRenameListener() {
            @Override
            public String rename(String filePath) {
                String result = keyName + "";
                return result;
            }//压缩前进行重新命名
        }).launch();
    }


    public interface CompressListener {
        void start();

        /**
         * @param file 文件压缩后的路径
         * @param url  预签名地址
         * @author lee
         * @date 2021/7/27 16:33
         */
        void success(File file, String url);

        void error(Throwable e);
    }

}
