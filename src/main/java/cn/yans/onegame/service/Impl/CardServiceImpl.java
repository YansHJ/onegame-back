package cn.yans.onegame.service.Impl;

import cn.yans.onegame.conmon.utils.ProbabilityUtils;
import cn.yans.onegame.dao.mapper.CardMapper;
import cn.yans.onegame.entity.BaseCard;
import cn.yans.onegame.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardServiceImpl implements CardService {
    @Autowired
    private CardMapper cardMapper;
    @Override
    public List<BaseCard> getBaseCard(int quantity) {
        int probability = ProbabilityUtils.getProbability();
        return null;
    }
}
