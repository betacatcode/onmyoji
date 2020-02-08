package com.ruin.util;

import com.ruin.dao.AttributeMapper;
import com.ruin.dao.SoulMapper;
import com.ruin.domain.Attribute;
import com.ruin.domain.Soul;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author ruin
 * @date 2020/2/4-13:40
 */
public class SoulUtil {

    public static Soul initSoul(String name, Integer pos) {
        Random r = new Random();

        Soul soul = new Soul();
        /**
         * 设置御魂名称
         */
        if (name != null)
            soul.setName(name);
        else
            soul.setName(Storage.souls.get(r.nextInt(37)));

        /**
         * 设置位置
         */
        if (pos != null)
            soul.setPosition(pos);
        else
            soul.setPosition(r.nextInt(6) + 1);

        /**
         * 设置主属性
         */
        Attribute mainAttribute = new Attribute();
        mainAttribute.setNum(1);
        mainAttribute.setIsMainAttribute(1);
        pos = soul.getPosition();
        int x = r.nextInt(100);

        switch (pos) {
            case 1:
                mainAttribute.setType("攻击");
                mainAttribute.setValue(81.0);
                break;
            case 2:
                if (x >= 0 && x < 10) {
                    mainAttribute.setType("速度");
                    mainAttribute.setValue(12.0);
                }
                generateOtherMainAttributes(mainAttribute, x);
                break;
            case 3:
                mainAttribute.setType("防御");
                ;
                mainAttribute.setValue(14.0);
                break;
            case 4:
                if (x >= 0 && x < 5) {
                    mainAttribute.setType("效果抵抗");
                    mainAttribute.setValue(10.0);
                } else if (x < 10) {
                    mainAttribute.setType("效果命中");
                    mainAttribute.setValue(10.0);
                }
                generateOtherMainAttributes(mainAttribute, x);
                break;
            case 5:
                mainAttribute.setType("生命");
                mainAttribute.setValue(342.0);
                break;
            case 6:
                if (x >= 0 && x < 5) {
                    mainAttribute.setType("暴击");
                    mainAttribute.setValue(10.0);
                } else if (x < 10) {
                    mainAttribute.setType("暴击伤害");
                    mainAttribute.setValue(14.0);
                }
                generateOtherMainAttributes(mainAttribute, x);
                break;
        }

        soul.getAttributes().add(mainAttribute);
        generateSecondaryAttributes(soul, null);
        return soul;
    }

    /**
     * 设置除了速度 抵抗 命中 暴击 爆伤外的主属性
     */
    public static void generateOtherMainAttributes(Attribute mainAttribute, int x) {
        if (x >= 10 && x < 40) {
            mainAttribute.setType("攻击加成");
            mainAttribute.setValue(10.0);
        }
        if (x >= 40 && x < 70) {
            mainAttribute.setType("防御加成");
            mainAttribute.setValue(10.0);
        }
        if (x >= 70 && x < 100) {
            mainAttribute.setType("生命加成");
            mainAttribute.setValue(10.0);
        }
    }

    /**
     * @param soul
     * @param sum  所需副属性条数 当为null时系统会随机生成2~4条
     */
    public static void generateSecondaryAttributes(Soul soul, Integer sum) {
        Random r = new Random();

        if (sum == null)
            sum = r.nextInt(3) + 2;
        Integer n = 0;
        while (true) {
            int x = r.nextInt(100);
            Attribute attribute = new Attribute();
            attribute.setNum(1);
            attribute.setIsMainAttribute(0);
            if (x >= 0 && x < 9) {
                attribute.setType("攻击");
                attribute.setValue(21 + 6 * r.nextDouble());
            } else if (x < 18) {
                attribute.setType("攻击加成");
                attribute.setValue(2.4 + 0.6 * r.nextDouble());
            } else if (x < 27) {
                attribute.setType("暴击");
                attribute.setValue(2.4 + 0.6 * r.nextDouble());
            } else if (x < 36) {
                attribute.setType("暴击伤害");
                attribute.setValue(3.2 + 0.8 * r.nextDouble());
            } else if (x < 45) {
                attribute.setType("生命");
                attribute.setValue(91 + 23 * r.nextDouble());
            } else if (x < 54) {
                attribute.setType("防御");
                attribute.setValue(4 + r.nextDouble());
            } else if (x < 63) {
                attribute.setType("生命加成");
                attribute.setValue(2.4 + 0.6 * r.nextDouble());
            } else if (x < 72) {
                attribute.setType("防御加成");
                attribute.setValue(2.4 + 0.6 * r.nextDouble());
            } else if (x < 81) {
                attribute.setType("效果抵抗");
                attribute.setValue(3.2 + 0.8 * r.nextDouble());
            } else if (x < 90) {
                attribute.setType("效果命中");
                attribute.setValue(3.2 + 0.8 * r.nextDouble());
            } else {
                attribute.setType("速度");
                attribute.setValue(2.4 + 0.8 * r.nextDouble());
            }

            List<Attribute> attributes = soul.getAttributes();

            /**
             * 查找是否有相同的副属性
             */
            boolean flag = true;
            for (int i = 0; i < attributes.size(); i++) {
                if (attributes.get(i).getIsMainAttribute() == 0 && attributes.get(i).getType().equals(attribute.getType()))
                    flag = false;
            }

            if (flag == true) {
                soul.getAttributes().add(attribute);
                n++;
            }

            if (n == sum)
                break;
        }
    }

