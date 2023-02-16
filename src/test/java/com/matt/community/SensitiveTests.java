package com.matt.community;


import com.matt.community.util.SensitiveFilter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class SensitiveTests {
    @Autowired
    private SensitiveFilter sensitiveFilter;

    @Test
    public void testSensitiveFilter() {
        String text = "这里可赌博，可嫖娼，可吸毒！！！！";
        text = sensitiveFilter.filter(text);
        System.out.println(text);

        text = "这里可△赌△博△，可△嫖△娼△，可△吸△毒△！！！！";
        text = sensitiveFilter.filter(text);
        System.out.println(text);
    }
}
