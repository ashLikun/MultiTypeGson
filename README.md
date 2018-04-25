
[![Release](https://jitpack.io/v/ashLikun/MultiTypeGson.svg)](https://jitpack.io/#ashLikun/MultiTypeGson)

# **MultiTypeGson**
1:Gson解析复杂的type数据
## 使用方法

build.gradle文件中添加:
```gradle
allprojects {
    repositories {
        maven { url "https://jitpack.io" }
    }
}
```
并且:

```gradle
dependencies {
    implementation 'com.github.ashLikun:MultiTypeGson:{latest version}'//MultiTypeGson
}
```
### 1.用法

```java
     GsonBuilder builder = new MultiTypeGsonBuilder()
                    //指定要解析的字段名称
                    .registerTypeElementName("type")
                    //是否强制把外层的type字段的值设置给内部
                    .forceUseUpperTypeValue()
                    //注册外部解析类
                    .registerTargetUpperLevelClass(DataListBean.class)
                    //注册内部对应type解析类
                    .registerTypeElementClass("AAA_type", TopBannerData.class)
                    .registerTypeElementClass("BBB_type", HomeData.BaseInfo.class)
                    .build();
            HomeData data = builder.create().fromJson(json, HomeData.class);
```

### 混肴
####


