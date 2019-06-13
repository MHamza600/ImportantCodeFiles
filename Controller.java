package com.example.aqs_new.Chain_of_responsibility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@org.springframework.stereotype.Controller
@RequestMapping(value = {"/", "aqs"})
public class Controller {

    @Autowired
    Search search;



    @RequestMapping(value = {"/Res"}, method = RequestMethod.GET)
    public @ResponseBody
    void Get() {
        search = new ByHumidity();
        search.handler();


            }

}
