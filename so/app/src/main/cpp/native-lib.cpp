#include <jni.h>
#include <string>




extern "C" JNIEXPORT jstring JNICALL
Java_com_example_so_MainActivity_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}
extern "C"
JNIEXPORT jint JNICALL
Java_com_example_so_MainActivity_addInt(JNIEnv *env, jobject thiz, jint a, jint b) {
    // TODO: implement addInt()
    /**
     * @param a
     * @param b
     * @return a+b
     */
    return a+b;
}




extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_so_MainActivity_addString(JNIEnv *env, jobject thiz, jstring a, jstring b) {
    // TODO: implement addString()
    char *inputS1 =(char *)(env->GetStringUTFChars(a,JNI_FALSE));
    char *inputS2 =(char *)(env->GetStringUTFChars(b,JNI_FALSE));
    size_t size1 =strlen(inputS1);
    size_t size2 =strlen(inputS2);
    char *newString = (char *) malloc(size1+size2);
    strncpy(newString,inputS1,size1);
    strncat(newString,inputS2,size2);
    env->ReleaseStringUTFChars(a,inputS1);
    env->ReleaseStringUTFChars(b,inputS2);

    return env->NewStringUTF(newString);
}
extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_so_MainActivity_xorString(JNIEnv *env, jobject thiz, jstring a, jstring b) {
    // TODO: implement xorString()
    char *inputS1 =(char *)(env->GetStringUTFChars(a,JNI_FALSE));
    char *inputS2 =(char *)(env->GetStringUTFChars(b,JNI_FALSE));
    size_t size1 =strlen(inputS1);
    size_t size2 =strlen(inputS2);
    if(size1!=size2){
        return env->NewStringUTF(inputS1);
    }
//    char *newString = (char *) malloc(size1+size2);
    char * pstr = inputS1;
    while(*pstr != '\0'){
        (*pstr) = (* pstr)^(* inputS2);
        ++pstr;
    }
//    strncpy(newString,inputS1,size1);
//    strncat(newString,inputS2,size2);
//    env->ReleaseStringUTFChars(a,inputS1);
    env->ReleaseStringUTFChars(b,inputS2);

    return env->NewStringUTF(inputS1);
}