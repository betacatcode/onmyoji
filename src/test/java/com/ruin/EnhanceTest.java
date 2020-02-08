package com.ruin;

import com.ruin.domain.Attribute;
import com.ruin.domain.Soul;
import com.ruin.util.SoulUtil;
import org.junit.jupiter.api.Test;

/**
 * @author ruin
 * @date 2020/2/8-22:07
 */


public class EnhanceTest {

    @Test
    public void doubleSpeedTest(){

        int sum=0;
        boolean flag=true;

        while(flag){
            sum++;
            Soul soul = SoulUtil.initSoul("招财猫", 2);
            if(soul.getAttributes().get(0).getType().equals("速度")==false)
                continue;
            SoulUtil.enhanceSoul(soul,15);
            for(Attribute attribute:soul.getAttributes()){
                if(attribute.getIsMainAttribute()==0){
                    if(attribute.getType().equals("速度")&&attribute.getValue()>=16)
                        flag=false;
                }
            }
        }

        System.out.println(sum);
    }
}
