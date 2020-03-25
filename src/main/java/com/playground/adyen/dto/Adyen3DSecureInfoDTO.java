package com.playground.adyen.dto;

import lombok.Data;

@Data
public class Adyen3DSecureInfoDTO {

    private String shopperIp;
    private String userAgent;
    private String acceptHeader;
    private String language;
    private Integer colorDepth;
    private Integer screenHeight;
    private Integer screenWidth;
    private Integer timeZoneOffset;
    private Boolean javaEnabled;
}
