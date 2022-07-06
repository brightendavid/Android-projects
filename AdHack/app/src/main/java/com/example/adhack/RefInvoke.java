package com.example.adhack;

import java.lang.reflect.Method;

public class RefInvoke {
    /**
     * 不知道在做什么
     * 反射执行类静态函数
     * @param className 类名
     * @param methodName 方法名
     * @param pareTypes 函数的参数类型
     * @param pareValues 调用函数时传入的参数
     * @return
     */
    public static Object invokeStaticMethod(String className,String methodName,Class[] pareTypes,Object[] pareValues){
        try {
            Class objClass = Class.forName(className);
            Method method = objClass.getMethod(methodName,pareTypes);
            return method.invoke (null,pareTypes);
        }
        catch (ClassNotFoundException | NoSuchMethodException | InvocationTarget Exception | IllegalAccessException e){
            e.printStackTrace();
        }
    }
    public static Object invokeMethod((String className, String methodName, Object obj, Class[] pareTypes, Object[] pareValues){
        try {
            Class objClass = Class.forName(className);
            Method method = objClass.getMethod(methodName,pareTypes);
            return method.invoke (obj,pareTypes);
        }
        catch (ClassNotFoundException | NoSuchMethodException | InvocationTarg etException | IllegalAccessException e){
            e.printStackTrace();
        }
    }
    public static Object getFieldObject(String className, Object obj, String fieldName){
        try {
            Class objClass = Class.forName(className);
            Field field = objClass.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(obj);
        }
        catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException e){
            e.printStackTrace();
        }
        return null;
    }
    public static Object getStaticFieldObject(String className, Object obj, String fieldName){
        try {
            Class objClass = Class.forName(className);
            Field field = objClass.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(null);
        }
        catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException e){
            e.printStackTrace();
        }
        return null;
    }
    public static void setFieldObject(String className, String fieldName, Object obj, Object fieldValue) {
        try {
            Class objClass  = Class.forName(className);
            Field field = objClass.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(obj, fieldValue);
        }
        catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
    public static void setStaticObject(String className, String fieldName, String fieldValue) {
        try {
            Class objClass  = Class.forName(className);
            Field field = objClass.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(obj, fieldValue);
        }
        catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }


}
