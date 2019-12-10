package com.restful.utils;

import org.jasig.cas.client.ssl.HttpURLConnectionFactory;
import org.jasig.cas.client.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.*;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URLConnection;
import java.security.KeyStore;
import java.util.Properties;

/**
 * @ClassName HttpsURLConnectionFactory
 * @Description TODO
 * @Author ljs
 * @Date 2019/12/6 14:18
 **/

public final class HttpsURLConnectionFactory2
        implements HttpURLConnectionFactory
{
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpsURLConnectionFactory2.class);

    private HostnameVerifier hostnameVerifier = HttpsURLConnection.getDefaultHostnameVerifier();

    private Properties sslConfiguration = new Properties();

    public HttpsURLConnectionFactory2() {
    }

    public HttpsURLConnectionFactory2(HostnameVerifier verifier, Properties config) {
        setHostnameVerifier(verifier);
        setSSLConfiguration(config);
    }

    public final void setSSLConfiguration(Properties config) {
        this.sslConfiguration = config;
    }

    public final void setHostnameVerifier(HostnameVerifier verifier)
    {
        this.hostnameVerifier = verifier;
    }

    public HttpURLConnection buildHttpURLConnection(URLConnection url) {
        return configureHttpsConnectionIfNeeded(url);
    }

    private HttpURLConnection configureHttpsConnectionIfNeeded(URLConnection conn)
    {
        if ((conn instanceof HttpsURLConnection)) {
            HttpsURLConnection httpsConnection = (HttpsURLConnection)conn;
            SSLSocketFactory socketFactory = createSSLSocketFactory();
            if (socketFactory != null) {
                httpsConnection.setSSLSocketFactory(socketFactory);
            }

            if (this.hostnameVerifier != null) {
                httpsConnection.setHostnameVerifier(this.hostnameVerifier);
            }
        }
        return (HttpURLConnection)conn;
    }

    private SSLSocketFactory createSSLSocketFactory()
    {
        InputStream keyStoreIS = null;
        InputStream trustStoreIS = null;
        try {
            SSLContext sslContext = SSLContext.getInstance(this.sslConfiguration.getProperty("protocol", "SSL"));

            KeyManager[] km = null;
            TrustManager[] tm = null;

            String ParentDir = Thread.currentThread().getContextClassLoader().getResource("../").getPath();

            if (this.sslConfiguration.getProperty("keyStoreType") != null) {
                KeyStore keyStore = KeyStore.getInstance(this.sslConfiguration.getProperty("keyStoreType"));
                if (this.sslConfiguration.getProperty("keyStorePath") != null) {
                    keyStoreIS = new FileInputStream(ParentDir + this.sslConfiguration.getProperty("keyStorePath"));
                    if (this.sslConfiguration.getProperty("keyStorePass") != null) {
                        keyStore.load(keyStoreIS, this.sslConfiguration.getProperty("keyStorePass").toCharArray());
                        LOGGER.debug("Keystore has {} keys", Integer.valueOf(keyStore.size()));
                        KeyManagerFactory keyManager = KeyManagerFactory.getInstance(this.sslConfiguration
                                .getProperty("keyManagerType", "SunX509"));
                        keyManager.init(keyStore, this.sslConfiguration.getProperty("certificatePassword")
                                .toCharArray());
                        km = keyManager.getKeyManagers();
                    }
                }
            }

            if (this.sslConfiguration.getProperty("trustStoreType") != null) {
                KeyStore trustStore = KeyStore.getInstance(this.sslConfiguration.getProperty("trustStoreType"));
                if (this.sslConfiguration.getProperty("trustStorePath") != null) {
                    trustStoreIS = new FileInputStream(ParentDir + this.sslConfiguration.getProperty("trustStorePath"));
                    if (this.sslConfiguration.getProperty("trustStorePass") != null) {
                        trustStore.load(trustStoreIS, this.sslConfiguration.getProperty("trustStorePass").toCharArray());
                        LOGGER.debug("truststore has {} keys", Integer.valueOf(trustStore.size()));
                        TrustManagerFactory trustManager = TrustManagerFactory.getInstance(this.sslConfiguration
                                .getProperty("trustManagerType", "SunX509"));
                        trustManager.init(trustStore);
                        tm = trustManager.getTrustManagers();
                    }
                }
            }

            sslContext.init(km, tm, null);
            SSLSocketFactory localSSLSocketFactory = sslContext.getSocketFactory();
            return localSSLSocketFactory;
        }
        catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            CommonUtils.closeQuietly(keyStoreIS);
            CommonUtils.closeQuietly(trustStoreIS);
        }

        return null;
    }
}
