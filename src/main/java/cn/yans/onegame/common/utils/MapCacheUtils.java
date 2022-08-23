package cn.yans.onegame.common.utils;

import cn.yans.onegame.entity.GameLevel;
import cn.yans.onegame.entity.Monster;
import cn.yans.onegame.entity.PlayerRole;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson2.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class MapCacheUtils {
    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 创建/初始化/地图
     */
    public String initMap(String roleId, List<List<GameLevel>> gameLevelList){
        String number = gameLevelList.get(0).get(0).getNumber().split("-")[0];
        redisTemplate.opsForValue().set("map:" + roleId + "-" + number, JSON.toJSONString(gameLevelList),8, TimeUnit.HOURS);
        return number;
    }

    /**
     * 获取角色地图
     */
    public List<List<GameLevel>> getMap(String roleId, String layerNumber){
        String number = layerNumber.split("-")[0];
        String mapJson = redisTemplate.opsForValue().get("map:" + roleId + "-" + number);
        if (StringUtils.isBlank(mapJson)){
            return null;
        }
        List<List<GameLevel>> newLists = new ArrayList<>();
        List<String> jsons = JSONArray.parseArray(mapJson, String.class);
        for (String json : jsons) {
            newLists.add(JSONArray.parseArray(json,GameLevel.class));
        }
        return newLists;
    }
    /**
     * 删除地图
     */
    public void deleteMap(String roleId, String layerNumber){
        String number = layerNumber.split("-")[0];
        redisTemplate.delete("map:" + roleId + "-" + number);
    }
}