    //    强化御魂
    public static Soul enhanceSoul(Soul soul, Integer targetLevel) {

        Random r = new Random();
        int n = (targetLevel - soul.getLevel()) / 3;

        for (int i = 1; i <= n; i++) {
            //        强化主属性
            enhanceAttribute(soul.getAttributes().get(0));

            //        强化副属性
            int x = r.nextInt(100);

//            如果是瘸腿 并且中了0.75概率出新属性
            if (x < 75 && soul.getAttributes().size() < 5) {
//               添加一条新属性
                generateSecondaryAttributes(soul, 1);
            } else {
//                在原有属性上强化
                List<Attribute> attributes = soul.getAttributes();
//                强化概率分布区间
                Double[] scopes = new Double[attributes.size() - 1];

//                强化次数和
                Double sum = 0.0;

                int idx = 0;
                for (Attribute attribute : attributes) {
//                    如果不是主属性
                    if (attribute.getIsMainAttribute() == 0) {
                        scopes[idx] = Double.valueOf(attribute.getNum());
                        idx++;
                        sum += Double.valueOf(attribute.getNum());
                    }
                }

                Double avg = 1.0 / sum;

                for (int j = 0; j < scopes.length; j++) {
                    scopes[j] *= avg;
                }

                for (int j = 1; j < scopes.length; j++) {
                    scopes[j] = scopes[j] + scopes[j - 1];
                }

//                一个强化的随机因子
                Double d = r.nextDouble();

//                被强化中的副属性下标
                int index = 0;
                for (int j = 0; j <= scopes.length - 1; j++) {
                    if (d < scopes[j]) {
                        index = j;
                        break;
                    }
                }
                enhanceAttribute(soul.getAttributes().get(1 + index));
            }

        }

        soul.setLevel(targetLevel);
        return soul;
    }

    public static void enhanceAttribute(Attribute attribute) {
//        先使强化次数+1
        attribute.setNum(attribute.getNum() + 1);
//        如果是主属性
        if (attribute.getIsMainAttribute() == 1) {
//            获取类型
            String type = attribute.getType();

            if (type.equals("攻击")) {
                attribute.setValue(attribute.getValue() + 27 * 3);
            } else if (type.equals("防御")) {
                attribute.setValue(attribute.getValue() + 6 * 3);
            } else if (type.equals("生命")) {
                attribute.setValue(attribute.getValue() + 114 * 3);
            } else if (type.equals("速度")) {
                attribute.setValue(attribute.getValue() + 3 * 3);
            } else if (type.equals("暴击伤害")) {
                attribute.setValue(attribute.getValue() + 5 * 3);
            } else {
                attribute.setValue(attribute.getValue() + 3 * 3);
            }
        } else {
            Random r = new Random();

            String type = attribute.getType();
            if (type.equals("攻击")) {
                attribute.setValue(attribute.getValue() + 21 + 6 * r.nextDouble());
            } else if (type.equals("防御")) {
                attribute.setValue(attribute.getValue() + 4 + r.nextDouble());
            } else if (type.equals("生命")) {
                attribute.setValue(attribute.getValue() + 91 + 23 * r.nextDouble());
            } else if (type.equals("暴击伤害") || type.equals("效果命中") || type.equals("效果抵抗")) {
                attribute.setValue(attribute.getValue() + 3.2 + 0.8 * r.nextDouble());
            } else {
                attribute.setValue(attribute.getValue() + 2.4 + 0.6 * r.nextDouble());
            }

        }
    }

}
