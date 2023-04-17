package ie.williamswalsh.uservices.camelmicroservicea.routes.c;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class AddMsgToActiveMQRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
//        from("timer:send-msg-to-q-timer?period=10000")
//                .transform().constant("Time now is new msg")
//                .log("${body}")
//                .to("activemq:my-activemq-queue"); // camel will create the queue in activemq automagically

//        Send file to activemq
//        from("file:files/json")
//                .log("${body}")
//                .to("activemq:my-activemq-queue"); // camel will create the queue in activemq automagically

//        Send file to activemq
        from("file:files/xml")
                .log("${body}")
                .to("activemq:my-activemq-xml-queue"); // camel will create the queue in activemq automagically


        // activemq endpoint requires you to import library
        // timer endpoint is built in
    }
}
