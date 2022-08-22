package cn.yans.onegame.service.Impl;

import cn.yans.onegame.common.utils.UUIDUtils;
import cn.yans.onegame.dao.mapper.GameLevelMapper;
import cn.yans.onegame.entity.GameLevel;
import cn.yans.onegame.service.GameLevelService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class GameLevelServiceImpl implements GameLevelService {

    @Autowired
    private GameLevelMapper gameLevelMapper;
    @Override

    /**
     * 通过第一关的节点序号构建整幅地图
     * 适用于，但也不是那么适用于固定关卡，后期改造
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

    /**
     * 新版构建地图树，但是还是难于渲染，逐渐放弃，改用随机生成
     */
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

    /**
     * 生成随机地图
     */
    private List<List<GameLevel>> initRandomMap(){
        //获取生成随机层数（目前暂定10 - 15）
        int layers = getLayers(10, 15);
        //奖励
        int reward = 0;
        //精英
        int elite = 0;
        GameLevel firstMap = gameLevelMapper.getMapByDifficulty(1, 1);
        firstMap.setId(UUIDUtils.get16Uuid());
        String number = firstMap.getId().substring(0, 4);
        //全局MapList
        List<List<GameLevel>> mapList = new ArrayList<>();
        mapList.add(Collections.singletonList(firstMap));
        //难度
        int diff = 4;
        //Y轴
        for (int i = 1; i < layers - 1; i++) {
            //当前层获取随机选项
            int x = getLayers(0, 4);
            //当前层MapList
            List<GameLevel> layerMap = new ArrayList<>();
            //X轴
            for (int i1 = 0; i1 < x; i1++) {
                GameLevel map = new GameLevel();
                if (reward == 2){
                    //奖励关
                    reward = 0;
                    if (elite >= 5){
                        elite--;
                    }else {
                        elite++;
                    }
                    map = gameLevelMapper.getMapByDifficulty(0, 0);
                }else if (elite == 5){
                    //精英
                    elite = 0;
                    reward++;
                    map = gameLevelMapper.getMapByDifficulty(2, diff/4);
                }else {
                    //普通
                    reward++;
                    elite++;
                    map = gameLevelMapper.getMapByDifficulty(1, diff/4);
                }
                map.setId(UUIDUtils.get16Uuid());
                map.setNumber(number + "-" + layers + "-" + i1);
                layerMap.add(map);
            }
            mapList.add(layerMap);
            diff++;
        }
        //Boss
        List<GameLevel> bossLayer = Collections.singletonList(gameLevelMapper.getMapByDifficulty(3, 1));
        mapList.add(bossLayer);
        return mapList;
    }

    /**
     * 生成 pre - suf 范围之间的层数
     */
    private int getLayers(int pre,int suf){
        Random random = new Random();
        int layers;
        do {
            layers = random.nextInt(suf);
        }while (layers < pre);
       return layers;
    }

}
