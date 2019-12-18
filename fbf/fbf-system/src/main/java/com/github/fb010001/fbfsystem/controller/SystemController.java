package com.github.fb010001.fbfsystem.controller;

import com.github.fb010001.fbfsystem.controller.models.license.License4Update;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/system")
public class SystemController {

    /**
     * 日志记录器
     */
    Logger logger = LoggerFactory.getLogger(this.getClass());
    private String licenseId;
    private License4Update license4Update;

    /**
     * 获取　系统信息
     * @return
     */
    @ResponseBody
    @GetMapping("/info")
    public String hello(){
        logger.info("hello");
        return "hello";
    }

    /**
     * 获取ｌｉｃｅｎｓｅ信息
     * @return
     */
    @GetMapping("/license")
    public String getLicense(){
        return "license info";
    }

    /**
     * 添加licesense
     * @return
     */
    @PostMapping("/license")
    public String addLicense(){
        return "add license";
    }

    /**
     * 更新license
     */
    @PutMapping("/{licenseId}")
    public String updateLicense(@RequestParam String licenseId, @RequestBody License4Update license4Update){
        this.licenseId = licenseId;
        this.license4Update = license4Update;
        return "update license";
    }

    @DeleteMapping("/license/{licenseId}")
    public String deleteLicense(@RequestParam String licenseId){
        return "delete license";
    }
}
