package cn.yans.onegame.service;

import cn.yans.onegame.entity.BaseCard;
import cn.yans.onegame.entity.PlayerRole;

import java.util.List;

public interface CardService {

    List<BaseCard> getBaseCard(int quantity, PlayerRole role);

    BaseCard getById(String id);

    List<BaseCard> drawCard(int quantity);
}
