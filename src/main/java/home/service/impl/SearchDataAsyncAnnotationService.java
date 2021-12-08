package home.service.impl;

import home.entity.ResponseHeader;
import home.service.AsyncSearchDataService;
import home.utils.ArgsReader;
import home.utils.ArrayListPrinter;
import home.validator.VariablesValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;


@Service
@ConditionalOnProperty(prefix = "executor", name = "service", havingValue = "disable")
public class SearchDataAsyncAnnotationService implements AsyncSearchDataService {

    RestTemplate restTemplate = new RestTemplate();

    @Value("${source.provider}")
    private String sourceProvider;

    @Value("${rows.page}")
    private int rowsPerPage;

    @Autowired
    VariablesValidator validator;

    @Autowired
    ArrayListPrinter arrayListPrinter;

    public void execute(Map<String, String> argsMap) throws InterruptedException, ExecutionException {
        List<Future<ResponseHeader>> futureResponsePerPage = new ArrayList<>();
        Future<ResponseHeader> response = null;
        if (validator.isContainsArgs(argsMap)) {
            int numFound = findNumFound(argsMap.get(ArgsReader.ARTIFACT_ID));
            if (numFound == 0) {
                System.out.println("Artifact with this id not found.");
            } else {
                System.out.println("numFound=" + numFound);
            }
            validator.validate(rowsPerPage);
            int totalPages = (int) Math.floor(numFound / rowsPerPage) + 1;
            for (int i = 0; i < totalPages; i++) {
                response = findPage(argsMap.get(ArgsReader.ARTIFACT_ID), i);
                futureResponsePerPage.add(response);
            }
            arrayListPrinter.printFutureMap(futureResponsePerPage);
        }
    }

    int findNumFound(String artifactId) {
        String url = String.format(AsyncSearchDataService.FIND_PAGE_URL, sourceProvider, artifactId,
                AsyncSearchDataService.FIND_NUMFOUND_ROWS_PER_PAGE,AsyncSearchDataService.FIND_NUMFOUND_START_PAGE);
        ResponseHeader result = restTemplate.getForObject(url, ResponseHeader.class);
        int numFound = Integer.parseInt(result.getResponse().getNumFound());
        return numFound;
    }

    @Async
    Future<ResponseHeader> findPage(String artifactId, int currentPage) {
        String url = String.format(AsyncSearchDataService.FIND_PAGE_URL, sourceProvider, artifactId, rowsPerPage, currentPage);
        ResponseHeader result = restTemplate.getForObject(url, ResponseHeader.class);
        return new AsyncResult<ResponseHeader>(result);
    }
}



