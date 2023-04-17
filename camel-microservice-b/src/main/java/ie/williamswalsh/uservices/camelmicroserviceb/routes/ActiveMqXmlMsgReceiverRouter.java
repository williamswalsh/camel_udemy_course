package ie.williamswalsh.uservices.camelmicroserviceb.routes;

import ie.williamswalsh.uservices.camelmicroserviceb.models.CurrencyExchange;
import ie.williamswalsh.uservices.camelmicroserviceb.processors.MyCurrencyExchangeProcessor;
import ie.williamswalsh.uservices.camelmicroserviceb.transformers.MyCurrencyExchangeTransformer;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActiveMqXmlMsgReceiverRouter extends RouteBuilder {

    @Autowired
    private MyCurrencyExchangeProcessor myCurrencyExchangeProcessor;

    @Autowired
    private MyCurrencyExchangeTransformer myCurrencyExchangeTransformer;

    //    Receives messages all at once no period between each consumption.
    @Override
    public void configure() throws Exception {

        from("activemq:my-activemq-xml-queue")
                .log("${body}")
//                Unmarshall message: convert XML to CurrencyExchange Object instance.
                .unmarshal()
                .jacksonXml(CurrencyExchange.class)
                .to("log:received-msg");
    }
}

// 2023-04-16 14:12:06.270  INFO 14606 --- [activemq-queue]] received-msg:
// Exchange[ExchangePattern: InOnly, BodyType: byte[],
// Body: {  "id": 1000,  "from": "USD",  "to": "INR",  "conversionMultiple": 70}]

