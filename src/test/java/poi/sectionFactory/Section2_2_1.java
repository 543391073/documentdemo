package poi.sectionFactory;

import poi.bo.DocumentSectionBO;

import java.util.HashMap;

/**
 * 2.2.1	安全通用要求指标
 * @author ChenGuoHao
 * @date 2020/3/6 11:24
 */
public class Section2_2_1 extends AbstractSectionTable {
    Section2_2_1(DocumentSectionBO section) {
        super(section);
    }

    @Override
    void init() {
        tableList = getCellsByFor(1, table.getRows().size(), new HashMap<Integer, String>(){{
            put(0, "安全类");
            put(1, "安全控制点");
            put(2, "测评项数");
        }});
    }
}
