package cn.yans.onegame.common.utils;

import cn.yans.onegame.entity.BaseCard;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CardUtils {

   public List<BaseCard> initColor(List<BaseCard> cards){
        for (BaseCard card : cards) {
            card.setColor(getColor(card.getLevel()));
        }
        return cards;
    }

    private String getColor(int level){
        String color = "";
        switch (level){
            case 1: color = "color: #2B2D42";break;
            case 2: color = "color: #6EEB83";break;
            case 3: color = "color: #1B98E0";break;
            case 4: color = "color: #540D6E";break;
            case 5: color = "color: #EF233C";break;
            case 6: color = "color: #FF9F1C";break;
        }
        return color;
    }
}
