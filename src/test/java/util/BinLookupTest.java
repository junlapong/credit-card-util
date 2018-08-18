package util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.junit.Ignore;
import org.junit.Test;

public class BinLookupTest {
	
	private static final Logger logger = LoggerFactory.getLogger(BinLookupTest.class);
	
	@Test
	public void shouldLookupBin() {

		AppConfig config = new AppConfig("config.properties");

		BinLookup bin = new BinLookup(config.getString("user-id"), config.getString("api-key"));
		bin.check("455205", "171.5.248.213");
		//bin.checkIp("171.5.248.213");
	}
}
