package cn.yans.onegame.service;

import cn.yans.onegame.entity.BaseCard;

import java.util.List;

public interface CardService {

    List<BaseCard> getBaseCard(int quantity);
}
