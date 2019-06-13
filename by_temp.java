package com.example.aqs_new.Chain_of_responsibility;

import com.example.aqs_new.datarecord.DataRecord;
import com.example.aqs_new.datarecord.DataRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class by_temp extends Search{


    @Autowired
    DataRecordRepository dataRecordRepository;
    Search search;
    @Override
    public Iterable<DataRecord> SearchBy()
    {
        return dataRecordRepository.findAllByTemperatureGreaterThanEqual(10.2);
    }

    @Override
    public void handler()
    {
        search  = new by_temp();
        Iterable<DataRecord>  record = search.SearchBy();
        if (record.equals(0))
        {
            search  = new byPm();
            search.handler();
        }



    }






}
