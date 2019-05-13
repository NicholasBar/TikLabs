/*
 * Copyright 2019 Google LLC.
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package buildsrc

object Versions {
    val ktlint = "0.29.0"
}

object Libs {
    val androidGradlePlugin = "com.android.tools.build:gradle:3.3.0"
    val dexcountGradlePlugin = "com.getkeepsafe.dexcount:dexcount-gradle-plugin:0.8.5"
    val playPublisherPlugin = "com.github.triplet.gradle:play-publisher:2.0.0"

    val mvRx = "com.airbnb.android:mvrx:0.7.2"

    val threeTenBp = "org.threeten:threetenbp:1.3.8"
    val threeTenBpNoTzdb = "org.threeten:threetenbp:1.3.8:no-tzdb"
    val threeTenAbp = "com.jakewharton.threetenabp:threetenabp:1.1.1"

    val gravitySnapHelper = "com.github.rubensousa:gravitysnaphelper:2.0"

    val rxLint = "nl.littlerobots.rxlint:rxlint:1.7.2"

    val timber = "com.jakewharton.timber:timber:4.7.1"

    val tmdbJava = "com.uwetrottmann.tmdb2:tmdb-java:1.10.1"

    val appauth = "net.openid:appauth:0.7.1"

    val junit = "junit:junit:4.12"

    val gson = "com.google.code.gson:gson:2.8.1"

    val jsoup = "org.jsoup:jsoup:1.11.3"

    val videoManager = "com.github.danylovolokh:video-player-manager:0.2.0"
    val listVisibility = "com.github.danylovolokh:list-visibility-utils:0.2.0"

    val androidAnnotations = "com.android.support:support-annotations:21.0.3"

    val materialSpinner = "com.jaredrummler:material-spinner:1.3.1"

    val matisse = "com.zhihu.android:matisse:0.5.1"

    val rxPermission = "com.github.tbruyelle:rxpermissions:0.10.2"
    val rxPreference = "com.f2prateek.rx.preferences2:rx-preferences:2.0.0"

    val commonIO = "org.apache.commons:commons-io:1.3.2"

    val assertj = "org.assertj:assertj-core:3.8.0"
    val robolectric = "org.robolectric:robolectric:4.1"
    val mockitoKotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:2.1.0"

    val exoPlayer = "com.google.android.exoplayer:exoplayer:2.10.0"

    val lottie = "com.airbnb.android:lottie:3.0.1"

    object Toro {
        private const val version = "3.6.2.2804"
        val toroCore = "im.ene.toro3:toro:$version"
        val toroExoPlayer = "im.ene.toro3:toro-ext-exoplayer:$version"
    }

    object Google {
        val material = "com.google.android.material:material:1.1.0-alpha02"
        val firebaseCore = "com.google.firebase:firebase-core:16.0.4"
        val crashlytics = "com.crashlytics.sdk.android:crashlytics:2.9.8"
        val gmsGoogleServices = "com.google.gms:google-services:4.2.0"
        val fabricPlugin = "io.fabric.tools:gradle:1.27.0"
    }

    object Kotlin {
        private const val version = "1.3.20"
        val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$version"
        val reflect = "org.jetbrains.kotlin:kotlin-reflect:$version"
        val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
        val extensions = "org.jetbrains.kotlin:kotlin-android-extensions:$version"
        val kotlinJunit = "org.jetbrains.kotlin:kotlin-test-junit:$version"
    }

    object Coroutines {
        private const val version = "1.1.0"
        val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
        val rx2 = "org.jetbrains.kotlinx:kotlinx-coroutines-rx2:$version"
        val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
    }

    object AndroidX {
        val appcompat = "androidx.appcompat:appcompat:1.0.2"
        val browser = "androidx.browser:browser:1.0.0"
        val palette = "androidx.palette:palette:1.0.0"
        val recyclerview = "androidx.recyclerview:recyclerview:1.0.0"
        val emoji = "androidx.emoji:emoji:1.0.0"
        val fragment = "androidx.fragment:fragment:1.1.0-alpha07"
        val navigation = "androidx.navigation:navigation-fragment-ktx:2.1.0-alpha03"
        val navigationktx = "androidx.navigation:navigation-ui-ktx:2.1.0-alpha03"

        object Test {
            val core = "androidx.test:core:1.1.0"
            val runner = "androidx.test:runner:1.1.1"
            val rules = "androidx.test:rules:1.1.1"

            val espressoCore = "androidx.test.espresso:espresso-core:3.1.1"
        }

        val archCoreTesting = "androidx.arch.core:core-testing:2.0.0"

        object Paging {
            private const val version = "2.0.0"
            val common = "androidx.paging:paging-common:$version"
            val runtime = "androidx.paging:paging-runtime:$version"
            val rxjava2 = "androidx.paging:paging-rxjava2:$version"
        }

        val preference = "androidx.preference:preference:1.1.0-alpha02"

        val constraintlayout = "androidx.constraintlayout:constraintlayout:2.0.0-alpha3"

        val coreKtx = "androidx.core:core-ktx:1.0.1"

        object Lifecycle {
            private const val version = "2.0.0"
            val extensions = "androidx.lifecycle:lifecycle-extensions:$version"
            val reactive = "androidx.lifecycle:lifecycle-reactivestreams:$version"
            val compiler = "androidx.lifecycle:lifecycle-compiler:$version"
            val runtime = "androidx.lifecycle:lifecycle-runtime:$version"
        }

        object Room {
            private const val version = "2.0.0"
            val common = "androidx.room:room-common:$version"
            val runtime = "androidx.room:room-runtime:$version"
            val rxjava2 = "androidx.room:room-rxjava2:$version"
            val compiler = "androidx.room:room-compiler:$version"
            val test = "android.arch.persistence.room:testing:$version"
        }

        object Work {
            private const val version = "1.0.0-beta01"
            val runtimeKtx = "android.arch.work:work-runtime-ktx:$version"
        }
    }

    object RxJava {
        val rxJava = "io.reactivex.rxjava2:rxjava:2.2.5"
        val rxKotlin = "io.reactivex.rxjava2:rxkotlin:2.3.0"
        val rxAndroid = "io.reactivex.rxjava2:rxandroid:2.1.0"
    }

    object Dagger {
        private const val version = "2.21"
        val dagger = "com.google.dagger:dagger:$version"
        val androidSupport = "com.google.dagger:dagger-android-support:$version"
        val compiler = "com.google.dagger:dagger-compiler:$version"
        val androidProcessor = "com.google.dagger:dagger-android-processor:$version"
    }

    object Glide {
        private const val version = "4.9.0"
        val glide = "com.github.bumptech.glide:glide:$version"
        val compiler = "com.github.bumptech.glide:compiler:$version"
        val glideRecycler = "com.github.bumptech.glide:recyclerview-integration:$version"
    }

    object Retrofit {
        private const val version = "2.5.0"
        val retrofit = "com.squareup.retrofit2:retrofit:$version"
        val retrofit_rxjava_adapter = "com.squareup.retrofit2:adapter-rxjava2:$version"
        val gsonConverter = "com.squareup.retrofit2:converter-gson:$version"
        val moshiConverter = "com.squareup.retrofit2:converter-moshi:$version"
        val moshiKotlin = "com.squareup.moshi:moshi-kotlin:1.8.0"
        val moshiAdapter = "com.squareup.moshi:moshi-adapters:1.8.0"
    }

    object OkHttp {
        private const val version = "3.14.1"
        val okHttp = "com.squareup.okhttp3:okhttp:$version"
        val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:$version"
    }

    object Epoxy {
        private const val version = "3.1.0"
        val epoxy = "com.airbnb.android:epoxy:$version"
        val paging = "com.airbnb.android:epoxy-paging:$version"
        val dataBinding = "com.airbnb.android:epoxy-databinding:$version"
        val processor = "com.airbnb.android:epoxy-processor:$version"
    }

    object AssistedInject {
        private const val version = "0.3.2"
        val annotationDagger2 = "com.squareup.inject:assisted-inject-annotations-dagger2:$version"
        val processorDagger2 = "com.squareup.inject:assisted-inject-processor-dagger2:$version"
    }

    object JavaX {
        private const val version = "1"
        val javaxAnnotation = "javax.annotation:jsr250-api:1.0"
        val javaxInject = "javax.inject:javax.inject:1"
    }
}
