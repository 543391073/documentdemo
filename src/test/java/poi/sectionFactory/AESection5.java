package poi.sectionFactory;

import lombok.extern.slf4j.Slf4j;
import poi.bo.DocumentSectionBO;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 等级测评结论扩展表（大数据安全）
 * @author ChenGuoHao
 * @date 2020/3/2 17:54
 */
@Slf4j
public class AESection5 extends AbstractSectionTable {
    AESection5(DocumentSectionBO section) {
        super(section);
    }

    @Override
    void init() {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("大数据形态",getCellCheckbox(1,1));
        map.put("运维所在地",getCell(2,1));
        map.put("部署模式",getCellCheckbox(2,3));
        map.put("大数据平台服务安全能力评价", getCellsByFor(4, table.getRows().size() - 1,
                new HashMap<Integer, String>() {{
                    put(1, "大数据平台服务列表");
                    put(2, "符合性评价");
                }}));
        map.put("等级测评结论",getCell(table.getRows().size() - 1,1));
        map.put("综合得分",getCell(table.getRows().size() - 1,3));
        tableList.add(map);
    }
}
