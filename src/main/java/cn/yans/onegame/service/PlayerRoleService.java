package cn.yans.onegame.service;

import cn.yans.onegame.entity.PlayerRole;

public interface PlayerRoleService {

    PlayerRole initRole(PlayerRole playerRole);

    PlayerRole getRole(String id);
}
