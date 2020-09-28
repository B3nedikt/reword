[![Download](https://api.bintray.com/packages/b3nedikt/reword/reword/images/download.svg?version=2.0.0)](https://bintray.com/b3nedikt/reword/reword/2.0.0/link)
[![Build Status](https://travis-ci.org/B3nedikt/reword.svg?branch=master)](https://travis-ci.org/B3nedikt/reword)
[![codecov](https://codecov.io/gh/B3nedikt/reword/branch/master/graph/badge.svg)](https://codecov.io/gh/B3nedikt/reword)
[![Documentation](https://img.shields.io/badge/docs-documentation-green.svg)](https://b3nedikt.github.io/reword/)

## Reword 2.0.0

Reword is a library to update the texts of views when the apps texts have
changed due to a language change or an update of the apps string resources with a lib like
[Restring](https://github.com/B3nedikt/restring) or [AppLocale](https://github.com/B3nedikt/AppLocale).

### 1. Add dependency

```groovy
// Intercept view inflation
implementation 'dev.b3nedikt.viewpump:viewpump:3.0.1'

// Allows to update the text of views at runtime without recreating the activity
implementation 'dev.b3nedikt.reword:reword:2.0.0'
```

### 2. Initialize

Initialize Reword in your Application class:

```java
ViewPump.init(ViewPump.builder()
        .addInterceptor(RewordInterceptor.INSTANCE)
        .build()
        );
```

### 3. Update some views

For example update all views in the current activity:

```java
Reword.setLocale(Locales.FRENCH);

// The layout containing the views you want to localize
final View rootView = getWindow().getDecorView().findViewById(android.R.id.content);
Reword.reword(rootView);
```

## License

This library takes some inspiration from the library "restring" originally developed by Hamid Gharehdaghi,
as well as the library "Philology" by JcMinarro.

```
Copyright 2019-present Reword Contributors.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
