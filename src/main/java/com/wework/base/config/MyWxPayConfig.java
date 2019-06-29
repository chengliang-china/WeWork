package com.wework.base.config;

import com.github.wxpay.sdk.WXPayConfig;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class MyWxPayConfig implements WXPayConfig {

    private byte[] certData;

    public MyWxPayConfig() throws Exception {
            ClassPathResource classPathResource = new ClassPathResource("apiclient_cert.p12");
            //获取文件流
            InputStream certStream = classPathResource.getInputStream();
            this.certData = IOUtils.toByteArray(certStream);
            certStream.read(this.certData);
            certStream.close();

    }

    public String getAppID() {
        return "wxb9e8029dd7ceccc4";
    }

    public String getMchID() {
        return "1540580371";
    }

    public String getKey() {
        return "YZ9GsGB7MskhhYkI1F4wy17P6MT9wICD";
    }

    public InputStream getCertStream() {
        ByteArrayInputStream certBis = new ByteArrayInputStream(this.certData);
        return certBis;
    }

    public int getHttpConnectTimeoutMs() {
        return 8000;
    }

    public int getHttpReadTimeoutMs() {
        return 10000;
    }
}
