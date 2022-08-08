package cn.yans.onegame.service;

import cn.yans.onegame.entity.BaseCard;
import cn.yans.onegame.entity.PlayerRole;

import java.util.List;

public interface PlayerRoleService {

    PlayerRole initRole(PlayerRole playerRole);

    PlayerRole getRole(String id);

    PlayerRole roleAddCard(String roleId,String cardId);

    PlayerRole updateLayer(String roleId);

    List<BaseCard> getMyCard(String roleId);
}
