package cn.yans.onegame.service.Impl;

import cn.yans.onegame.common.utils.ProbabilityUtils;
import cn.yans.onegame.dao.mapper.CardMapper;
import cn.yans.onegame.entity.BaseCard;
import cn.yans.onegame.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.List;

@Service
public class CardServiceImpl implements CardService {
    @Autowired
    private CardMapper cardMapper;
    @Override
    public List<BaseCard> getBaseCard(int quantity) {
        StopWatch sw = new StopWatch();
        sw.start("getCard");
        int a = 0;
        int b = 0;
        int c = 0;
        int d = 0;
        List<BaseCard> cardList = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            BaseCard oneCard;
            System.out.println("=========");
            do {
                int probability = ProbabilityUtils.getProbability();
                System.out.println("probability = " + (probability));
                oneCard = cardMapper.getOne(probability);
            }while (null == oneCard);
            if (oneCard.getName().equals("普攻")){
                ++a;
            }else if (oneCard.getName().equals("飞刀")){
                ++b;
            }else if (oneCard.getName().equals("治疗术")){
                ++c;
            }else if (oneCard.getName().equals("毁天灭地")){
                ++d;
            }
            cardList.add(oneCard);
        }
        System.out.println("普攻:" + a);
        System.out.println("飞刀:" + b);
        System.out.println("治疗术:" + c);
        System.out.println("大招:" + d);
        sw.stop();
        System.out.println("用时毫秒：" + sw.getTotalTimeMillis());
        System.out.println("用时秒：" + sw.getTotalTimeSeconds());
        System.out.println(sw.prettyPrint());
        return cardList;
    }
}
