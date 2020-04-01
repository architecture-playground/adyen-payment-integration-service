package com.playground.adyen.converter;

import com.playground.adyen.dto.AdyenTransactionDTO;
import com.playground.adyen.model.AdyenTransaction;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class AdyenTransactionConverter {

    public AdyenTransactionDTO toDto(AdyenTransaction source) {
        AdyenTransactionDTO target = new AdyenTransactionDTO();
        BeanUtils.copyProperties(source, target);
        return target;
    }
}
