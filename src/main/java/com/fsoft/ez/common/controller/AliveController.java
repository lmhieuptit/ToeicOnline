package com.fsoft.ez.common.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ヘルスチェック用のハンドラークラスです。
 *
 * @author FPT.DanNT3
 */
@RestController
public class AliveController {

    /** ロガー */
    private static final Log LOGGER = LogFactory.getLog(AliveController.class);

    public AliveController() {
        LOGGER.debug("AliveController() {}");
    }

    /**
     * ヘルスチェック用のハンドラーメソッドです。
     *
     * @return OK
     */
    @GetMapping("/alive")
    public String getAlive() {
        LOGGER.debug("getAlive() {}");

        return "OK";
    }

}
