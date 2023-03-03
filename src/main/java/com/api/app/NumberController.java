package com.api.app;

import com.api.app.cache.NumberCache;
import com.api.app.enums.NumberRangeFlag;
import com.api.app.models.CountRequestsModel;
import com.api.app.models.NumbersCollectionModel;
import com.api.app.models.RequestModel;
import com.api.app.models.ResultNumbersModel;
import com.api.app.operations.RandomNumberOperation;
import com.api.app.operations.RequestsCounter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

@RestController
@SpringBootApplication
public class NumberController {
    @Autowired
    NumberCache<Double, ResultNumbersModel> cache;
    @Autowired
    RequestsCounter requestsCounter;
    Lock lock = new ReentrantLock();

    private static final Logger logger = LoggerFactory.getLogger(NumberController.class);
    @GetMapping("/number/random")
    public ResultNumbersModel random(@ModelAttribute RequestModel model) {

            lock.lock();
            requestsCounter.Add();
            lock.unlock();

            var alreadyInCacheValue = cache.Get(model.getMiddleNumber());
            if(alreadyInCacheValue != null) return alreadyInCacheValue;

            var randomNumberOperation = new RandomNumberOperation();

            var beforeNumber = randomNumberOperation.get(model, NumberRangeFlag.Before);
            var afterNumber = randomNumberOperation.get(model, NumberRangeFlag.After);


            var result = new ResultNumbersModel(beforeNumber, afterNumber);

            cache.Push(model.getMiddleNumber(), result);

            return result;
    }

    @GetMapping("/statistic/requestCount")
    public CountRequestsModel statistic() {
        var model = new CountRequestsModel();

        model.countOfRequests = requestsCounter.GetCount();

        return model;
    }

    @PostMapping(value="/number/random/collection", consumes="application/json", produces="application/json")
    public Collection<ResultNumbersModel> randomForCollection(@RequestBody NumbersCollectionModel model)  {
        return model.collection.stream().parallel().map(this::random).collect(Collectors.toList());
    }
}

