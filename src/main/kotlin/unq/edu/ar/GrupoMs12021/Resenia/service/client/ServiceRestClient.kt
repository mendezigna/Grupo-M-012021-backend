package unq.edu.ar.GrupoMs12021.Resenia.service.client

import org.apache.http.HttpResponse
import org.apache.http.client.config.RequestConfig
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.CloseableHttpClient
import org.apache.http.impl.client.HttpClients
import org.springframework.stereotype.Component
import java.lang.RuntimeException

@Component
class ServiceRestClient() {

    fun getResponsehttp(endpoint: String?): HttpResponse {
        var httpclient: CloseableHttpClient? = null
        try {
            val requestConfig = RequestConfig.custom()
                    .setConnectTimeout(3000).setSocketTimeout(3000)
                    .build()

            httpclient = HttpClients.custom()
                    .setDefaultRequestConfig(requestConfig)
                    .build()

            val httpget = HttpGet(endpoint)

            return httpclient.execute(httpget)
        } catch (e: Exception) {
            httpclient!!.close()
            e.printStackTrace()
            throw RuntimeException(e)
        }
    }

}