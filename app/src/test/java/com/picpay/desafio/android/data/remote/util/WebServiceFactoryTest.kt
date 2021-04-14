package com.picpay.desafio.android.data.remote.util

import com.nhaarman.mockitokotlin2.mock
import com.picpay.desafio.android.data.remote.service.PicPayService
import okhttp3.Cache
import org.junit.Assert
import org.junit.Test

class WebServiceFactoryTest {

    private val cache = mock<Cache>()

    @Test
    fun `WHEN create a webFactory SHOULD return not null instance`() {
        val result = WebServiceFactory.createWebService<PicPayService>(
            WebServiceFactory.okHttpClientBuilder(
                cache = cache,
                wasDebugVersion = true
            )
        )

        Assert.assertNotNull(result)
    }
}