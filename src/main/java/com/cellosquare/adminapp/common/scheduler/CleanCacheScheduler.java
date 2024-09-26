package main.java.com.cellosquare.adminapp.common.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CleanCacheScheduler {

   // @Scheduled(cron = "* 0/30 * * * ?")
    public void cleanCache(){
        log.info("CleanCacheScheduler执行一次-------------------------------");
//        AwsUtil.CleanCdnCache();
    }
}
