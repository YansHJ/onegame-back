package cn.yans.onegame.service;

import cn.yans.onegame.entity.BaseCard;
import cn.yans.onegame.entity.PlayerRole;

import java.util.List;

public interface CardService {

    List<BaseCard> getBaseCard(int quantity, PlayerRole role);

    BaseCard getById(String id);

    List<BaseCard> drawBaseCard(int quantity);

    List<BaseCard> getPkgCardFromCache(int quantity, PlayerRole role);

    List<BaseCard> getDrawCardFromCache(int quantity, String roleId);

    void deleteCardCache(String roleId);
}
