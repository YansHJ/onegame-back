package cn.yans.onegame.service.Impl;

import cn.yans.onegame.common.utils.CardUtils;
import cn.yans.onegame.common.utils.ProbabilityUtils;
import cn.yans.onegame.dao.mapper.CardMapper;
import cn.yans.onegame.entity.BaseCard;
import cn.yans.onegame.entity.PlayerRole;
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
    public List<BaseCard> getBaseCard(int quantity, PlayerRole role) {
        List<String> cards = role.getCards();
        List<BaseCard> cardList = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            BaseCard oneCard;
            do {
                int probability = ProbabilityUtils.getProbability();
                oneCard = cardMapper.getFromPack(probability,cards);
            }while (null == oneCard);
            cardList.add(oneCard);
        }
        return cardUtils.initColor(cardList);
    }

    @Override
    public BaseCard getById(String id) {

        return cardMapper.getById(id);
    }

    @Override
    public List<BaseCard> drawCard(int quantity) {
        List<BaseCard> cardList = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            BaseCard oneCard;
            do {
                int probability = ProbabilityUtils.getProbability();
                oneCard = cardMapper.getFromAll(probability);
            }while (null == oneCard);
            cardList.add(oneCard);
        }
        return cardUtils.initColor(cardList);
    }
}
