package cn.yans.onegame.controller.base;

import cn.yans.onegame.common.enumpkg.RespData;
import cn.yans.onegame.common.utils.RoleCacheUtils;
import cn.yans.onegame.entity.BaseCard;
import cn.yans.onegame.entity.PlayerRole;
import cn.yans.onegame.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private RoleCacheUtils roleCacheUtils;

    @GetMapping("/getInitCard")
    public RespData<?> getCard(int quantity,String roleId){
        if (quantity < 0){
            return new RespData<>().fail("参数有误");
        }
        PlayerRole role = roleCacheUtils.getRole(roleId);
        if (null == role){
            return new RespData<>().fail("角色不存在");
        }
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
