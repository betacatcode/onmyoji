package com.ruin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author ruin
 * @date 2020/2/4-22:07
 */

@MapperScan("com.ruin.dao")
@SpringBootApplication
public class OnmyojiApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnmyojiApplication.class);
    }
}
