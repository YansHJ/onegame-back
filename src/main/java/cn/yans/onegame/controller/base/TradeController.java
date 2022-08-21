package cn.yans.onegame.controller.base;


import cn.yans.onegame.common.enumpkg.RespData;
import cn.yans.onegame.entity.PlayerRole;
import cn.yans.onegame.service.TradeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trade")
public class TradeController {

    @Autowired
    private TradeService tradeService;

    @GetMapping("/decrease")
    public RespData<?> buy(@RequestParam("roleId") String roleId,@RequestParam("cardId") String cardId){
        if (StringUtils.isBlank(roleId)){
            return new RespData<>().fail("参数有误");
        }
        PlayerRole role = tradeService.buy(roleId, cardId);
        if (null == role){
            return new RespData<>().fail("余额不足");
        }
        return new RespData<>(role);
    }
}
