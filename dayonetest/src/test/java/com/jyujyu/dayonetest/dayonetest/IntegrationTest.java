package com.jyujyu.dayonetest.dayonetest;

import com.redis.testcontainers.RedisContainer;
import jakarta.transaction.Transactional;
import org.junit.Ignore;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.containers.wait.strategy.Wait;

import java.io.File;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Ignore
@SpringBootTest
@Transactional
@ContextConfiguration(initializers = IntegrationTest.IntegrationTestInitializer.class)
public class IntegrationTest {
    static DockerComposeContainer rdbms;
    static RedisContainer redis;

    static {
        //docker-compose에서 정의한 service Name을 입력한다.
        rdbms = new DockerComposeContainer(new File("infra/test/docker-compose.yaml"))
                .withExposedService(
                        "local-db",
                        3306,
                        Wait.forLogMessage(".*ready for connections.*", 1)
                                .withStartupTimeout(Duration.ofSeconds(300))
                )
                .withExposedService(
                        "local-db-migrate",
                        0,
                        Wait.forLogMessage("(.*Successfully applied.*)|(.*Successfully validated.*)", 1)
                                .withStartupTimeout(Duration.ofSeconds(300))
                );

        //MySQL 컨테이너 실행 메서드
        rdbms.start();

        //  사용할 Redis 이미지를 지정
        redis = new RedisContainer(RedisContainer.DEFAULT_IMAGE_NAME.withTag("6"));
        redis.start();


    }

    static class IntegrationTestInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            Map<String, String> properties = new HashMap<>();


            // 생성한 MySQL 컨테이너 접속
            var rdbmsHost = rdbms.getServiceHost("local-db", 3306);
            var rdbmsPort = rdbms.getServicePort("local-db", 3306);

            // properties 설정 값 메모리에 저장
            properties.put("spring.datasource.url", "jdbc:mysql://" + rdbmsHost + ":" + rdbmsPort + "/score");

            // 생성한 redis 컨테이너 접속
            var redisHost = redis.getHost();
            var riedisPort = redis.getFirstMappedPort();


            properties.put("spring.data.redis.host", redisHost);
            properties.put("spring.data.redis.port", riedisPort.toString());

            TestPropertyValues.of(properties)
                    .applyTo(applicationContext);
        }
    }


}