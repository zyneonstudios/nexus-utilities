# Nexus Utilities `2024.9.3`

### Java 21 library designed to simplify the development of Java programs.

---

[![Javadoc](https://img.shields.io/badge/JavaDoc-Online-green)](https://zyneonstudios.github.io/nexus-utilities/apidocs/)

---

### Contents
- [NexusUtilities](https://zyneonstudios.github.io/nexus-utilities/apidocs/com/zyneonstudios/nexus/utilities/NexusUtilities)
- [AES](https://zyneonstudios.github.io/nexus-utilities/apidocs/com/zyneonstudios/nexus/utilities/aes/package-summary)
  - [AESUtility: Encrypt and decrypt bytes with a key](https://zyneonstudios.github.io/nexus-utilities/apidocs/com/zyneonstudios/nexus/utilities/aes/AESUtility)
- [File](https://zyneonstudios.github.io/nexus-utilities/apidocs/com/zyneonstudios/nexus/utilities/file/package-summary)
  - [FileActions: Some actions for files like folder deletion](https://zyneonstudios.github.io/nexus-utilities/apidocs/com/zyneonstudios/nexus/utilities/file/FileActions)
  - [FileExtractor: Extract files from zip and resources](https://zyneonstudios.github.io/nexus-utilities/apidocs/com/zyneonstudios/nexus/utilities/file/FileExtractor)
  - [FileGetter: Read and download files](https://zyneonstudios.github.io/nexus-utilities/apidocs/com/zyneonstudios/nexus/utilities/file/FileGetter)
- [Json](https://zyneonstudios.github.io/nexus-utilities/apidocs/com/zyneonstudios/nexus/utilities/json/package-summary)
  - [GsonUtility: Get GSON string and objects from files and urls](https://zyneonstudios.github.io/nexus-utilities/apidocs/com/zyneonstudios/nexus/utilities/json/GsonUtility)
- [Logger](https://zyneonstudios.github.io/nexus-utilities/apidocs/com/zyneonstudios/nexus/utilities/logger/package-summary)
  - [NexusLogger: Output to console](https://zyneonstudios.github.io/nexus-utilities/apidocs/com/zyneonstudios/nexus/utilities/logger/NexusLogger)
- [SQL](https://zyneonstudios.github.io/nexus-utilities/apidocs/com/zyneonstudios/nexus/utilities/sql/package-summary)
  - [MySQL: Simplifies the creation of a mysql/mariadb connection](https://zyneonstudios.github.io/nexus-utilities/apidocs/com/zyneonstudios/nexus/utilities/sql/MySQL)
  - [SQL (Interface)](https://zyneonstudios.github.io/nexus-utilities/apidocs/com/zyneonstudios/nexus/utilities/sql/SQL)
  - [SQLite: Simplifies the creation of a sqlite connection](https://zyneonstudios.github.io/nexus-utilities/apidocs/com/zyneonstudios/nexus/utilities/sql/SQLite)
- [Storage](https://zyneonstudios.github.io/nexus-utilities/apidocs/com/zyneonstudios/nexus/utilities/storage/package-summary)
  - [JsonStorage: Saving and reading stuff in a json file](https://zyneonstudios.github.io/nexus-utilities/apidocs/com/zyneonstudios/nexus/utilities/storage/JsonStorage)
  - [LocalStorage: Saving and reading stuff in local cache (gone after session)](https://zyneonstudios.github.io/nexus-utilities/apidocs/com/zyneonstudios/nexus/utilities/storage/LocalStorage)
  - [SQLStorage: Saving and reading stuff in a (sql based) database](https://zyneonstudios.github.io/nexus-utilities/apidocs/com/zyneonstudios/nexus/utilities/storage/SQLStorage)
  - [Storage (Interface)](https://zyneonstudios.github.io/nexus-utilities/apidocs/com/zyneonstudios/nexus/utilities/storage/Storage)
- [Strings](https://zyneonstudios.github.io/nexus-utilities/apidocs/com/zyneonstudios/nexus/utilities/strings/package-summary)
  - [StringConverter: Convert strings](https://zyneonstudios.github.io/nexus-utilities/apidocs/com/zyneonstudios/nexus/utilities/strings/StringConverter)
  - [StringGenerator: Generate strings](https://zyneonstudios.github.io/nexus-utilities/apidocs/com/zyneonstudios/nexus/utilities/strings/StringGenerator)
- [System](https://zyneonstudios.github.io/nexus-utilities/apidocs/com/zyneonstudios/nexus/utilities/system/package-summary)
  - [OperatingSystem: Get OS information](https://zyneonstudios.github.io/nexus-utilities/apidocs/com/zyneonstudios/nexus/utilities/system/OperatingSystem)

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