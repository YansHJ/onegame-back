package cn.yans.onegame.service;

import cn.yans.onegame.entity.PlayerRole;

public interface TradeService {

    PlayerRole buy(String roleId, String cardId);
}
