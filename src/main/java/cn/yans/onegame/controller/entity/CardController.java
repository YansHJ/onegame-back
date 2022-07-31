package cn.yans.onegame.controller.entity;

import cn.yans.onegame.conmon.enumpkg.RespData;
import cn.yans.onegame.entity.BaseCard;
import cn.yans.onegame.service.CardService;
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

    @GetMapping("/getInitCard")
    public RespData<?> getCard(int quantity){
        if (quantity < 0){
            return new RespData<>().fail("参数有误");
        }
        List<BaseCard> baseCard = cardService.getBaseCard(quantity);
        return new RespData<>(baseCard);
    }
}
