package cn.yans.onegame.controller.base;

import cn.yans.onegame.common.enumpkg.RespData;
import cn.yans.onegame.entity.PlayerRole;
import cn.yans.onegame.service.PlayerRoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/playerRole")
public class PlayerRoleController {

    @Autowired
    private PlayerRoleService roleService;

    @PostMapping("/initRole")
    public RespData<?> initRole(@RequestBody PlayerRole playerRole){
        if (null == playerRole){
            return new RespData<>().fail("参数有误");
        }
        PlayerRole role = roleService.initRole(playerRole);
        return new RespData<>(role);
    }

    @GetMapping("/getRole")
    public RespData<?> getRole(@RequestParam("id") String id){
        if (StringUtils.isBlank(id)){
            return new RespData<>().fail("参数有误");
        }
        PlayerRole role = roleService.getRole(id);
        if (StringUtils.isBlank(role.getId())){
            return new RespData<>().fail("角色不存在");
        }
        return new RespData<>(role);
    }


}
