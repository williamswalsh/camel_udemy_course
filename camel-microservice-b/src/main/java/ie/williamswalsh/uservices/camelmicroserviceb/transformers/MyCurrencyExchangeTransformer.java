package ie.williamswalsh.uservices.camelmicroserviceb.transformers;

import ie.williamswalsh.uservices.camelmicroserviceb.models.CurrencyExchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class MyCurrencyExchangeTransformer {

    Logger logger = LoggerFactory.getLogger(ie.williamswalsh.uservices.camelmicroserviceb.processors.MyCurrencyExchangeProcessor.class);

    public CurrencyExchange processMsg(CurrencyExchange currencyExchange) {
        currencyExchange.setConversionMultiple(currencyExchange.getConversionMultiple().multiply(BigDecimal.TEN));

        return currencyExchange;
    }
}

