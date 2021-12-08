package home.utils;

import home.entity.Docs;
import home.entity.ResponseHeader;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Component
public class ArrayListPrinter {
    public void printFutureMap(List<Future<ResponseHeader>> futureResponsePerPage) throws ExecutionException, InterruptedException {
        for (int i = 0; i < futureResponsePerPage.size(); i++) {
            List<Docs> docs = futureResponsePerPage.get(i).get().getResponse().getDocs();
            System.out.println("page" + i);
            System.out.println(docs);
        }
    }
}
