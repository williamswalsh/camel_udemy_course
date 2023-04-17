package ie.williamswalsh.uservices.camelmicroserviceb.processors;

import ie.williamswalsh.uservices.camelmicroserviceb.models.CurrencyExchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MyCurrencyExchangeProcessor {

    Logger logger = LoggerFactory.getLogger(MyCurrencyExchangeProcessor.class);
    public void processMsg(CurrencyExchange currencyExchange) {
        logger.info("Do some processing with conversionMultiple value: {}", currencyExchange.getConversionMultiple());
    }
}
