package com.ninjasul.unittesting.service;

import lombok.extern.log4j.Log4j2;

import java.util.Arrays;
import java.util.Optional;
import java.util.OptionalInt;

@Log4j2
public class SomeBusinessImpl {

    SomeDataService someDataService;

    public void setSomeDataService(SomeDataService someDataService) {
        this.someDataService = someDataService;
    }

    public int calculateSum(int[] data) {
        return getSumOfData(data);
    }

    public int calculateSumUsingDataService() {
        return getSumOfData(someDataService.retrieveAllData());
    }

    private int getSumOfData(int[] data) {
       return Arrays.stream(data).reduce(Integer::sum).orElse(0);
    }
}