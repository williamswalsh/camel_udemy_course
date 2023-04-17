package ie.williamswalsh.uservices.camelmicroservicea.routes.a;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

// Must add Component annotation
// Can disable route by commenting out the Component annotation - nothing in log
//@Component
public class MyFirstTimerRouter extends RouteBuilder {

    // best practice to create a bean reference instead of hardcoding the getCurrentTimeBean string in the
    // camel code.
    @Autowired
    private GetCurrentTimeBean getCurrentTimeBean;

    @Autowired
    private SimpleLoggingProcessingComponent loggingProcessingComponent;


    @Override
    public void configure() throws Exception {
        // configure is where you create all the routes.
        // timer - endpoint
        // transformation
        // save to log - endpoint

        // starting point of route - from(<ENDPOINT>) - timer endpoint
        // timer/log -> keyword

        // Exchange[ExchangePattern: InOnly, BodyType: null, Body: [Body is null]]
        from("timer:first-timer")
                .log("${body}")

                .transform().constant("My Constant Message")
                .log("${body}")

                // constant created - not variable - same for all logs
//                .transform().constant("Time now is " + LocalDateTime.now())

                // Using spring bean to do transformation - invoked once for each message on first-timer timer.
                // shouldn't use hardcoded reference: getCurrentTimeBean
                // create member
//                .bean("getCurrentTimeBean")

//                Log the msg body
//                .log("${body}")

//                processing - doesn't change the message body
//                transformation - does change the message body


//                If there are more than one method in the bean, you will need to specify which method by passing
//                a second param to the bean() method like this:
                .bean(getCurrentTimeBean, "getCurrentTime")
                .log("${body}")

//                Can use processor by using the method process
                .process(new SimpleLoggingProcessor2())


                .bean(loggingProcessingComponent)
                .log("${body}") // no change in body after a "processing" component

                .to("log:first-timer");
    }
}

@Component
class GetCurrentTimeBean {

    public String getCurrentTime() {
        return "Time now is: " + LocalDateTime.now();
    }
}

@Component
class SimpleLoggingProcessingComponent {

    private Logger logger = LoggerFactory.getLogger(MyFirstTimerRouter.class);
    public void process(String message) {
        logger.info("SimpleLoggingProcessingComponent: {}", message);
    }
}

class SimpleLoggingProcessor2 implements org.apache.camel.Processor {

    private Logger logger = LoggerFactory.getLogger(SimpleLoggingProcessor2.class);

    @Override
    public void process(Exchange exchange) throws Exception {
//        Print exchange?
//        logger.info("SimpleLoggingProcessor - manual instantiation - exchange: {}", exchange);
//        Print message
        logger.info("SimpleLoggingProcessor - manual instantiation - msg: {}", exchange.getMessage().getBody());
    }
}