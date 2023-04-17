package ie.williamswalsh.uservices.camelmicroservicea.routes.c;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class AddMsgToKafkaRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
//        Send file to kafka
        from("file:files/json")
                .log("${body}")
                .to("kafka:my-kafka-topic");
    }
}
