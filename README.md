# Nexus Utilities

### Java 21 library designed to simplify the development of Java programs.

---

[![Javadoc](https://img.shields.io/badge/JavaDoc-Online-green)](https://zyneonstudios.github.io/nexus-utilities/apidocs/)

---

### Contents
- NexusUtilities
- AES
  - AESUtility: Encrypt and decrypt bytes with a key
- File
  - FileActions: Some actions for files like folder deletion
  - FileExtractor: Extract files from zip and resources
  - FileGetter: Read and download files
- Json
  - GsonUtility: Get GSON string and objects from files and urls
- Logger
  - NexusLogger: Output to console
- Storage
  - JsonStorage: Saving and reading stuff in a json file
  - LocalStorage: Saving and reading stuff in local cache (gone after session)
  - Storage (Interface)
- Strings
  - StringConverter: Convert strings
  - StringGenerator: Generate strings
- System
  - OperatingSystem: Get OS information

---

### Implementation

You can implement this library via [Maven](#implement-via-maven), [Gradle (Groovy)](#implement-via-gradle-groovy),  [Gradle (Kotlin)](#implement-via-gradle-kotlin), [SBT](#implement-via-sbt) and [local library](https://github.com/zyneonstudios/nexus-utilities/releases/latest/)

#### Implement via Maven
```
<repositories>
    <!--Other repositories-->
    <repository>
      <id>zyneonstudios-repo-releases</id>
      <name>Zyneon Studios Maven repository</name>
      <url>https://maven.zyneonstudios.com/releases</url>
    </repository>
</repositories>
```
```
<dependencies>
    <!--Other dependencies-->
    <dependency>
        <groupId>com.zyneonstudios.nexus</groupId>
        <artifactId>base-utilities</artifactId>
        <version>LATEST</version>
    </dependency>
</dependencies>
```

#### Implement via Gradle (Groovy)
```
repositories {
    maven {
        name "zyneonstudiosRepoReleases"
        url "https://maven.zyneonstudios.com/releases"
    }
}
```
```
dependencies {
    implementation 'com.zyneonstudios.nexus:base-utilities:+'
}
```

#### Implement via Gradle (Kotlin)
```
repositories {
    maven {
        name = "zyneonstudiosRepoReleases"
        url = uri("https://maven.zyneonstudios.com/releases")
    }
}
```
```
dependencies {
    implementation('com.zyneonstudios.nexus:base-utilities:+')
}
```

#### Implement via SBT
```
resolvers += "zyneonstudios-repo-releases" at "https://maven.zyneonstudios.com/releases"
```
```
libraryDependencies += "com.zyneonstudios.nexus" % "base-utilities" % "LATEST_VERSION"
```