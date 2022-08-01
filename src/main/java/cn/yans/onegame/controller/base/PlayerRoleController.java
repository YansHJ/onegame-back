package cn.yans.onegame.controller.base;

import cn.yans.onegame.common.enumpkg.RespData;
import cn.yans.onegame.entity.PlayerRole;
import cn.yans.onegame.service.PlayerRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/playerRole")
public class PlayerRoleController {

    @Autowired
    private PlayerRoleService roleService;

    @PostMapping("/initRole")
    public RespData<?> initRole(PlayerRole playerRole){
        PlayerRole role = roleService.initRole(playerRole);
        return new RespData<>(role);
    }


}
