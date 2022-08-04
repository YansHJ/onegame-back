package cn.yans.onegame;

import cn.yans.onegame.common.utils.UUIDUtils;
import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SpringBootTest
class OnegameApplicationTests {

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

}
