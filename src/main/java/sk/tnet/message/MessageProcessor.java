package sk.tnet.message;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import sk.tnet.display.MainFrame;

@Component
public class MessageProcessor implements Processor {

    @Autowired
    private MainFrame mainFrame;

    @Override
	public void process(Exchange exchange) throws Exception {
		Message camelMessage = exchange.getIn();
		
		byte[] body = (byte[]) camelMessage.getBody();
		String payload = new String(body, "utf-8");

		mainFrame.drawURL(payload);
	}

}
