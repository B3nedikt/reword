[ ![Download](https://api.bintray.com/packages/b3nedikt/reword/restring/images/download.svg?version=3.1.0) ](https://bintray.com/b3nedikt/reword/reword/3.1.0/link)
[![Build Status](https://travis-ci.org/B3nedikt/reword.svg?branch=master)](https://travis-ci.org/B3nedikt/reword)
[![codecov](https://codecov.io/gh/B3nedikt/reword/branch/master/graph/badge.svg)](https://codecov.io/gh/B3nedikt/reword)

## Reword 1.0.0
Reword is a library to update the texts of views when the apps texts have
changed due to a language change or an update of the apps string resources with a lib like
restring.

### 1. Add dependency
```groovy
implementation 'com.b3nedikt.reword:reword:1.0.0'
implementation 'io.github.inflationx:viewpump:2.0.3'
```

### 2. Initialize
Initialize Reword in your Application class:
```java
ViewPump.init(ViewPump.builder()
        .addInterceptor(RewordInterceptor.INSTANCE)
        .build()
        );
```

## Update some views
For example update all views in the current activity:
```java
Restring.setLocale(Locales.FRENCH);

// The layout containing the views you want to localize
final View rootView = getWindow().getDecorView().findViewById(android.R.id.content);
Restring.reword(rootView);
```

## License
This is a fork of a library originally developed by Hamid Gharehdaghi.
Also takes some inspiration from Philology by JcMinarro.
<pre>
Copyright 2018-present Restring Contributors.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
</pre>
