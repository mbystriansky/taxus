package sk.tnet.message;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.component.paho.PahoConstants;
import org.springframework.stereotype.Component;

@Component
public class MessageProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		Object header = exchange.getIn().getHeader(PahoConstants.MQTT_TOPIC);
		String topic = (String) header;
		Message camelMessage = exchange.getIn();
		
		byte[] body = (byte[]) camelMessage.getBody();
		String payload = new String(body, "utf-8");
		
//		System.out.println("topic=" + topic + ", payload=" + payload);
	}

}
