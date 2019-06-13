package com.example.aqs_new.Chain_of_responsibility;

import com.example.aqs_new.datarecord.DataRecord;
import com.example.aqs_new.datarecord.DataRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ByHumidity extends Search {
    @Autowired
    DataRecordRepository dataRecordRepository;
    Search search;

    @Override
    public Iterable<DataRecord> SearchBy()
    {
        return dataRecordRepository.findAllByHumidityGreaterThanEqual(10.2);
    }

    @Override
    public void handler()
    {
         search  = new ByHumidity();
        Iterable<DataRecord>  record = search.SearchBy();
        if (record.equals(0))
        {
            search  = new by_temp();
            search.handler();

        }

    }

}
