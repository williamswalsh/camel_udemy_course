package ie.williamswalsh.uservices.camelmicroservicea.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class RestApiConsumeRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        restConfiguration()
                .host("localhost").port(8000);

//        timer triggers a rest API to an API endpoint on uServiceB
        from("timer:rest-api-consumer/period=10000")
                .setHeader("from", () -> "EUR")
                .setHeader("to", () -> "INR")
                .log("${body}")
                .to("rest:get:/currency-exchange/from/{from}/to/{to}")
                // header values passed into URL values {from} & {to}
                .log("${body}");
    }
}
