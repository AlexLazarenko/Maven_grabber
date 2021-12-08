package home.service;

import java.util.Map;
import java.util.concurrent.ExecutionException;

public interface AsyncSearchDataService {
    String FIND_PAGE_URL="https://%s/solrsearch/select?q=a:\"%s\"+AND+p:\"jar\"&rows=%d&start=%d&wt=json";
    int FIND_NUMFOUND_START_PAGE=0;
    int FIND_NUMFOUND_ROWS_PER_PAGE=0;
    void execute(Map<String, String> argsMap) throws InterruptedException, ExecutionException;
}
