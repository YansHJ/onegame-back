package cn.yans.onegame.service.Impl;

import cn.yans.onegame.common.utils.CardCacheUtils;
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
    @Autowired
    private CardCacheUtils cardCacheUtils;

    /**
     * 角色背包抽卡
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

    /**
     * 卡池抽卡
     */
    @Override
    public List<BaseCard> drawBaseCard(int quantity) {
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

    @Override
    public List<BaseCard> getPkgCardFromCache(int quantity, PlayerRole role) {
        if (null == cardCacheUtils.getRoleCard(role.getId())){
            List<BaseCard> baseCard = getBaseCard(quantity, role);
            cardCacheUtils.setRoleCard(baseCard,role.getId());
        }
        return cardCacheUtils.getRoleCard(role.getId());
    }

    @Override
    public List<BaseCard> getDrawCardFromCache(int quantity,String roleId) {
        if (null == cardCacheUtils.getDrawCard(roleId)){
            List<BaseCard> baseCards = drawBaseCard(quantity);
            cardCacheUtils.setDrawCard(baseCards,roleId);
        }
        return cardCacheUtils.getDrawCard(roleId);
    }

    @Override
    public void deleteCardCache(String roleId) {
        cardCacheUtils.deleteDrawCard(roleId);
        cardCacheUtils.deleteRoleCard(roleId);
    }
}
