package cn.yans.onegame;

import cn.yans.onegame.common.utils.UUIDUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OnegameApplicationTests {

	@Test
	void contextLoads() {
		for (int i = 0; i < 10; i++) {
			System.out.println(UUIDUtils.get16Uuid());
		}
	}

}
