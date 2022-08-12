package cn.yans.onegame.controller.base;


import cn.yans.onegame.common.enumpkg.RespData;
import cn.yans.onegame.common.utils.MonsterCacheUtils;
import cn.yans.onegame.common.utils.RoleCacheUtils;
import cn.yans.onegame.entity.Monster;
import cn.yans.onegame.service.MonsterService;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/monster")
public class MonsterController {

    @Autowired
    private MonsterService monsterService;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private RoleCacheUtils roleCacheUtils;
    @Autowired
    private MonsterCacheUtils monsterCacheUtils;


    @GetMapping("/getMonster")
    public RespData<?> getMonster(@RequestParam("id") String id){
        if (StringUtils.isBlank(id)){
            return new RespData<>().fail("参数有误");
        }
        Monster monster = monsterService.getMonster(id);
        return new RespData<>(monster);
    }

    /**
     * 初始化怪物
     * @param id 怪物id
     * @param roleId 当前角色id
     * @return 怪物
     */
    @GetMapping("/initMonster")
    public RespData<?> initMonster(@RequestParam("id") String id,@RequestParam("roleId") String roleId){
        if (StringUtils.isBlank(id)){
            return new RespData<>().fail("参数有误");
        }
        Monster monster = monsterCacheUtils.getMonster(id, roleId);
        if (null == monster){
            monster = monsterService.getMonster(id);
            monsterCacheUtils.initMonster(monster,roleId,4L);
            return new RespData<>(monster);
        }
        return new RespData<>(monster);
    }

}
