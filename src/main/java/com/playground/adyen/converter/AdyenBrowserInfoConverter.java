package com.playground.adyen.converter;

import com.adyen.model.BrowserInfo;
import com.playground.adyen.dto.Adyen3DSecureInfoDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class AdyenBrowserInfoConverter {

    public BrowserInfo from3DSecureInfo(Adyen3DSecureInfoDTO source) {
        BrowserInfo target = new BrowserInfo();
        BeanUtils.copyProperties(source, target);
        return target;
    }
}
