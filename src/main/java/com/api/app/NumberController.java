package com.api.app;

import com.api.app.enums.NumberRangeFlag;
import com.api.app.models.ResultNumbersModel;
import com.api.app.operations.RandomNumberOperation;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class NumberController {

    @GetMapping("/number/random")
    public ResultNumbersModel random(@RequestParam(name="middleNumber") double middleNumber) {

        var randomNumberOperation = new RandomNumberOperation();

        var beforeNumber = randomNumberOperation.get(middleNumber, NumberRangeFlag.Before);
        var afterNumber = randomNumberOperation.get(middleNumber, NumberRangeFlag.After);

        return new ResultNumbersModel(beforeNumber, afterNumber);
    }
}