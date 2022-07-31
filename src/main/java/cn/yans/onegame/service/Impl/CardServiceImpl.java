package cn.yans.onegame.service.Impl;

import cn.yans.onegame.conmon.utils.ProbabilityUtils;
import cn.yans.onegame.dao.mapper.CardMapper;
import cn.yans.onegame.entity.BaseCard;
import cn.yans.onegame.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CardServiceImpl implements CardService {
    @Autowired
    private CardMapper cardMapper;
    @Override
    public List<BaseCard> getBaseCard(int quantity) {
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
        return cardList;
    }
}
