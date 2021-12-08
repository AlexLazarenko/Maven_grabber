package home.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ArgsReader {
    private static final Logger logger = LogManager.getLogger(ArgsReader.class);

    public final static String GROUP_ID="groupId";

    public final static String ARTIFACT_ID="artifactId";

    public Map<String, String> readArgs(String[] args) {
        Map<String, String> argsMap = new HashMap<>();
        if (args.length > 0) {  //если через консоль были введены аргументы
            argsMap.put(GROUP_ID, args[1]);
            argsMap.put(ARTIFACT_ID, args[3]);
        } else {  //иначе —
           logger.warn("There is no args!");
           throw new RuntimeException("There is no args");
        }
        return argsMap;
    }
}
