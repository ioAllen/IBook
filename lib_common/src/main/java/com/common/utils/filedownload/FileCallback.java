package com.common.utils.filedownload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class FileCallback implements Callback<ResponseBody> {
    /**
     * 目标文件存储的文件夹路径
     */
    private String destFileDir;
    /**
     * 目标文件存储的文件名
     */
    private String destFileName;

    public FileCallback(String destFileDir, String destFileName) {
        this.destFileDir = destFileDir;
        this.destFileName = destFileName;
    }

    /**
     * 成功后回调
     */
    public abstract void onSuccess(File file);

    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        try {
            saveFile(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private File saveFile(Response<ResponseBody> response) throws Exception {
        InputStream in = null;
        FileOutputStream out = null;
        byte[] buf = new byte[2048 * 10];
        int len;
        try {
            File dir = new File(destFileDir);
            // 如果文件不存在新建一个
            if (!dir.exists()) {
                dir.mkdirs();
            }
            ResponseBody body = response.body();
            if (body != null) {
                in = body.byteStream();
            }
            File file = new File(dir, destFileName);
            out = new FileOutputStream(file);
            if (in != null) {
                while ((len = in.read(buf)) != -1) {
                    out.write(buf, 0, len);
                }
            }
            // 回调成功的接口
            onSuccess(file);
            return file;
        } finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }
}
