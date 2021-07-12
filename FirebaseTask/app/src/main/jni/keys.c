
#include <jni.h>

JNIEXPORT jstring JNICALL
Java_com_example_firebasetask_NdkTestActivity_getNativeKey1(JNIEnv *env, jobject instance) {

    return (*env)->  NewStringUTF(env, "QUl6YVN5QjJGOEVhLUZpaVctMVl5c3ZuSThXQVkzRm5tWVFXc3Rj");
}

