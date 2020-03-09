package poi.sectionFactory;

import lombok.extern.slf4j.Slf4j;
import poi.bo.DocumentSectionBO;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 等级测评结论
 * @author ChenGuoHao
 * @date 2020/3/2 17:01
 */
@Slf4j
public class AESection3 extends AbstractSectionTable {
    AESection3(DocumentSectionBO section) {
        super(section);
    }

    @Override
    void init() {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("被测对象名称",getCell(1,1));
        map.put("安全保护等级",getCell(1,3));
        map.put("等级保护对象形态",getCellCheckbox(2,1));
        map.put("被测对象描述",getCell(3,1));
        map.put("测评工作描述",getCell(4,1));
        map.put("等级测评结论",getCell(5,1));
        map.put("综合得分",getCell(5,3));
        tableList.add(map);
    }
}
