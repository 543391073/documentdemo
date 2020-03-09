package poi.sectionFactory;

import poi.bo.DocumentSectionBO;

import java.util.HashMap;

/**
 * 2.3.2.8	数据类别
 * @author ChenGuoHao
 * @date 2020/3/6 15:25
 */
public class Section2_3_2_8 extends AbstractSectionTable {
    Section2_3_2_8(DocumentSectionBO section) {
        super(section);
    }

    @Override
    void init() {
        tableList = getCellsByFor(1, table.getRows().size(), new HashMap<Integer, String>() {{
            put(1, "数据类别");
            put(2, "所属业务应用");
            put(3, "安全防护需求");
        }});
    }
}
