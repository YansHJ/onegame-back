package cn.yans.onegame.controller.base;

import cn.yans.onegame.common.enumpkg.RespData;
import cn.yans.onegame.entity.AttackResultVO;
import cn.yans.onegame.entity.BaseCard;
import cn.yans.onegame.entity.Monster;
import cn.yans.onegame.entity.PlayerRole;
import cn.yans.onegame.service.*;
import com.alibaba.fastjson2.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    @Autowired
    private SpecialCardService specialCardService;

    @GetMapping("/attack")
    public RespData<?> roleAttack(@RequestParam("monsterId")String monsterId,
                              @RequestParam("roleId")String roleId,
                              @RequestParam("cardId")String cardId){
        if (StringUtils.isBlank(monsterId) || StringUtils.isBlank(roleId) || StringUtils.isBlank(cardId)){
            return new RespData<>().fail("参数有误");
        }
        PlayerRole role = roleService.getRole(roleId);
        String monsterJson = redisTemplate.opsForValue().get("monster:" + roleId + "-" + monsterId);
        if (StringUtils.isBlank(monsterJson)){
            return new RespData<>().fail("当前怪物不存在");
        }
        Monster monster = JSON.parseObject(monsterJson, Monster.class);
        BaseCard card = cardService.getById(cardId);
        //TODO 后续引入攻击力增幅概念，目前先基础伤害的加减
        AttackResultVO attackResultVO = new AttackResultVO();
        if (card.getValueType() == 1){
            //基础卡
            attackResultVO = battleService.baseAttack(monster, role, card);
        }else if (card.getValueType() == 2){
            //特殊效果卡(单独处理逻辑)
            attackResultVO = specialCardService.attackCard(monster, role, card);
        }
        //打败
        if (!StringUtils.isBlank(attackResultVO.getResultCode()) && attackResultVO.getResultCode().equals("666")){
            return new RespData<>().victory(attackResultVO);
        }
        if (!StringUtils.isBlank(attackResultVO.getResultCode()) && attackResultVO.getResultCode().equals("999")){
            return new RespData<>().defeated(attackResultVO);
        }
        return new RespData<>(attackResultVO);
    }

    /**
     * 受到攻击/怪物行动
     */
    @GetMapping("/underAttack")
    public RespData<?> underAttack(@RequestParam("monsterId")String monsterId,
                                   @RequestParam("roleId")String roleId){
        if (StringUtils.isBlank(monsterId) || StringUtils.isBlank(roleId)){
            return new RespData<>().fail("参数有误");
        }
        //取缓存
        String roleJson = redisTemplate.opsForValue().get("role:" + roleId);
        String monsterJson = redisTemplate.opsForValue().get("monster:" + roleId + "-" + monsterId);
        if (StringUtils.isBlank(monsterJson) || StringUtils.isBlank(roleJson)){
            return new RespData<>().fail("当前实体缓存不存在");
        }
        Monster monster = JSON.parseObject(monsterJson, Monster.class);
        PlayerRole role = JSON.parseObject(roleJson, PlayerRole.class);
        Map<String, Object> resultMap = battleService.underAttack(monster, role);
        //被击败
        if (null == resultMap){
            return new RespData<>().defeated(null);
        }
        //"role" => role
        //"monsterSkill" => 使用的技能
        return new RespData<>(resultMap);
    }

    @GetMapping("/heal")
    public RespData<?> getHeal(@RequestParam("roleId")String roleId,
                               @RequestParam("cardId")String cardId){
        if (StringUtils.isBlank(roleId) || StringUtils.isBlank(cardId)){
            return new RespData<>().fail("参数有误");
        }
        PlayerRole role = roleService.getRole(roleId);
        BaseCard card = cardService.getById(cardId);
        PlayerRole healedRole = battleService.getHeal(role, card);
        return new RespData<>(healedRole);
    }

    @GetMapping("/increaseArmor")
    public RespData<?> increaseArmor(@RequestParam("roleId")String roleId,
                                     @RequestParam("cardId")String cardId){
        if (StringUtils.isBlank(roleId) || StringUtils.isBlank(cardId)){
            return new RespData<>().fail("参数有误");
        }
        PlayerRole role = roleService.getRole(roleId);
        BaseCard card = cardService.getById(cardId);
        PlayerRole newRole = battleService.increaseArmor(role, card);
        return new RespData<>(newRole);
    }

}
