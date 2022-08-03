package cn.yans.onegame.controller.base;


import cn.yans.onegame.common.enumpkg.RespData;
import cn.yans.onegame.entity.Monster;
import cn.yans.onegame.service.MonsterService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/monster")
public class MonsterController {

    @Autowired
    private MonsterService monsterService;
    @GetMapping
    public RespData<?> getMonster(@RequestParam("id") String id){
        if (StringUtils.isBlank(id)){
            return new RespData<>().fail("参数有误");
        }
        Monster monster = monsterService.getMonster(id);
        return new RespData<>(monster);
    }
}
