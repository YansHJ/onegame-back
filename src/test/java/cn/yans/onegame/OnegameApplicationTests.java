package cn.yans.onegame;

import cn.yans.onegame.common.utils.UUIDUtils;
import cn.yans.onegame.dao.mapper.GameLevelMapper;
import cn.yans.onegame.entity.GameLevel;
import cn.yans.onegame.service.GameLevelService;
import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SpringBootTest
class OnegameApplicationTests {

	@Autowired
	private GameLevelMapper gameLevelMapper;
	@Autowired
	private GameLevelService gameLevelService;

	@Test
	void contextLoads() {
		for (int i = 0; i < 10; i++) {
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
		List<String> gameLevel = new ArrayList<>();
		gameLevel.add("10ac1904b1ca14b6");
		String s = JSON.toJSONString(gameLevel);
		System.out.println(s);
	}

	@Test
	void mapperTest(){
		GameLevel map = gameLevelService.getMap("0d07");
		String s = JSON.toJSONString(map);
		System.out.println(s);
	}

}
