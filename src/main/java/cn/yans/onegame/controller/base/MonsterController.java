package cn.yans.onegame.controller.base;


import cn.yans.onegame.common.enumpkg.RespData;
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
        String monsterCache = redisTemplate.opsForValue().get("monster:" + roleId + "-" + id);
        if (StringUtils.isBlank(monsterCache)){
            Monster monster = monsterService.getMonster(id);
            redisTemplate.opsForValue().set("monster:" + roleId + "-" + monster.getId(), JSON.toJSONString(monster),8, TimeUnit.HOURS);
            return new RespData<>(monster);
        }
        Monster monster = JSON.parseObject(monsterCache, Monster.class);
        return new RespData<>(monster);
    }

}
