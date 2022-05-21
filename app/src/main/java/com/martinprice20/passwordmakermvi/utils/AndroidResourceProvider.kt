package com.martinprice20.passwordmakermvi.utils

import android.content.res.Resources
import com.martinprice20.passwordmakermvi.PMmviApp

class AndroidResourceProvider(val app: PMmviApp) : ResourceProvider {
    override fun getResources(app: PMmviApp): Resources {
        return app.applicationContext.resources
    }

}