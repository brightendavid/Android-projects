package com.example.lib;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.zip.Adler32;

public class MyClass {
    public static void main(String[] args) throws IOException {
        try {
            System.out.println("hello Java");
            File srcApkFile = new File("force/AdHack.apk");
            System.out.println("path:"+srcApkFile.getAbsolutePath());
            System.out.println("size:"+srcApkFile.length());
            byte[] enSrcApkArray = encrypt(readFileBytes(srcApkFile));
            // 完成apk加密

            // 新建dex文件
            String str1 ="force/classes.dex";
            File file1 =new File(str1);
            if(!file1.exists()){
                if(!file1.createNewFile()){
                    System.out.println("file creat error");
                }
            }
            FileOutputStream fos =new FileOutputStream(str1);
            fos.write(enSrcApkArray);
            fos.flush();
            fos.close();
            // 保存加密后的apk
            String str ="AdHack_encrpt.apk";
            File file = new File(str);
            if(!file.exists()){
                if(!file.createNewFile()){
                    System.out.println("file creat error");
                }
            }

            FileOutputStream fos1 =new FileOutputStream(str);
            fos1.write(enSrcApkArray);
            fos1.flush();
            fos1.close();






            File unShellDexFile =new File("force/classesShell.dex");
            System.out.println("unshellDex file path:"+unShellDexFile.getAbsolutePath());
            System.out.println("unshellDex size:"+unShellDexFile.length());
            byte[] unShellDexArray =readFileBytes(unShellDexFile);
            int unShellDexLen = unShellDexArray.length;
            int enSrcApkLen =enSrcApkArray.length;
            int totalLen = unShellDexLen + enSrcApkLen +4; // Dex+加密后的APK
            byte[] newDex= new byte[totalLen];
            System.arraycopy(unShellDexArray,0,newDex,0,unShellDexLen);
            System.arraycopy(enSrcApkArray,0,newDex,unShellDexLen,enSrcApkLen);
            System.arraycopy(intToByte(enSrcApkLen),0,newDex,totalLen-4,4);


            // 修改文件头
            fixFileSizeHeader(newDex);
            fixSHA1Header(newDex);
            fixCheckSumHeader(newDex);
            String str2 = "force/classesShell.dex";
            File file2 = new File(str2);
            if(!file2.exists()){
                if(!file2.createNewFile()){
                    System.out.println("file create error");
                    return;
                }
            }
            FileOutputStream fos2 =new FileOutputStream(str2);
            fos2.write(newDex);
            fos2.flush();
            fos2.close();





        }
        catch (IOException | NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        // fos为已经加密的APK
    }

    private static void fixCheckSumHeader(byte[] dexBytes) {
        // 修改校验码checkSum  8-11位  站4位 从12到最后计算校验码
        // 算法不明
        Adler32 adler = new Adler32();
        adler.update(dexBytes,12,dexBytes.length-12);
        long value =adler.getValue();
        int va =(int) value;
        byte[] newCs =intToByte(va);
        byte[] reCs =new byte[4];
        for(int i =0 ;i<4;i++){
            reCs[i] =newCs[newCs.length-1-i];
            System.out.println("fix SumHeader:"+Integer.toHexString(newCs[i]));
        }
        System.arraycopy(reCs,0,dexBytes,8,4);
        System.out.println("fix check SumHeader:"+ Long.toHexString(value));

    }

    private static void fixSHA1Header(byte[] dexBytes) throws NoSuchAlgorithmException {
        // signature   12 - 32 占20Byte
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        md.update(dexBytes,32,dexBytes.length-32);
        byte[] newDt = md.digest();
        System.arraycopy(newDt,0,dexBytes,12,20);
        StringBuilder hexStr = new StringBuilder();
        for (byte anNewDt:newDt){
            hexStr.append(Integer.toString((anNewDt &0xFF)+0x100,16).substring(1));
        }
        System.out.println("fixSHA1Header:" + hexStr.toString());
    }

    private static void fixFileSizeHeader(byte[] dexBytes) {
        //文件长度
        byte[] newFS =intToByte(dexBytes.length);
        System.out.println("fix File size header :"+ Integer.toHexString(dexBytes.length));
        byte[] reFs =new byte[4];
        for(int i  =0 ; i<4;i++){
            reFs[i] =newFS[newFS.length-1-i];
            System.out.println("fix File Header:"+ Integer.toHexString(newFS[i]));

        }
        System.arraycopy(reFs,0,dexBytes,32,4);
    }

    private static byte[] intToByte(int number) {
        byte[] b = new byte[4];
        for (int i = 3; i >= 0; i--) {
            b[i] = (byte) (number % 256);
            number >>= 8;
        }
        return b;
    }

    private static byte[] readFileBytes(File file) throws IOException {
        System.out.println('1');
        byte[] bytes =new byte[1024];
        ByteArrayOutputStream baos =new ByteArrayOutputStream();
        FileInputStream fis = new FileInputStream(file);
        while (true){
            int len =fis.read(bytes);
            if(-1==len) break;
            baos.write(bytes,0,len);
        }
        byte[] byteArray =baos.toByteArray();
        fis.close();
        baos.close();
        return byteArray;
    }
    private static byte[] encrypt(byte[] SrcApkArray) {
        System.out.println("2");
        for(int i =0;i<SrcApkArray.length;i++){
            SrcApkArray[i]^=0xFF;
        }
        return SrcApkArray;
    }
}