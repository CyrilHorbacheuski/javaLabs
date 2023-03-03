package com.api.app.operations;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
@Component(value="counter")
@Scope("singleton")
public class RequestsCounter {
    private static int countOfRequests;

    public void Add() {
        countOfRequests++;
    }

    public int GetCount() {
        return countOfRequests;
    }
}
