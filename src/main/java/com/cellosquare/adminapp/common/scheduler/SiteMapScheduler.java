package main.java.com.cellosquare.adminapp.common.scheduler;

import com.cellosquare.adminapp.common.util.AwsUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Slf4j
public class SiteMapScheduler {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private com.cellosquare.adminapp.admin.apihistory.controller.ApiHistoryController apiHistoryController;

    //1天执行
    @Scheduled(fixedRate = 1000 * 60 * 60 * 24 * 1)
    public void siteMap() {
        //ip-109-247-13-117.cn-north-1.compute.internal
        //ip-109-247-13-155.cn-north-1.compute.internal
        String deviceName = AwsUtil.getDeviceName();
        logger.warn("siteMap getDeviceName is " + deviceName);
        if (Objects.equals("ip-109-247-13-155.cn-north-1.compute.internal", deviceName)) {
            try {
                //执行
                String siteMap = apiHistoryController.siteMap();
                logger.warn("SiteMapScheduler siteMap " + siteMap);
            } catch (Exception e) {
                logger.error("siteMap Exception is", e);
            }
        }
    }
}
