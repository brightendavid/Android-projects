package com.example.adhack;

import android.app.Application;
import android.app.PendingIntent;
import android.content.pm.PackageManager;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.channels.ClosedSelectorException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ProxyApplication extends Application {
    private static final String TAG = ProxyApplication.class.getSimpleName();
    private static final String APP_KEY = "APPLICATION_CLASS_NAME";
    private static final String CLASS_NAME_ACTIVITY_THREAD = "android.app.A ctivityThread";
    private static final String CLASS_NAME_LOADED_APK = "android.app.Loaded Apk";
    private  String mSrcApkFilePath;
    private String mOdexPath;
    private String mLibPath;
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        Log.d(TAG, "attachBaseContext: --------onCreate");
        try {
            File odex = this.getDir("payload_odex", MODE_PRIVATE);
            File libs = this.getDir("payload_lib", MODE_PRIVATE);
            mOdexPath = odex.getAbsolutePath();
            mLibPath = libs.getAbsolutePath();
            mSrcApkFilePath = mOdexPath + "/payload.apk";
            File srcApkFile = new File(mSrcApkFilePath);
            Log.d(TAG, "attachBaseContext: apk size: " + srcApkFile.length());
            if (!srcApkFile.exists()) {
                Log.d(TAG, "attachBaseContext: isFirstLoading");
                // 拿到源 apk 的 dex 文件
                byte[] dexData = this.readDexFileFromApk();
                // 取出解密后的 apk 放置在/payload.apk，及其 so 文件放置在 payloa d_lib 下
                this.splitPayLoadFromDex(dexData);
            }
            Object currentActivityThread =RefInvoke.invokeStaticMethod(
                    CLASS_NAME_ACTIVITY_THREAD,
                    "currentActivityThread",
                    new Class[]{},
                    new Object[]{}
            );
            String packageName = this.getPackageName();
            ArrayMap mPackages = (ArrayMap) RefInvoke.getFieldObject(
                    CLASS_NAME_ACTIVITY_THREAD,
                    currentActivityThread,
                    "mPackages"
            );
            WeakReference wr = (WeakReference) mPackages.get(packageName);
            Object mClassLoader = RefInvoke.getFieldObject(
                    CLASS_NAME_LOADED_APK, wr.get(), "mClassLoader"
            );
            DexClassLoader dLoader = new DexClassLoader(
                    mSrcApkFilePath, mOdexPath, mLibPath, (ClassLoader) mClass Loader
            );
            Log.d(TAG, "attachBaseContext: 父 ClassLoader: " + mClassLoader);
            RefInvoke.setFieldObject(
                    CLASS_NAME_LOADED_APK,
                    "mClassLoader", wr.get(), dLoader
            );
            Log.d(TAG, "attachBaseContext: 子 ClassLoader: " + dLoader);

            try {
                Object actObj = dLoader.loadClass("com.example.hellopack.MainActiv ity");
                Log.d(TAG, "attachBaseContext: SrcApk_MainActivity: " + actObj);

            }
            catch (ClassNotFoundException e){
                e.printStackTrace();
                Log.d(TAG, "attachBaseContext: LoadSrcActivityErr: " + Log.getStackTraceString(e));
            }

        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "attachBaseContext: error: " + Log.getStackTraceString(e));
        }
    }
    private byte[] decrypt(byte[] srcData){
        for(int i =0 ;i<srcData.length;i++){
            srcData[i]^=0xFF;
        }
        return srcData;
    }
    private byte[] readDexFileFromApk() throws IOException {
        ByteArrayOutputStream baos =new ByteArrayOutputStream();
        ZipInputStream zis =new ZipInputStream(
                new BufferedInputStream(
                        new FileInputStream(
                                this.getApplicationInfo().sourceDir
                        )
                )
        );
        while(true){
            ZipEntry entry =zis.getNextEntry();
            if(null ==entry){
                zis.close();
                break;
            }
            if("class.dex".equals(entry.getName())){
                byte[] bytes =new byte[1024];
                while (true){
                    int len =zis.read(bytes);
                    if(len ==-1) break;
                    baos.write(bytes,0,len);
                }

            }
            zis.closeEntry();
        }
        zis.close();
        return baos.toByteArray();
    }
    public void onCreate(){
        super.onCreate();
        Log.d(TAG,"ONCREATE;------");
        String  appClassName = null;
        try{
            Application ai = this.getPackageManager().getApplicationInfo(this.getPackageName(), PackageManager.GET_META_DATA);


        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}

