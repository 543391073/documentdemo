package poi.sectionFactory;

import poi.bo.DocumentSectionBO;

import java.util.HashMap;

/**
 * 2.3.2.3	安全设备
 * @author ChenGuoHao
 * @date 2020/3/6 15:19
 */
public class Section2_3_2_3 extends AbstractSectionTable {
    Section2_3_2_3(DocumentSectionBO section) {
        super(section);
    }

    @Override
    void init() {
        tableList = getCellsByFor(1, table.getRows().size(), new HashMap<Integer, String>() {{
            put(1, "设备名称");
            put(2, "是否虚拟设备");
            put(3, "系统及版本");
            put(4, "品牌及型号");
            put(5, "用途");
            put(6, "重要程度");
        }});
    }
}
