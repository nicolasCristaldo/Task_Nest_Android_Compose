# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

-keepattributes SourceFile,LineNumberTable
-keepattributes *Annotation*
-keepattributes Signature
-keepattributes Exceptions

-dontwarn kotlin.**
-dontwarn kotlinx.**
-keep class kotlin.** { *; }
-keep class kotlinx.** { *; }
-keepclassmembers class **$WhenMappings {
    <fields>;
}
-keepclassmembers class **$Companion {
    <methods>;
    <fields>;
}

-keep class androidx.room.** { *; }
-keep @androidx.room.Entity class * { *; }
-keep @androidx.room.Dao class * { *; }
-keep class com.nicolascristaldo.tasknest.data.room.entity.TaskEntity { *; }
-keep class com.nicolascristaldo.tasknest.data.room.converters.Converters { *; }
-keepclassmembers class com.nicolascristaldo.tasknest.data.room.entity.TaskEntity {
    <fields>;
}
-keepclassmembers class com.nicolascristaldo.tasknest.data.room.converters.Converters {
    <methods>;
}

-keep class dagger.** { *; }
-keep class javax.inject.** { *; }
-keep @dagger.** class * { *; }
-keepclassmembers,allowobfuscation class * {
    @javax.inject.* *;
    @dagger.* *;
}
-keep class com.nicolascristaldo.tasknest.di.module.** { *; }
-keepclassmembers class com.nicolascristaldo.tasknest.di.module.** {
    <methods>;
}

-keep class androidx.work.** { *; }
-keep class com.nicolascristaldo.tasknest.data.worker.** { *; }
-keepclassmembers class com.nicolascristaldo.tasknest.data.worker.** {
    <fields>;
    <methods>;
}
-keepclassmembers class androidx.work.Worker {
    <init>(android.content.Context, androidx.work.WorkerParameters);
}
-keep class androidx.work.WorkerFactory { *; }
-keepclassmembers class androidx.work.WorkerFactory {
    <methods>;
}

-keep class com.nicolascristaldo.tasknest.domain.model.Task { *; }
-keep class com.nicolascristaldo.tasknest.domain.model.Category { *; }
-keep class com.nicolascristaldo.tasknest.domain.model.Status { *; }
-keepclassmembers class com.nicolascristaldo.tasknest.domain.model.** {
    <fields>;
    <methods>;
}

-keep class com.nicolascristaldo.tasknest.data.mapper.** { *; }
-keepclassmembers class com.nicolascristaldo.tasknest.data.mapper.** {
    <methods>;
}

-keep class com.nicolascristaldo.tasknest.R { *; }
-keepclassmembers class com.nicolascristaldo.tasknest.R$* {
    <fields>;
}

-dontwarn androidx.**
-dontwarn dagger.**
-dontwarn javax.inject.**
-dontwarn okio.**
-dontwarn kotlinx.coroutines.**

-dontobfuscate
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class * extends android.view.View