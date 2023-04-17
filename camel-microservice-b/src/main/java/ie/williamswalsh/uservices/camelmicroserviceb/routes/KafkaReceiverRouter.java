package ie.williamswalsh.uservices.camelmicroserviceb.routes;

import ie.williamswalsh.uservices.camelmicroserviceb.models.CurrencyExchange;
import ie.williamswalsh.uservices.camelmicroserviceb.processors.MyCurrencyExchangeProcessor;
import ie.williamswalsh.uservices.camelmicroserviceb.transformers.MyCurrencyExchangeTransformer;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class KafkaReceiverRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        from("kafka:my-kafka-topic")
//                .unmarshal()
//                .jacksonXml(CurrencyExchange.class)
                .to("log:received-msg");
    }
}
// [my-kafka-topic]] org.apache.kafka.clients.NetworkClient:
// [Consumer clientId=consumer-8726bcc9-f6f0-4d58-8ce9-7aa47337f6e9-1, groupId=8726bcc9-f6f0-4d58-8ce9-7aa47337f6e9]
// Error connecting to node 3d8b69a9aa08:9092 (id: 1001 rack: null)
//java.net.UnknownHostException: 3d8b69a9aa08
