package cn.yans.onegame.controller.base;

import cn.yans.onegame.common.enumpkg.RespData;
import cn.yans.onegame.entity.BaseCard;
import cn.yans.onegame.entity.PlayerRole;
import cn.yans.onegame.service.CardService;
import com.alibaba.fastjson2.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/card")
public class CardController {

    @Autowired
    public CardService cardService;
    @Autowired
    public StringRedisTemplate redisTemplate;

    @GetMapping("/getInitCard")
    public RespData<?> getCard(int quantity,String roleId){
        if (quantity < 0){
            return new RespData<>().fail("参数有误");
        }
        String roleJson = redisTemplate.opsForValue().get("role:" + roleId);
        if (StringUtils.isBlank(roleJson)){
            return new RespData<>().fail("角色不存在");
        }
        PlayerRole role = JSON.parseObject(roleJson, PlayerRole.class);
        List<BaseCard> baseCard = cardService.getBaseCard(quantity,role);
        return new RespData<>(baseCard);
    }

    @GetMapping("/drawCard")
    public RespData<?> drawCard(int quantity){
        if (quantity < 0){
            return new RespData<>().fail("参数有误");
        }
        List<BaseCard> baseCards = cardService.drawCard(quantity);
        return new RespData<>(baseCards);
    }
}
