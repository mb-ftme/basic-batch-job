package com.example.sayehwebservices.config;

import com.example.sayehwebservices.Dto.SmsResultReport;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MyItemWriter implements ItemWriter<SmsResultReport> {


    @Override
    public void write(List list) throws Exception {
        System.out.println(list);
        System.out.println("writtinggggggggggggggggggggggggggggggggggggggggggg");




    }
}
