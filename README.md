# offline-gbt12350-calculator

示例 Android（Kotlin）工程 — 将本地 HTML 计算器在 WebView 中离线加载

说明：
- 本项目示例实现了把本地 assets 中的 index.html 通过 WebViewAssetLoader 映射到 https://appassets.androidplatform.net/ 并加载。
- 应用不声明 INTERNET 权限（AndroidManifest 中未包含），并且在 WebView 层对外部请求做了拦截，确保“完全离线、数据不联网”。

如何使用：
1. 在 Android Studio 中打开此仓库根目录作为项目。
2. 把你已有的 HTML/静态资源复制到 app/src/main/assets/（保持相对路径），并确保所有外部引用（CDN、API）已替换为本地文件。
3. 运行（Run）或使用 Gradle assembleDebug 生成 APK。默认包名：com.example.offlinecalculator

我可以帮你：
- 把你上传的 HTML/静态资源替换进 assets 并验证没有外部请求。
- 在服务器上构建并发布 debug APK（你已选择源码+APK）。如果要我替你构建并上传 Release，请授权或提供签名信息。
