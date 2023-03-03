package com.api.app.operations;

import com.api.app.constants.ValidationErrorConstants;
import com.api.app.enums.NumberRangeFlag;
import com.api.app.models.RequestModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Random;
import static java.lang.Double.MAX_VALUE;

public class RandomNumberOperation {
    public double get(RequestModel model, NumberRangeFlag flag) {

        if(model.getMiddleNumber() == 15.0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ValidationErrorConstants.BadArguments);
        }

        if(model.getMiddleNumber() == 16.0) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ValidationErrorConstants.ServerError);
        }

        var rand = new Random();

        double scale = Math.pow(10, 3);

        if(flag.equals(NumberRangeFlag.Before)) {
            return rand.nextDouble(-MAX_VALUE, model.getMiddleNumber());
        }
        else {
            return rand.nextDouble(model.getMiddleNumber(), MAX_VALUE);
        }
    }
}
