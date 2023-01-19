package cn.kgc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * TODO
 *
 * @auth dacai
 * @date 2020/7/10 21:12
 */
@SpringBootApplication
@MapperScan("cn.kgc.mapper")
public class HelloApplication {
    public static void main(String[] args) {
        SpringApplication.run(HelloApplication.class);
    }
}
