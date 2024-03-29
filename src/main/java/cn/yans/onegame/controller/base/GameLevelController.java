package cn.yans.onegame.controller.base;


import cn.yans.onegame.common.enumpkg.RespData;
import cn.yans.onegame.entity.GameLevel;
import cn.yans.onegame.service.GameLevelService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/gameLevel")
public class GameLevelController {

    @Autowired
    private GameLevelService gameLevelService;

    @GetMapping("/getMap")
    public RespData<?> getMapByFirstId(@RequestParam("id")String id){
        if (StringUtils.isBlank(id)){
            return new RespData<>().fail("参数有误");
        }
        List<List<GameLevel>> lists = gameLevelService.nextMap(id);
//        List<GameLevel> list = new ArrayList<>();
//        list.add(map);
        return new RespData<>(lists);
    }

    @GetMapping("/initRandomMap")
    public RespData<?> getInitMap(@RequestParam("roleId")String roleId){
        if (StringUtils.isBlank(roleId)){
            return new RespData<>().fail("参数有误");
        }
        List<GameLevel> lists = gameLevelService.initRandomMap(roleId);
        return new RespData<>(lists);
    }
}
