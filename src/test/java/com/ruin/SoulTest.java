package com.ruin;

import com.ruin.domain.Soul;
import com.ruin.service.SoulService;
import com.ruin.util.SoulUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author ruin
 * @date 2020/2/8-16:27
 */
@SpringBootTest
public class SoulTest {

    @Autowired
    SoulService soulService;

    @Test
    public void testEnhanceSoul(){
        Soul soul = soulService.findById((long) 46);

        System.out.println(soul);
        System.out.println("---------------------------------");

        Soul soul1= SoulUtil.enhanceSoul(soul,15);
        System.out.println(soul1);
    }
}
