package poi.sectionFactory;

import poi.bo.DocumentSectionBO;

import java.util.HashMap;

/**
 * @author ChenGuoHao
 * @date 2020/3/6 11:35
 */
public class Section2_3_2_1 extends AbstractSectionTable {
    Section2_3_2_1(DocumentSectionBO section) {
        super(section);
    }

    @Override
    void init() {
        tableList = getCellsByFor(1, table.getRows().size(), new HashMap<Integer, String>() {{
            put(1, "机房名称");
            put(2, "物理位置");
            put(3, "重要程度");
        }});
    }
}
