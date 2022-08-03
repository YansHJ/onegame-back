package cn.yans.onegame.service.Impl;

import cn.yans.onegame.common.utils.ProbabilityUtils;
import cn.yans.onegame.entity.BaseCard;
import cn.yans.onegame.entity.Monster;
import cn.yans.onegame.entity.PlayerAttribute;
import cn.yans.onegame.entity.PlayerRole;
import cn.yans.onegame.entity.noindatabase.MonsterSkill;
import cn.yans.onegame.service.BattleService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class BattleServiceImpl implements BattleService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public Monster baseAttack(Monster monster, PlayerRole role, BaseCard card) {
        Long monsterArmor = monster.getBaseArmor();
        if (monsterArmor - card.getValue() <= 0){
            monster.setBaseHealth((monsterArmor + monster.getBaseHealth()) - card.getValue());
            monster.setBaseArmor(0L);
        }else {
            monster.setBaseArmor(monsterArmor - card.getValue());
        }
        //打败
        if (monster.getBaseHealth() <= 0){
            redisTemplate.delete("monster:" + role.getId());
            return null;
        }
        //更新怪物
        redisTemplate.opsForValue().set("monster:" + role.getId(), JSON.toJSONString(monster),4, TimeUnit.HOURS);
        return monster;
    }

    @Override
    public Map<String,Object> underAttack(Monster monster, PlayerRole role) {
        List<MonsterSkill> skill = monster.getSkill();
        int probability = ProbabilityUtils.getProbability();
        int index = changeMonsterSkill(skill, 0, probability, 0);
        MonsterSkill monsterSkill = skill.get(index);
        PlayerAttribute attribute = role.getAttribute();
        Long baseArmor = attribute.getBaseArmor();
        if (baseArmor - monsterSkill.getValue() <= 0) {
            attribute.setBaseHealth((attribute.getBaseHealth() + baseArmor) - monsterSkill.getValue());
            attribute.setBaseArmor(0L);
        } else {
            attribute.setBaseArmor(baseArmor - monsterSkill.getValue());
        }
        //打败
        if (attribute.getBaseHealth() <= 0) {
            redisTemplate.delete("role:" + role.getId());
            return null;
        }
        role.setAttribute(attribute);
        redisTemplate.opsForValue().set("role:" + role.getId(), JSON.toJSONString(role),7, TimeUnit.DAYS);
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("role",role);
        resultMap.put("monsterSkill",monsterSkill);
        return resultMap;
    }

    /**
     * 获取怪物的此次攻击
     * @param skill 技能组
     * @param last 左边的限定
     * @param probability 随机值
     * @param index index
     * @return 抽中的index
     */
    private int changeMonsterSkill(List<MonsterSkill> skill,int last,int probability,int index){
        MonsterSkill monsterSkill = skill.get(index);
        if (last < probability && probability <= monsterSkill.getProbability()) {
            return index;
        }
        return changeMonsterSkill(skill,monsterSkill.getProbability(),probability,index + 1);
    }
}
