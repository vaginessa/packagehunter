#PackageHunter    [![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.nisrulz/packagehunter/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.nisrulz/packagehunter) [![Twitter](https://img.shields.io/badge/Twitter-@nisrulz-blue.svg?style=flat)](http://twitter.com/nisrulz)
Android library to hunt down package information 

#Integration
- PackageHunter is available in the MavenCentral, so getting it as simple as adding it as a dependency
```gradle
compile 'com.github.nisrulz:packagehunter:1.0.0'
```

#Usage
+ Get a list of all package information by passing in the query text by calling `PackageHunter
.getInstance().getListOfPackages()`
```java
ArrayList<PkgInfo> pkglist = PackageHunter.getInstance().getListOfPackages(getApplicationContext(), querytext);
```

*where*

|argument|type|
|---|---|
|getApplicationContext()|`Context`|
|querytext|`String`|

License
=======

    Copyright 2016 Nishant Srivastava

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
