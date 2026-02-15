package com.iamkarim

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.ApplicationProductFlavor
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.ProductFlavor

enum class FlavorDimension {
    ContentType
}

enum class AppFlavor(val dimension: FlavorDimension, val applicationIdSuffix: String? = null) {
    Dev(FlavorDimension.ContentType, applicationIdSuffix = ".dev"),
    Prod(FlavorDimension.ContentType)
}

fun configureFlavors(
    commonExtension: CommonExtension,
    flavorConfigurationBlock: ProductFlavor.(flavor: AppFlavor) -> Unit = {}
) {
    commonExtension.apply {
        flavorDimensions += FlavorDimension.ContentType.name
        productFlavors.apply {
            AppFlavor.entries.forEach {
                create(it.name.lowercase()) {
                    dimension = it.dimension.name
                    flavorConfigurationBlock(this, it)
                    if (commonExtension is ApplicationExtension && this is ApplicationProductFlavor) {
                        if (it.applicationIdSuffix != null) {
                            applicationIdSuffix = it.applicationIdSuffix
                        }
                    }
                }
            }
        }
    }
}