package com.amanullah.sampletemplate.utils.extensions

import android.content.Context
import androidx.compose.ui.graphics.Color
import com.amanullah.sampletemplate.BuildConfig
import androidx.compose.ui.text.font.FontWeight
import com.google.gson.JsonParser

fun hexToColor(color: String) = Color(android.graphics.Color.parseColor(color))

// Bold Font
//val FontBold by lazy { FontFamily(Font(R.font.sf_pro_bold)) }

// Regular Font
//val FontRegular by lazy { FontFamily(Font(R.font.sf_pro_regular)) }

// Bold Font
val FontWeightBold by lazy { FontWeight(600) }

// Regular Font
val FontWeightRegular by lazy { FontWeight(400) }

/*Check is current build is Release build or not*/
val isReleaseBuild: Boolean = BuildConfig.BUILD_TYPE.equals("release", true)

val isStagingBuild: Boolean = BuildConfig.BUILD_TYPE.equals("staging", true)

val isDebugBuild: Boolean = BuildConfig.BUILD_TYPE.equals("debug", true)

val getVersionCode: String = BuildConfig.VERSION_CODE.toString()

val getVersionName: String = BuildConfig.VERSION_NAME

fun loadJSONFromAsset(context: Context, fileName: String) =
    context.assets.open(fileName).bufferedReader()
        .use(JsonParser::parseReader).asJsonObject.toString()