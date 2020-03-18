package fun.sherman.log.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {

    @RequestMapping("/log")
    public String getLog() {
        log.debug("# ============测试日志===========");
        log.info("# ============测试日志===========");
        log.warn("# ============测试日志===========");
        log.error("# ============测试日志===========");
        return "success";
    }
}
