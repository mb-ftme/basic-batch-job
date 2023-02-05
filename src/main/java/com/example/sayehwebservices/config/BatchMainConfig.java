package com.example.sayehwebservices.config;


import com.example.sayehwebservices.Dto.NationalCodeDto;
import com.example.sayehwebservices.Dto.SmsResultReport;
import com.example.sayehwebservices.services.ResultInquery;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class BatchMainConfig {
    private final StepBuilderFactory stepBuilderFactory;
    private final JobBuilderFactory jobBuilderFactory;

    public BatchMainConfig(StepBuilderFactory stepBuilderFactory, JobBuilderFactory jobBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
        this.jobBuilderFactory = jobBuilderFactory;
    }


    @Bean
    public FlatFileItemReader<NationalCodeDto> reader() {
        return new FlatFileItemReaderBuilder<NationalCodeDto>()
                .name("nationalCodeReader")
                .resource(new ClassPathResource("./query.csv"))
                .delimited()
                .names(new String[]{ "nationalCode"})
                .fieldSetMapper(new BeanWrapperFieldSetMapper<NationalCodeDto>() {{
                    setTargetType(NationalCodeDto.class);
                }})
                .build();

    }
    @Bean
    public ItemWriter<SmsResultReport> writer() {
        System.out.println("writer here");

        FlatFileItemWriter<SmsResultReport> writer = new FlatFileItemWriter<SmsResultReport>();
        writer.setResource(new FileSystemResource("./result.csv"));
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
        return writer;
    }

    @Bean
    public ItemProcessor<NationalCodeDto,SmsResultReport> processor(ResultInquery inquery){
        return   new MyItemProccersor();

    }


    @Autowired
    ResultInquery inquery;

    @Autowired
    DataSource dataSource;
    @Bean

    public Job job(
    ) {
        Step step = stepBuilderFactory.get("FTM_STEP")
                .<NationalCodeDto, SmsResultReport>chunk(1)
                .reader(reader())
                .processor(processor(inquery))
                .writer(writer())
                .build();

        return jobBuilderFactory.get("FTM_JOB")
                .incrementer(new RunIdIncrementer())
                .start(step)
                .build();

    }




}
