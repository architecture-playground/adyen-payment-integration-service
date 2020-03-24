package com.playground.adyen.props;

import com.adyen.enums.Environment;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("adyen")
public class AdyenProperties {

    private String apiKey;
    private Environment environment;
    private String liveEndpointUrlPrefix;
    private String merchantAccount;
}
