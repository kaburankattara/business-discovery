package com.example.businessdiscovery.utils;//import com.facebook.ads.sdk.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PrintUtils {

    private static final String br = "<br/>";

    public static String printMap(Map<String, Object> map) {
        return printMap(map, "", new StringBuilder());
    }

    private static String printMap(Map<String, Object> map, String indent, StringBuilder sb) {
        for (Map.Entry<String, Object> entry : map.entrySet()) {

            System.out.println(indent + "key：" + entry.getKey());
            sb.append(indent + "key：" + entry.getKey() + br);
            Object value = entry.getValue();
            if (value.getClass() == ArrayList.class) {
                System.out.println(indent + "value(list)：");
                sb.append(indent + "value(list)：" + br);
                sb.append(printList((List) value, indent + " ", sb));
                continue;
            }
            if (value.getClass() == LinkedHashMap.class) {
                System.out.println(indent + "value：");
                sb.append(indent + "value：" + br);
                sb.append(printMap((Map<String, Object>) value, indent + " ", sb));
                continue;
            }
            System.out.println(indent + "value：" + value);
            sb.append(indent + "value：" + value + br);
        }
        return sb.toString();
    }

    public static String printList(List list) {
        return printList(list, "", new StringBuilder());
    }

    private static String printList(List list, String indent, StringBuilder sb) {
        int i = 0;
        for (Object listValue : list) {
            i++;
            System.out.println(indent + "index：" + i);
            sb.append(indent + "index：" + br);
            if (listValue.getClass() == ArrayList.class) {
                sb.append(printList((List) listValue, indent + " ", sb));
                continue;
            }
            if (listValue.getClass() == LinkedHashMap.class) {
                sb.append(printMap((Map<String, Object>) listValue, indent + " ", sb));
                continue;
            }
            System.out.println(indent + " " + "value：" + listValue);
            sb.append(indent + " " + "value：" + listValue + br);
        }
        return sb.toString();
    }
}
