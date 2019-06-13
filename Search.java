package com.example.aqs_new.Chain_of_responsibility;

import com.example.aqs_new.datarecord.DataRecord;
import org.springframework.stereotype.Service;

@Service
public abstract class Search {
    public abstract Iterable<DataRecord> SearchBy();
    public abstract void handler();

}
