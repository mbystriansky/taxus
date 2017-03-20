package sk.tnet.route;

import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TaxusRouteBuilder extends RouteBuilder {

	@Autowired
	private Processor messageProcessor;

	@Override
	public void configure() throws Exception {
		from("paho://{{taxus.app.mqtt.topic}}").process(messageProcessor).to("log:paho");
	}

}
