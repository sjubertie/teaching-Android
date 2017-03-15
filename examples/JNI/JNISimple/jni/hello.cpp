#include "hello.h"

#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT jstring JNICALL Java_sjubertie_examples_JNISimpleDemoActivity_hello(JNIEnv * env, jclass c)
  {
  return env->NewStringUTF("Hello JNI!" );
}

#ifdef __cplusplus
}
#endif
