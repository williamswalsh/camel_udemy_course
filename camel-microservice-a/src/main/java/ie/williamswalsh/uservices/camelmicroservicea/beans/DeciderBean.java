package ie.williamswalsh.uservices.camelmicroservicea.beans;

import org.apache.camel.Body;
import org.apache.camel.Headers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class DeciderBean {
    Logger logger = LoggerFactory.getLogger(DeciderBean.class);

    // can add @ExchangeProperties Map<String, String> headers
    public boolean isThisConditionMet(@Body String body, @Headers Map<String, String> headers) {
        logger.info("DeciderBean: {}, {}", body, headers);
        return false;   // can add detection stuff here
    }
}
