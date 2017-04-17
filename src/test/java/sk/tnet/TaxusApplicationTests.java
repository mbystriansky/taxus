package sk.tnet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import sk.tnet.display.MainFrame;
import sk.tnet.rest.client.ImageClient;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaxusApplicationTests {
    Logger LOG = LoggerFactory.getLogger(TaxusApplicationTests.class);

    @MockBean
    MainFrame mainFrame;

    @Autowired
    private ImageClient imageClient;

    @Test
	public void contextLoads() {
	}

}
