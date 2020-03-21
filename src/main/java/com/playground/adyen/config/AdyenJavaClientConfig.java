package com.playground.adyen.config;


import com.adyen.Client;
import com.playground.adyen.props.AdyenProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdyenJavaClientConfig {

    @Bean
    public Client adyenJavaClient(AdyenProperties adyenProps) {
        return new Client(adyenProps.getApiKey(), adyenProps.getEnvironment(), adyenProps.getLiveEndpointUrlPrefix());
    }
}
