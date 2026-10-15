package demo2;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import demo2.model.order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        System.setProperty("org.slf4j.simpleLogger.defaultLogLevel", "debug");
        Logger log = LoggerFactory.getLogger(Main.class);

        InputStream file = Main.class.getResourceAsStream("/orders.json");

        ObjectMapper mapper = new ObjectMapper();
        List<order> orders = mapper.readValue(file, new TypeReference<List<order>>() {});

        for (order o : orders) {
            log.debug("Loaded order: {}", o.getId());
        }
    }
}
