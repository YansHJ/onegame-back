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
import java.util.stream.Stream;

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

    public List<List<GameLevel>> nextMap(String firstNum){
        List<GameLevel> mapList = gameLevelMapper.getMap(firstNum);
        Optional<GameLevel> firstMap = mapList.stream().filter(o -> StringUtils.startsWith(o.getId(), firstNum)).findFirst();
        if (firstMap.isPresent()){
            List<List<GameLevel>> nextMapList = new ArrayList<>();
            GameLevel firstLevel = firstMap.get();
            //设置层数
            firstLevel.setLayer(1);
            List<GameLevel> firstList = new ArrayList<>();
            firstList.add(firstLevel);
            nextMapList.add(firstList);
            List<GameLevel> lastList = mapList.stream().filter(o -> !o.getId().equals(firstLevel.getId())).collect(Collectors.toList());
            return buildMapList(firstLevel,nextMapList,lastList);
        }
        return null;
    }

    private List<List<GameLevel>> buildMapList(GameLevel firstLevel,List<List<GameLevel>> nextMapList,List<GameLevel> lastList){
        if (lastList.size() < 1) {
            return nextMapList;
        }
        List<GameLevel> temp = lastList.stream().filter(o -> StringUtils.indexOf(o.getLast(), firstLevel.getId()) != -1).collect(Collectors.toList());
        List<GameLevel> nextList = new ArrayList<>();
        //构建序号和层数
        for (GameLevel gameLevel : temp) {
            String[] numberSplit = gameLevel.getNumber().split("-");
            gameLevel.setNumber(numberSplit[1] + "-" +numberSplit[2]);
            gameLevel.setLayer(Integer.parseInt(numberSplit[1]));
            nextList.add(gameLevel);
        }
        nextMapList.add(nextList);
        List<GameLevel> newLastList = lastList.stream().filter(o -> StringUtils.indexOf(o.getLast(), firstLevel.getId()) == -1).collect(Collectors.toList());
        return buildMapList(nextList.get(0),nextMapList,newLastList);
    }

    /**
     * 递归构建Map树，但前端不会解析渲染，只能放弃
     */
    private GameLevel packageMap(GameLevel firstLevel,List<GameLevel> mapList){
        if (mapList.size() < 1){
            return firstLevel;
        }
        String side = firstLevel.getNumber().split("-")[2];
        System.out.println(side + "..." + firstLevel.getNumber());
        if (Integer.parseInt(side) > 1){
            return firstLevel;
        }
        List<GameLevel> nextNextMap = mapList.stream().filter(o -> StringUtils.indexOf(o.getLast(), firstLevel.getId()) == -1).collect(Collectors.toList());
        List<GameLevel> nextMap = mapList.stream().filter(o -> StringUtils.indexOf(o.getLast(), firstLevel.getId()) != -1).collect(Collectors.toList());
        List<GameLevel> nextGameLevel = new ArrayList<>();
        int i = 0;
        for (GameLevel gameLevel : nextMap) {
            nextGameLevel.add(packageMap(gameLevel,nextNextMap));
        }
        firstLevel.setNextGameLevel(nextGameLevel);
        return firstLevel;
    }
}
