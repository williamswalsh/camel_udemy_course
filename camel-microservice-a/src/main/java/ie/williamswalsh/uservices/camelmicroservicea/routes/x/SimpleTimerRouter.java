package ie.williamswalsh.uservices.camelmicroservicea.routes.x;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class SimpleTimerRouter extends RouteBuilder {

//    .to() is an optional method chain link.
//    .to("log:XXX") prints the message to the log with endpoint name.
    @Override
    public void configure() {
        from("timer:my-own-timer")
                .log("${body}")
                .transform().constant("My dummy constant string")
                .log("${body}");
//                .to("log:another-arbitrary-endpoint-name");
//                .to("jetty:"); Can I trigger the jetty endpoint from the timer endpoint ??

    }
}
