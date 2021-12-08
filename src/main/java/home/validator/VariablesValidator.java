package home.validator;

import home.utils.ArgsReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class VariablesValidator {
    private static final Logger logger = LogManager.getLogger(VariablesValidator.class);

    public void validate(int rowsPerPage) {
        if (rowsPerPage <= 0 || rowsPerPage % 1 != 0) {
            logger.error("Variable \"rowsPerPage\" has incorrect value: {}",rowsPerPage);
            throw new RuntimeException("Variable \"rowsPerPage\" has incorrect value: " + rowsPerPage);
        }
    }

    public boolean isContainsArgs(Map<String, String> argsMap) {
        if (argsMap.isEmpty() & !argsMap.containsKey(ArgsReader.ARTIFACT_ID)) {
            logger.error("Can not find Arg \"artifactId\": {}",  argsMap.get(ArgsReader.ARTIFACT_ID));
            throw new RuntimeException("Can not find Arg \"artifactId\": " + argsMap.get(ArgsReader.ARTIFACT_ID));
        }
        return true;
    }
}
