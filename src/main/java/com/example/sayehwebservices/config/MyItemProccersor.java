package com.example.sayehwebservices.config;

import com.example.sayehwebservices.Dto.NationalCodeDto;
import com.example.sayehwebservices.Dto.SmsResultReport;
import com.example.sayehwebservices.services.ResultInquery;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

public class MyItemProccersor implements ItemProcessor<NationalCodeDto, SmsResultReport> {
    @Autowired
    ResultInquery inquery;


    @Override
    public SmsResultReport process(NationalCodeDto nationalcodeAndMessageDto) throws Exception {

        Thread.sleep(150);
        String s = inquery.connectWithNetToMagfaServerAndRequestSmsSend(nationalcodeAndMessageDto.getNationalCode());

        return new SmsResultReport(nationalcodeAndMessageDto.getNationalCode(),  s);
    }
}



