package cn.yans.onegame.service.Impl;

import cn.yans.onegame.dao.mapper.GameLevelMapper;
import cn.yans.onegame.entity.GameLevel;
import cn.yans.onegame.service.GameLevelService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GameLevelServiceImpl implements GameLevelService {

    @Autowired
    private GameLevelMapper gameLevelMapper;
    @Override

    /**
     * 通过第一关的节点序号构建整幅地图
     */
    public GameLevel getMap(String firstNum) {
        List<GameLevel> mapList = gameLevelMapper.getMap(firstNum);
        Optional<GameLevel> firstMap = mapList.stream().filter(o -> StringUtils.startsWith(o.getId(), firstNum)).findFirst();
        if (firstMap.isPresent()){
            GameLevel firstLevel = firstMap.get();
            List<GameLevel> nextList = mapList.stream().filter(o -> !o.getId().equals(firstLevel.getId())).collect(Collectors.toList());
            return packageMap(firstLevel, nextList);
        }
        return null;
    }

    private GameLevel packageMap(GameLevel firstLevel,List<GameLevel> mapList){
        if (mapList.size() < 1){
            return firstLevel;
        }
        List<GameLevel> nextNextMap = mapList.stream().filter(o -> StringUtils.indexOf(o.getLast(), firstLevel.getId()) == -1).collect(Collectors.toList());
        List<GameLevel> nextMap = mapList.stream().filter(o -> StringUtils.indexOf(o.getLast(), firstLevel.getId()) != -1).collect(Collectors.toList());
        List<GameLevel> nextGameLevel = new ArrayList<>();
        for (GameLevel gameLevel : nextMap) {
            GameLevel newGameLevel = new GameLevel();
            BeanUtils.copyProperties(gameLevel,newGameLevel);
            nextGameLevel.add(packageMap(newGameLevel,nextNextMap));
        }
        firstLevel.setNextGameLevel(nextGameLevel);
        return firstLevel;
    }
}
