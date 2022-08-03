package cn.yans.onegame.controller.base;

import cn.yans.onegame.common.enumpkg.RespData;
import cn.yans.onegame.common.utils.ProbabilityUtils;
import cn.yans.onegame.entity.BaseCard;
import cn.yans.onegame.entity.Monster;
import cn.yans.onegame.entity.PlayerRole;
import cn.yans.onegame.entity.noindatabase.MonsterSkill;
import cn.yans.onegame.service.BattleService;
import cn.yans.onegame.service.CardService;
import cn.yans.onegame.service.MonsterService;
import cn.yans.onegame.service.PlayerRoleService;
import com.alibaba.fastjson2.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.management.relation.Role;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/battle")
public class BattleController {

    @Autowired
    private PlayerRoleService roleService;
    @Autowired
    private MonsterService monsterService;
    @Autowired
    private CardService cardService;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private BattleService battleService;

    @GetMapping("/attack")
    public RespData<?> roleAttack(@RequestParam("monsterId")String monsterId,
                              @RequestParam("roleId")String roleId,
                              @RequestParam("cardId")String cardId){
        if (StringUtils.isBlank(monsterId) || StringUtils.isBlank(roleId) || StringUtils.isBlank(cardId)){
            return new RespData<>().fail("参数有误");
        }
        PlayerRole role = roleService.getRole(roleId);
        String monsterJson = redisTemplate.opsForValue().get("monster:" + role.getId());
        if (StringUtils.isBlank(monsterJson)){
            return new RespData<>().fail("当前怪物不存在");
        }
        Monster monster = JSON.parseObject(monsterJson, Monster.class);
        BaseCard card = cardService.getById(cardId);
        //TODO 后续引入攻击力增幅概念，目前先基础伤害的加减
        Monster finalMonster = battleService.baseAttack(monster, role, card);
        //打败
        if (null == finalMonster){
            return new RespData<>().defeated();
        }
        return new RespData<>(finalMonster);
    }

    @GetMapping("/underAttack")
    public RespData<?> underAttack(@RequestParam("monsterId")String monsterId,
                                   @RequestParam("roleId")String roleId){
        if (StringUtils.isBlank(monsterId) || StringUtils.isBlank(roleId)){
            return new RespData<>().fail("参数有误");
        }
        //取缓存
        String roleJson = redisTemplate.opsForValue().get("role:" + roleId);
        String monsterJson = redisTemplate.opsForValue().get("monster:" + roleId);
        if (StringUtils.isBlank(monsterJson) || StringUtils.isBlank(roleJson)){
            return new RespData<>().fail("当前实体缓存不存在");
        }
        Monster monster = JSON.parseObject(monsterJson, Monster.class);
        PlayerRole role = JSON.parseObject(roleJson, PlayerRole.class);
        Map<String, Object> resultMap = battleService.underAttack(monster, role);
        return new RespData<>(resultMap);
    }

}
