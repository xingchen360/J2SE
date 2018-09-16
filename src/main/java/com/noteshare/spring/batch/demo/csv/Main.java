package com.noteshare.spring.batch.demo.csv;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * 
 * @author itnoteshare
 *
 */
public class Main {

    public static void main(String[] args) throws Exception {
        // 加载上下文
        String[] configLocations = {"/spring/springbatch/applicationContext.xml"};
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(configLocations);
        // 获取任务启动器
        JobLauncher jobLauncher = applicationContext.getBean(JobLauncher.class);
        JobRepository jobRepository = applicationContext.getBean(JobRepository.class);
        PlatformTransactionManager transactionManager = applicationContext.getBean(PlatformTransactionManager.class);
        
        
        // 创建reader
        FlatFileItemReader<DeviceCommand> flatFileItemReader = new FlatFileItemReader<DeviceCommand>();
        flatFileItemReader.setResource(new FileSystemResource("src/main/resources/spring/springbatch/batch-data-source.csv"));
        flatFileItemReader.setLineMapper(new HelloLineMapper());
        // 创建processor
        HelloItemProcessor helloItemProcessor = new HelloItemProcessor();
        // 创建writer
        FlatFileItemWriter<DeviceCommand> flatFileItemWriter = new FlatFileItemWriter<>();
        flatFileItemWriter.setResource(new FileSystemResource("src/main/resources/spring/springbatch/batch-data-target.csv"));
        flatFileItemWriter.setLineAggregator(new HelloLineAggregator());
        // 创建Step
        StepBuilderFactory stepBuilderFactory = new StepBuilderFactory(jobRepository, transactionManager);
        /**
         * 合理的使用Chunk机制
			Spring batch在配置Step时采用的是基于Chunk的机制。即每次读取一条数据，再处理一条数据，
			累积到一定数量后再一次性交给writer进行写入操作。这样可以最大化的优化写入效率，整个事务也是基于Chunk来进行。
			当我们在需要将数据写入到文件、数据库中之类的操作时可以适当设置Chunk的值以满足写入效率最大化。
			但有些场景下我们的写入操作其实是调用一个web service或者将消息发送到某个消息队列中，
			那么这些场景下我们就需要设置Chunk的值为1，这样既可以及时的处理写入，
			也不会由于整个Chunk中发生异常后，在重试时出现重复调用服务或者重复发送消息的情况。
         */
        Step step = stepBuilderFactory.get("step")
                          .<DeviceCommand, DeviceCommand>chunk(1)
                          .reader(flatFileItemReader)       // 读操作
                          .processor(helloItemProcessor)    // 处理操作
                          .writer(flatFileItemWriter)       // 写操作
                          .build();
        // 创建Job
        JobBuilderFactory jobBuilderFactory = new JobBuilderFactory(jobRepository);
        Job job = jobBuilderFactory.get("job")
                                   .start(step)
                                   .build();
        // 启动任务
        jobLauncher.run(job, new JobParameters());
        ((ConfigurableApplicationContext)applicationContext).close();
    }
}
