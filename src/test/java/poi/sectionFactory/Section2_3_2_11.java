package poi.sectionFactory;

import poi.bo.DocumentSectionBO;

import java.util.HashMap;

/**
 * @author ChenGuoHao
 * @date 2020/3/9 10:54
 */
public class Section2_3_2_11 extends AbstractSectionTable {
    Section2_3_2_11(DocumentSectionBO section) {
        super(section);
    }

    @Override
    void init() {
        tableList = getCellsByFor(1, table.getRows().size(), new HashMap<Integer, String>() {{
            put(1, "产品/模块名称");
            put(2, "生产厂商");
            put(3, "商密型号");
            put(4, "密码算法");
            put(5, "重要程度");
        }});
    }
}
