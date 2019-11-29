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

        Fluency fluency = builder.build(
                Arrays.asList(
                        new InetSocketAddress("fluentd1", 24224),
                        new InetSocketAddress("fluentd2", 24224)));

        Map<String, Object> event = new HashMap<>();

        event.put("name", "aaaa");
        event.put("age", 42);
        for (int i = 0; i < 100000; i++) {
            event.put("i", i);
            fluency.emit("foo.bar", event);
            TimeUnit.SECONDS.sleep(2);
        }
        fluency.close();
    }
}
