package cn.yans.onegame;

import cn.yans.onegame.common.utils.UUIDUtils;
import cn.yans.onegame.dao.mapper.GameLevelMapper;
import cn.yans.onegame.entity.GameLevel;
import cn.yans.onegame.service.GameLevelService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@SpringBootTest
class OnegameApplicationTests {

	@Autowired
	private GameLevelMapper gameLevelMapper;
	@Autowired
	private GameLevelService gameLevelService;

	@Test
	void contextLoads() {
		for (int i = 0; i < 20; i++) {
			System.out.println(UUIDUtils.get16Uuid());
		}
	}

	@Test
	void skillChange(){
		List<String> skillId = new ArrayList<>();
		skillId.add("dda3c4f40759e20d");
		skillId.add("dda3c4f40759e20d");
		String s = JSON.toJSONString(skillId);
		System.out.println(s);
	}

	@Test
	void randomTest(){
		Random random = new Random();
		for (int i = 0; i < 10; i++) {
			int i1 = random.nextInt(2);
			System.out.println(i1);
		}
	}

	@Test
	void gameLevel(){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		//当前日期设置为指定日期
		c.setTime(new Date());
		//指定日期月份减去一
		c.add(Calendar.MONTH, -2);
		//指定日期月份减去一后的 最大天数
		c.set(Calendar.DATE, c.getActualMaximum(Calendar.DATE));
		String lastDay = format.format(c.getTime());
		System.out.println(lastDay);
	}

	@Test
	void mapperTest(){
		List<List<GameLevel>> lists = gameLevelService.initRandomMap();
		String mapJson = JSON.toJSONString(lists);

		List<List<GameLevel>> newLists = new ArrayList<>();
		List<String> strings = JSONArray.parseArray(mapJson, String.class);
		for (String string : strings) {
			newLists.add(JSONArray.parseArray(string,GameLevel.class));
		}
		System.out.println(newLists.get(0).get(0).getNumber());

	}

}
