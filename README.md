![Image](https://github.com/nisrulz/packagehunter/blob/master/img/github_banner.png)


### Specs
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.nisrulz/packagehunter/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.nisrulz/packagehunter)

### Featured in
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-PackageHunter-green.svg?style=true)](https://android-arsenal.com/details/1/3815) [![AndroidDev Digest](https://img.shields.io/badge/AndroidDev%20Digest-%23101-blue.svg)](https://www.androiddevdigest.com/digest-101/)

### Show some :heart:
[![GitHub stars](https://img.shields.io/github/stars/nisrulz/packagehunter.svg?style=social&label=Star)](https://github.com/nisrulz/packagehunter) [![GitHub forks](https://img.shields.io/github/forks/nisrulz/packagehunter.svg?style=social&label=Fork)](https://github.com/nisrulz/packagehunter/fork) [![GitHub watchers](https://img.shields.io/github/watchers/nisrulz/packagehunter.svg?style=social&label=Watch)](https://github.com/nisrulz/packagehunter) [![GitHub followers](https://img.shields.io/github/followers/nisrulz.svg?style=social&label=Follow)](https://github.com/nisrulz)  
[![Twitter Follow](https://img.shields.io/twitter/follow/nisrulz.svg?style=social)](https://twitter.com/nisrulz) 


Android library to hunt down package information.

The library is built for simplicity and approachability. It not only eliminates most boilerplate code for dealing with package information, but also provides an easy and simple API to retrieve them and associated data.

### Screenshot of sample app
![Sc1](https://github.com/nisrulz/packagehunter/blob/master/img/sc1.png) ![Sc2](https://github.com/nisrulz/packagehunter/blob/master/img/sc2.png)

# Changelog

Starting with `1.1.0`, Changes exist in the [releases tab](https://github.com/nisrulz/packagehunter/releases).

# Get information for 

+[Specific Package](https://github.com/nisrulz/packagehunter/wiki/Usage#specific-package)
+[All Packages in Device](https://github.com/nisrulz/packagehunter/wiki/Usage#all-packages-in-device)
+[Search for a Package](https://github.com/nisrulz/packagehunter/wiki/Usage#search-for-a-package)


# Including in your project
- PackageHunter is available in the MavenCentral, so getting it as simple as adding it as a dependency
```gradle
compile 'com.github.nisrulz:packagehunter:1.1.0'
```

# Simple example

Create an instance of `PackageHunter`
```java
PackageHunter packageHunter = new PackageHunter(context);
```
Next call an available function on the ***packageHunter*** instance such as
```java
String appName= packageHunter.getAppNameForPkg(packageName);
```

### <center> :page_with_curl: For more info , check the **[Wiki Docs](https://github.com/nisrulz/packagehunter/wiki/Usage)** </center>

# Pull Requests
I welcome and encourage all pull requests. It usually will take me within 24-48 hours to respond to any issue or request. Here are some basic rules to follow to ensure timely addition of your request:
  1. Match coding style (braces, spacing, etc.) This is best achieved using `CMD`+`Option`+`L` (Reformat code) on Mac (not sure for Windows) with Android Studio defaults.
  2. If its a feature, bugfix, or anything please only change code to what you specify.
  3. Please keep PR titles easy to read and descriptive of changes, this will make them easier to merge :)
  4. Pull requests _must_ be made against `develop` branch. Any other branch (unless specified by the maintainers) will get rejected.
  5. Check for existing [issues](https://github.com/nisrulz/packagehunter/issues) first, before filing an issue.  
  6. Have fun!

### Created & Maintained By
[Nishant Srivastava](https://github.com/nisrulz) ([@nisrulz](https://www.twitter.com/nisrulz))
