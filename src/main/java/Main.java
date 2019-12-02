import org.komamitsu.fluency.Fluency;
import org.komamitsu.fluency.fluentd.FluencyBuilderForFluentd;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        Logger logger = LoggerFactory.getLogger(Main.class);
        FluencyBuilderForFluentd builder = new FluencyBuilderForFluentd();

        builder.setErrorHandler(ex -> {
            logger.error("Error handler", ex);
        });

        InetSocketAddress fluentd1 = new InetSocketAddress("fluentd1", 24224);
        InetSocketAddress fluentd2 = new InetSocketAddress("fluentd2", 24224);

        logger.info("fluentd1 hostname = {} {}", fluentd1.getHostName(), fluentd1.getAddress().getHostAddress());
        logger.info("fluentd2 hostname = {} {}", fluentd2.getHostName(), fluentd2.getAddress().getHostAddress());

        for (int i = 0; i < 100000; i++) {
            logger.info("i = " + i);
            TimeUnit.SECONDS.sleep(2);
        }

/*
        Fluency fluency = builder.build(
                Arrays.asList(
                        fluentd1,
                        fluentd2));

        Map<String, Object> event = new HashMap<>();

        event.put("name", "aaaa");
        event.put("age", 42);
        for (int i = 0; i < 100000; i++) {
            event.put("i", i);
            fluency.emit("foo.bar", event);
            TimeUnit.SECONDS.sleep(2);
        }
        fluency.close();
*/
    }
}
