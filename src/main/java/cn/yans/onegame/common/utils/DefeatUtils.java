package cn.yans.onegame.common.utils;

import cn.yans.onegame.entity.Monster;
import cn.yans.onegame.entity.PlayerRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 击败缓存处理类
 */
@Component
public class DefeatUtils {
    @Autowired
    private RoleCacheUtils roleCacheUtils;
    @Autowired
    private MonsterCacheUtils monsterCacheUtils;
    @Autowired
    private MapCacheUtils mapCacheUtils;
    @Autowired
    private CardCacheUtils cardCacheUtils;

    /**
     * 打败
     */
    public void defeatMonster(Monster monster, PlayerRole role){
        monsterCacheUtils.deleteMonster(monster,role);
        cardCacheUtils.deleteRoleCard(role.getId());
        cardCacheUtils.deleteDrawCard(role.getId());
    }


    /**
     * 被打败
     */
    public void beDefeated(Monster monster, PlayerRole role){
        roleCacheUtils.deleteRole(role);
        monsterCacheUtils.deleteMonster(monster,role);
        mapCacheUtils.deleteMap(role.getId(), role.getLayerNumber());
        cardCacheUtils.deleteRoleCard(role.getId());
        cardCacheUtils.deleteDrawCard(role.getId());
    }
}
