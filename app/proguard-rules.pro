-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.module.AppGlideModule
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
#Fragment
-keep class * extends androidx.fragment.app.Fragment{}
#dexter
-renamesourcefileattribute SourceFile
# Preserve all Dexter classes and method names
-keepattributes InnerClasses, Signature, *Annotation*
-keep class com.karumi.dexter.** { *; }
-keep interface com.karumi.dexter.** { *; }
-keepclasseswithmembernames class com.karumi.dexter.** { *; }
-keepclasseswithmembernames interface com.karumi.dexter.** { *; }
-keep public class * implements com.videon.downloader.include.API{ *; }
-keepclassmembers class com.videon.downloader.list.** { <fields>; }

-keep class com.truenet.** {
      *;
}

-keepattributes Exceptions, InnerClasses, Signature, Deprecated, SourceFile,
LineNumberTable, *Annotation*, EnclosingMethod
-dontwarn android.webkit.JavascriptInterface
-dontwarn org.jetbrains.annotations.**

# Gson specific classes
-dontwarn sun.misc.**
#-keep class com.google.gson.stream.** { *; }

# Application classes that will be serialized/deserialized over Gson
-keep class com.google.gson.examples.android.model.** { <fields>; }

# Prevent proguard from stripping interface information from TypeAdapterFactory,
# JsonSerializer, JsonDeserializer instances (so they can be used in @JsonAdapter)
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer

# Prevent R8 from leaving Data object members always null
-keepclassmembers,allowobfuscation class * {
  @com.google.gson.annotations.SerializedName <fields>;
}

#calligraphy
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }

-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}

-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}

#loopj
-keep class cz.msebera.android.httpclient.** { *; }
-keep class com.loopj.android.http.** { *; }
# For communication with AdColony's WebView
-keepclassmembers class * {
    @android.webkit.JavascriptInterface <methods>;
}

# Retrofit
-keep class com.google.gson.** { *; }
-keep public class com.google.gson.** {public private protected *;}
-keep class com.google.inject.** { *; }
-keep class org.apache.http.** { *; }
-keep class org.apache.james.mime4j.** { *; }
-keep class javax.inject.** { *; }
-keep class javax.xml.stream.** { *; }
-keep class retrofit.** { *; }
-keep class com.google.appengine.** { *; }
-keepattributes EnclosingMethod

-keepclasseswithmembers class * {
    @retrofit2.* <methods>;
}

-keepclasseswithmembers interface * {
    @retrofit2.* <methods>;
}
-keepclassmembers class com.videon.downloader.** {
      *;
    }
-keep class org.json.** { *; }
-dontnote retrofit2.Platform
-keepattributes Exceptions
-keepattributes *Annotation*
-keepattributes Signature
-dontwarn com.squareup.okhttp.*
-dontwarn rx.**
-dontwarn javax.xml.stream.**
-dontwarn com.google.appengine.**
-dontwarn java.nio.file.**
-dontwarn org.codehaus.**
-dontwarn okio.**
-dontwarn javax.annotation.**
-dontwarn com.loopj.android.http.**
-dontwarn org.apache.http.**
-dontwarn rx.internal.util.**

-dontwarn rx.**

-dontwarn okio.**

-dontwarn com.squareup.okhttp.**
-keep class com.squareup.okhttp.** { *; }
-keep interface com.squareup.okhttp.** { *; }

