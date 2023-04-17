package ie.williamswalsh.uservices.camelmicroservicea.routes.b;

import ie.williamswalsh.uservices.camelmicroservicea.beans.DeciderBean;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyFileRouter extends RouteBuilder {

    @Autowired
    private DeciderBean deciderBean;

    @Override
    public void configure() throws Exception {

//        Move file from input folder to output folder
        from("file:files/input")
                .routeId("Files-Input-Route")   // identifier of route - good for debugging problems
//                like a switch case when a -> do x, when b -> do y, etc.
//                .choice()
//                    .when(simple("${file:ext} ends with 'xml'"))
//                        .log("XML FILE")
//                    .otherwise()
//                        .log("NOT AN XML FILE")
//                .end()

//                transforms string to a body - required for contain
                .transform().body(String.class)
                .choice()
                    .when(method(deciderBean))
//                    .when(simple("${body} contains 'USD'"))
                        .log("FILE CONTAINS USD")
                    .otherwise()
                        .log("File doesn't contain USD")
                .end()
//                Can log the message history
//                .log("${messageHistory}")
//                Message History (source location and message history is disabled)
//---------------------------------------------------------------------------------------------------------------------------------------
//Source                                   ID                             Processor                                          Elapsed (ms)
//                                         Files-Input-Route/Files-Input- from[file://files/input]                               28826687
//	...
//                                         Files-Input-Route/log3         log                                                           0
//
//Exchange
//---------------------------------------------------------------------------------------------------------------------------------------
//Exchange[
//	Id                  ECC285CBBA2FD95-0000000000000000
//	ExchangePattern     InOnly
//	Headers             {CamelFileAbsolute=false, CamelFileAbsolutePath=/Users/legoman/code/camel/camel-microservice-a/files/input/1000.json, CamelFileInitialOffset=org.apache.camel.support.resume.Resumables$AnonymousResumable@95e5b6a, CamelFileLastModified=1681647091443, CamelFileLength=76, CamelFileName=1000.json, CamelFileNameConsumed=1000.json, CamelFileNameOnly=1000.json, CamelFileParent=files/input, CamelFilePath=files/input/1000.json, CamelFileRelativePath=1000.json, CamelMessageTimestamp=1681647091443}
//	BodyType            String
//	Body                {  "id": 1000,  "from": "USD",  "to": "INR",  "conversionMultiple": 70}
//]
//                can take specific headers from message history and print their values: false
//                .log("${headers.CamelFileAbsolute}")
//                false

//                NB: References direct endpoint? at end of block
//                .to("direct://log-file-values")
                .to("file:files/output");


//        Re-usable endpoint
//         this route logs data and can be used in multiple places
        from("direct:log-file-values")
                .log("${file:absolute.path}")
                .log("${file:name} ${file:name.ext} ${file:name.noext} ${file:onlyname}")
                .log("${file:onlyname.noext} ${file:parent} ${file:path} ${file:absolute}")
                .log("${file:size} ${file:modified}")
                .log("${routeId} ${camelId} ${body}");

    }
}
// TODO:
// Lookup simple camel document.
// Lookup simple operators camel document.
// simple operators require spaces at either side of the operator
