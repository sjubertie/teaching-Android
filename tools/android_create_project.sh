#!/bin/bash

ANDROID_SDK_PATH="/home/orion/opt/android-sdk"
PROJECT_NAME=""
ACTIVITY_NAME=""
PACKAGE_NAME=""
PACKAGE_DIR=""
MIN_SDK_VERSION="21"
TARGET_SDK_VERSION="21"
TARGET_NUM=27

get_information()
{
    echo "Project parameters:"
    while [ -z "$PROJECT_NAME" ];
    do
	echo "Enter project name:"
	read PROJECT_NAME
    done
    while [ -z "$ACTIVITY_NAME" ];
    do
	echo "Enter activity name:"
	read ACTIVITY_NAME
    done
    while [ -z "$PACKAGE_NAME" ];
    do
	echo "Enter package name (domain.firm....):"
	read PACKAGE_NAME
	if [ "$(echo $PACKAGE_NAME | wc -w)" -ne "1" ];
	then
	    echo "Error: Package name must contain only one word."
	    PACKAGE_NAME=""
	fi
	if [ "$(echo $PACKAGE_NAME | tr '.' ' ' | wc -w)" -lt "2" ];
	then
	    echo "Error: package name must contain at least one dot."
	    PACKAGE_NAME=""
	fi
    done
    PACKAGE_DIR=$(echo $PACKAGE_NAME | tr '.' '/' )
    echo $PACKAGE_DIR
}


create_android_manifest()
{
    echo "Create AndroidManifest.xml"
cat <<EOF > AndroidManifest.xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
      package="$PACKAGE_NAME"
      android:versionCode="1"
      android:versionName="1.0">
      <uses-sdk
      	android:minSdkVersion="$MIN_SDK_VERSION"
      	      	android:targetSdkVersion="$TARGET_SDK_VERSION"
      />
    <application android:label="@string/app_name"
android:theme="@android:style/Theme.NoTitleBar.Fullscreen"
     android:icon="@mipmap/ic_launcher">
        <activity android:name="$ACTIVITY_NAME"
                  android:label="@string/app_name">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>
</manifest>
EOF
}


create_tree()
{
    echo "Create project tree"
    mkdir -p lib res/layout res/values src/$PACKAGE_DIR
}


create_main_activity()
{
    echo "Create main activity"
    cat <<EOF > src/$PACKAGE_DIR/$ACTIVITY_NAME.java
package $PACKAGE_NAME;

import android.app.Activity;
import android.os.Bundle;

public class $ACTIVITY_NAME extends Activity
{
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main_activity);
  }
}
EOF
}


create_main_layout()
{
    echo "Create layout."
    cat <<EOF > res/layout/main_activity.xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
	      android:orientation="vertical"
	      android:layout_width="fill_parent"
	      android:layout_height="fill_parent"
	      >
<TextView
    android:layout_width="fill_parent"
    android:layout_height="wrap_content"
    android:text="Hello Android!"
    />
</LinearLayout>
EOF
}


create_strings()
{
    echo "Create strings."
    cat <<EOF > res/values/strings.xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
  <string name="app_name">$ACTIVITY_NAME</string>
</resources>
EOF
}


create_mipmap()
{
  mkdir -p res/mipmap-hdpi res/mipmap-mdpi res/mipmap-xhdpi res/mipmap-xxhdpi res/mipmap-xxxhdpi
    cat <<EOF | base64 -d > res/mipmap-xxxhdpi/ic_launcher.png
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
EOF

	convert res/mipmap-xxxhdpi/ic_launcher.png -resize 144x144 res/mipmap-xxhdpi/ic_launcher.png
	convert res/mipmap-xxxhdpi/ic_launcher.png -resize 96x96 res/mipmap-xhdpi/ic_launcher.png
	convert res/mipmap-xxxhdpi/ic_launcher.png -resize 72x72 res/mipmap-hdpi/ic_launcher.png
	convert res/mipmap-xxxhdpi/ic_launcher.png -resize 48x48 res/mipmap-mdpi/ic_launcher.png
}

create_compilation_file()
{
cat <<EOF > compile.sh
#!/bin/bash

rm -rf bin/* obj/* gen/*
mkdir -p obj bin gen

COL0="\e[1;33m"
RES="\e[0m"

msg_info()
{
	echo -e "$COL0$1$RES"
}

msg_info "Generating R.java"

aapt package -v -f -m -S res/ -J src -M AndroidManifest.xml -I $ANDROID_SDK_PATH/platforms/android-$TARGET_NUM/android.jar 

msg_info "Compiling *.java files"

javac -verbose -d obj -classpath $ANDROID_SDK_PATH/platforms/android-$TARGET_NUM/android.jar:$PWD/obj -sourcepath $PWD/src $PWD/src/$PACKAGE_DIR/*.java

msg_info "Generating DEX file"

dx --dex --verbose --output=bin/classes.dex obj lib

msg_info "Generating unsigned APK file"

aapt package -v -f -M AndroidManifest.xml -S res -I $ANDROID_SDK_PATH/platforms/android-$TARGET_NUM/android.jar -F bin/$PROJECT_NAME-unsigned-unaligned.apk bin


msg_info "Aligning unsigned APK file"

zipalign -v -f 4 bin/$PROJECT_NAME-unsigned-unaligned.apk bin/$PROJECT_NAME-unsigned-aligned.apk

msg_info "Signing aligned APK file"

apksigner sign --ks ~/.android/debug.keystore --ks-pass pass:android --key-pass pass:android --out bin/$PROJECT_NAME.apk bin/$PROJECT_NAME-unsigned-aligned.apk
EOF
}


if [ $(ls|wc -l) -gt "0" ];
then
    echo "Error: Non empty directory."
else
    get_information
    create_android_manifest
    create_tree
    create_main_activity
    create_main_layout
    create_strings
    create_mipmap
    create_compilation_file
fi