-dontwarn retrofit.**
-dontwarn retrofit.appengine.UrlFetchClient
-keep class retrofit.** { *; }
-keepclasseswithmembers class * {
    @retrofit.http.* <methods>;
}
#TTTTTT
-keep class com.tapdaq.sdk.** { *; }
-keep class com.tapdaq.adapters.* { *; }
-keep class com.tapdaq.unityplugin.* { *; }
-keep class com.google.android.gms.ads.identifier.** { *; }
-keepclassmembers class * {
    @android.webkit.JavascriptInterface <methods>;
}
-dontwarn android.app.Activity
-keep public class com.google.android.gms.ads.** {
    public *;
}
-keep public class com.google.ads.** {
    public *;
}
-dontwarn com.applovin.**
-keep class com.applovin.** { *; }
-keep class com.chartboost.** { *; }
-keep class com.facebook.ads.** { *; }
-keepattributes SourceFile,LineNumberTable,InnerClasses
-keep class com.inmobi.** { *; }
-dontwarn com.inmobi.**
-keep class com.google.android.gms.common.api.GoogleApiClient { public *; }
-keep class com.google.android.gms.common.api.GoogleApiClient$* {public *;}
-keep class com.google.android.gms.ads.identifier.AdvertisingIdClient$Info{
     public *;
}
-dontwarn com.google.android.gms.**
-keep class com.squareup.picasso.** {*;}
-dontwarn com.squareup.picasso.**
-keep class com.moat.** {*;}
-dontwarn com.moat.**
-keepclassmembers class * implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator *;
}
-keep public class com.google.android.gms.ads.** {
   public *;
}
-keep class com.ironsource.adapters.** { *;
}
-keep interface jp.maio.sdk.android.**
-keep class jp.maio.sdk.android.** {*;}
-dontwarn jp.maio.sdk.android.**
-keep class com.bytedance.sdk.** { *; }
-keep class com.pgl.sys.ces.* {*;}
-keep class com.moat.** { *; }
-keepattributes JavascriptInterface
-keepattributes *Annotation*

-keep public class com.google.android.gms.common.internal.safeparcel.SafeParcelable {
public static final *** NULL;
}
-keepnames @com.google.android.gms.common.annotation.KeepName class *
-keepclassmembernames class * {
@com.google.android.gms.common.annotation.KeepName *;
}
-keepnames class * implements android.os.Parcelable {
public static final ** CREATOR;
}
-keep class com.google.android.gms.ads.identifier.** { *; }
-keepattributes SourceFile,LineNumberTable
-keepattributes JavascriptInterface

-keep class android.webkit.JavascriptInterface {
   *;
}

-keep class com.unity3d.ads.** {
   *;
}

-keep class com.unity3d.services.** {
   *;
}
-dontwarn com.unity3d.services.**
-keep class javax.inject.*

# GreenRobot
-dontwarn de.greenrobot.event.util.**

# RxJava
-dontwarn rx.internal.util.unsafe.**
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
   long producerIndex;
   long consumerIndex;
}

# MOAT
-dontwarn com.moat.**
-keep class com.moat.** { public protected private *; }

# Retrofit
-dontwarn okio.**

-dontwarn com.facebook.infer.annotation.Nullsafe$Mode
-dontwarn com.facebook.infer.annotation.Nullsafe

#SVG
-dontwarn com.bumptech.glide.GenericRequestBuilder
-dontwarn com.bumptech.glide.GenericTranscodeRequest
-dontwarn com.bumptech.glide.RequestManager$GenericModelRequest$GenericTypeRequest
-dontwarn com.bumptech.glide.RequestManager$GenericModelRequest
-dontwarn com.bumptech.glide.load.resource.file.FileToStreamDecoder

-keep public class com.google.android.gms.** { public *; }

-dontwarn com.google.api.client.extensions.android.**
-dontwarn com.google.api.client.googleapis.extensions.android.**
-dontnote java.nio.file.Files, java.nio.file.Path
-dontnote **.ILicensingService
-dontnote sun.misc.Unsafe
-dontwarn sun.misc.Unsafe
-dontwarn com.google.common.**
-dontwarn com.google.api.client.json.**
-keep class com.android.vending.billing.**

-keepclassmembers class * extends androidx.viewbinding.ViewBinding {
    public static *** bind(android.view.View);
}
-keepclassmembers class com.videon.downloader.databinding.**  {
    public <methods>;
}
-keep class com.videon.downloader.databinding.**
-keep class * implements androidx.viewbinding.ViewBinding {
    public static *** bind(android.view.View);
    public static *** inflate(android.view.LayoutInflater);
    }