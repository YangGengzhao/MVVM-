package com.example.baselibrary.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;

import androidx.annotation.NonNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MFileUtils {

    private static final String FOLDER_IMAGE = "/Pictures/中宏教育";
    private static final  byte[] gSyncCode = new byte[0];

    public static File saveBitmap(Context context, Bitmap bm) {
        //指定我们想要存储文件的地址
        String TargetPath = Environment.getExternalStorageDirectory() + "" + FOLDER_IMAGE;
        //判断指定文件夹的路径是否存在
        if (!fileExist(TargetPath)) {
        } else {
            //如果指定文件夹创建成功，那么我们则需要进行图片存储操作
            File saveFile = new File(TargetPath, "" + System.currentTimeMillis() + ".jpg");
            try {
                FileOutputStream saveImgOut = new FileOutputStream(saveFile);
                bm.compress(Bitmap.CompressFormat.JPEG, 80, saveImgOut);
                saveImgOut.flush();
                saveImgOut.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                return saveFile;
            }
        }
        return null;
    }

    public static void inSystemAlbum(Context context, File file) {
        if (file == null) return;
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(), file.getAbsolutePath(), file.getName(), null);
            context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(new File(file.getPath()))));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static boolean deleteFile(@NonNull String path) {

        synchronized (gSyncCode) {
            if (TextUtils.isEmpty(path)) {
                return true;
            }

            File file = new File(path);
            if (!file.exists()) {
                return true;
            }
            if (file.isFile()) {
                return file.delete();
            }
            if (!file.isDirectory()) {
                return false;
            }
            File[] filesList = file.listFiles();

            if (filesList != null) {
                for (File f : filesList) {
                    if (f.isFile()) {
                        f.delete();
                    } else if (f.isDirectory()) {
                        deleteFile(f.getAbsolutePath());
                    }
                }
            }

            return file.delete();
        }

    }

    /**
     * @param fromName 需要重命名的文件，为文件绝对路径
     * @param toName   要改成的名字，为文件绝对路径
     * @return boolean 成功或失败
     * @Method: fileRename
     * @Description: 将文件从fromName命名为toName，由于使用的是File自带的renameTo()接口，需要注意： <li>读写存储器权限</li> <li>
     * fromName和toName这两个路径在相同的挂载点。如果不在同一挂载点，重命名失败。</li>
     */
    public static boolean fileRename(@NonNull String fromName, @NonNull String toName) {
        synchronized (gSyncCode) {
            // TODO: 根据文件名判断是否属于同一挂载点
            File fromFile = new File(fromName);
            File toFile = new File(toName);
            if (!fromFile.exists()) {
                return false;
            }
            boolean result = fromFile.renameTo(toFile);
            if (result) {
            }
            return result;
        }

    }

    /**
     * get file size
     * <ul>
     * <li>if path is null or empty, return -1</li>
     * <li>if path exist and it is a file, return file size, else return -1</li>
     * <ul>
     *
     * @param path
     * @return returns the length of this file in bytes. returns -1 if the file does not exist.
     */
    public static long getFileSize(@NonNull String path) {
        if (TextUtils.isEmpty(path)) {
            return -1;
        }

        File file = new File(path);
        return (file.exists() && file.isFile() ? file.length() : -1);
    }

    public static boolean fileExist(String fileName) {
        //传入指定的路径，然后判断路径是否存在
        File file = new File(fileName);
        if (file.exists())
            return true;
        else {
            return file.mkdirs();
        }
    }

    /**
     * 创建文件
     *
     * @param folder
     */
    public static void creatFile(String folder, Context context) {
        try {
            //新建一个File类型的成员变量，传入文件名路径。
            boolean folder1 = createFolder(folder, context);
            if (folder1) {
                File mFile = new File(context.getExternalCacheDir().getPath() + "/" + folder + "/.nomedia");
                //判断文件是否存在，
                if (mFile.exists()) {
                    return;
//                mFile.delete();
                }
                //创建文件
                mFile.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
//            Log.e("creatXMLFileException", e.getMessage());
        }
    }

    /**
     * 创建文件夹
     */
    public static boolean createFolder(String folder, Context context) {
        //获取SD卡的路径
        //String path = MyApplication.getContext().getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS).getPath();

        //getFilesDir()获取你app的内部存储空间
        File Folder = new File(context.getExternalCacheDir().getPath(), "" + folder);

        if (!Folder.exists())//判断文件夹是否存在，不存在则创建文件夹，已过经存在则跳
        {
//            Folder.mkdir();//创建文件夹
            //两种方式判断文件夹是否创建成功
            //Folder.isDirectory()返回True表示文件路径是对的，即文件创建成功，false则相反
//            boolean isFilemaked1 = Folder.isDirectory();
            //Folder.mkdirs()返回true即文件创建成功，false则相反
            boolean isFilemaked2 = Folder.mkdirs();
            return isFilemaked2;

        } else {
            return true;
        }

    }

    public static File createDownLoadFile(Context context, int what) {
        return createFile(Environment.DIRECTORY_DOWNLOADS, context, what);
    }

    public static File createPictureFile(Context context) {
        return createFile(Environment.DIRECTORY_PICTURES, context, 1);
    }

    public static File createMoviesFile(Context context) {
        return createFile(Environment.DIRECTORY_MOVIES, context, 0);
    }

    //what:区分下载的是视频还是图片 视频0,图片1
    public static File createFile(String type, Context context, int what) {
        File dir = context.getExternalFilesDir(type);
        File file = null;
        if (!dir.exists()) {
            dir.mkdirs();
        }
        if (type.equals(Environment.DIRECTORY_DOWNLOADS)) {
            if (what == 0) {
                file = new File(dir, System.currentTimeMillis() + ".mp4");
            } else {
                file = new File(dir, System.currentTimeMillis() + ".jpg");
            }
        } else if (type.equals(Environment.DIRECTORY_PICTURES)) {
            file = new File(dir, System.currentTimeMillis() + ".jpg");
        } else if (type.equals(Environment.DIRECTORY_MOVIES)) {
            file = new File(dir, System.currentTimeMillis() + ".mp4");
        }
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }

    public static String getDiskCacheDir(Context context) {
        String cachePath = null;

        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())

                || !Environment.isExternalStorageRemovable()) {
            cachePath = context.getExternalCacheDir().getPath();

        } else {
            cachePath = context.getCacheDir().getPath();

        }

        return cachePath;

    }

    public static String getFilePathFromContentUri(Uri contentUri,
                                                   ContentResolver contentResolver) {
        String filePath;
        String[] filePathColumn = {MediaStore.MediaColumns.DATA};
        Cursor cursor = contentResolver.query(contentUri, filePathColumn, null, null, null);
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        filePath = cursor.getString(columnIndex);
        cursor.close();
        return filePath;
    }
}
