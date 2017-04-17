package sk.tnet.message;

import java.awt.Image;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import sk.tnet.display.MainFrame;
import sk.tnet.rest.client.ImageClient;

@Component
public class MessageProcessor implements Processor {

    @Autowired
    private MainFrame mainFrame;

    @Autowired
    private ImageClient imageClient;

    private String lastNumber = null;

    @Override
	public void process(Exchange exchange) throws Exception {
		Message camelMessage = exchange.getIn();
		
		byte[] body = (byte[]) camelMessage.getBody();
		String payload = new String(body, "utf-8");

		if (lastNumber == null || !lastNumber.equals(payload)) {
		    lastNumber = payload;
		    Image image = imageClient.imageForNumber(payload);
		    mainFrame.drawImage(image);
		}
	}

}
