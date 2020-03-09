package poi.sectionFactory;

import poi.bo.DocumentSectionBO;
import poi.util.ITableUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 2.2.3\t其他安全要求指标
 * @author ChenGuoHao
 * @date 2020/3/4 16:49
 */
public class Section2_2_3 extends AbstractSectionTable {
    Section2_2_3(DocumentSectionBO section) {
        super(section);
    }

    @Override
    void init() {
        tableList = getCellsByFor(1, table.getRows().size(), new HashMap<Integer, String>() {{
            put(0, "安全类");
            put(1, "安全控制点");
            put(2, "特殊要求描述");
            put(3, "测评项数");
        }});
    }
}
