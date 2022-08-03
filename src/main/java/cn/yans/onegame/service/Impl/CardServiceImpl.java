package cn.yans.onegame.service.Impl;

import cn.yans.onegame.common.utils.CardUtils;
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
    @Autowired
    private CardUtils cardUtils;

    /**
     * 概率抽卡
     */
    @Override
    public List<BaseCard> getBaseCard(int quantity) {
        StopWatch sw = new StopWatch();
        sw.start("getCard");
        List<BaseCard> cardList = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            BaseCard oneCard;
            System.out.println("=========");
            do {
                int probability = ProbabilityUtils.getProbability();
                System.out.println("probability = " + (probability));
                oneCard = cardMapper.getOne(probability);
            }while (null == oneCard);
            cardList.add(oneCard);
        }
        sw.stop();
        System.out.println("用时毫秒：" + sw.getTotalTimeMillis());
        System.out.println("用时秒：" + sw.getTotalTimeSeconds());
        System.out.println(sw.prettyPrint());
        return cardUtils.initColor(cardList);
    }

    @Override
    public BaseCard getById(String id) {

        return cardMapper.getById(id);
    }
}
