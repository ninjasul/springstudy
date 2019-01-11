package com.ninjasul.unittesting.service;

import com.ninjasul.unittesting.data.SomeDataService;
import lombok.extern.log4j.Log4j2;

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
        int result = 0;
        if( data == null ) {
            data = new int[0];
        }

        for( int num : data ) {
            result += num;
        }

        log.info( "result : {}", result );
        return result;
    }
}