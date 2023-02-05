package com.example.sayehwebservices;

import com.example.sayehwebservices.Dto.SmsResultReport;
import com.example.sayehwebservices.services.ResultInquery;
import org.junit.jupiter.api.Test;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

@SpringBootTest
class SayehWebservicesApplicationTests {
    @Autowired
    ResultInquery inquery;


    @Autowired
    @Qualifier(value = "writer")
    FlatFileItemWriter<SmsResultReport> itemWriter;


//    @Test
//    void contextLoads() {
//    }

//
//    @Test
//    void testResultServiceForSendSMS() throws IOException {
//        inquery.connectWithNetToMagfaServerAndRequestSmsSend();
//    }

    @Test
    void testWriter() throws IOException {
        System.out.println("writer here");

        FlatFileItemWriter<SmsResultReport> writer = new FlatFileItemWriter<SmsResultReport>();
        String path = "./result.csv";
        File file = new File(path);
        FileSystemResource resource = new FileSystemResource(path);
        writer.setResource(resource);
        writer.setAppendAllowed(true);
        writer.setLineAggregator(new DelimitedLineAggregator<>() {
            {
                setDelimiter(",");
                setFieldExtractor(new BeanWrapperFieldExtractor<>() {
                    {
                        setNames(new String[]{"nationalCode", "result"});
                    }
                });
            }
        });


        ArrayList<SmsResultReport> smsResultReports = new ArrayList<>();
        smsResultReports.add(new SmsResultReport("hasan","mmd"));

        writer.doWrite(smsResultReports);
    }

}
