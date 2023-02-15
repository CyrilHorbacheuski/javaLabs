package com.api.app.operations;

import com.api.app.enums.NumberRangeFlag;
import java.util.Random;
import static java.lang.Double.MAX_VALUE;
import static java.lang.Double.MIN_VALUE;

public class RandomNumberOperation {
    public double get(double middle, NumberRangeFlag flag) {
        var rand = new Random();

        if(flag.equals(NumberRangeFlag.Before))
            return rand.nextDouble(MIN_VALUE, middle);
        else
            return rand.nextDouble(middle, MAX_VALUE);
    }
}
