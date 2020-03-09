package poi.sectionFactory;

import poi.bo.DocumentSectionBO;

import java.util.HashMap;

/**
 * 2.3.2.7	业务应用系统/平台
 * @author ChenGuoHao
 * @date 2020/3/6 15:25
 */
public class Section2_3_2_7 extends AbstractSectionTable {
    Section2_3_2_7(DocumentSectionBO section) {
        super(section);
    }

    @Override
    void init() {
        tableList = getCellsByFor(1, table.getRows().size(), new HashMap<Integer, String>() {{
            put(1, "业务应用系统/平台名称");
            put(2, "主要功能");
            put(3, "业务应用软件及版本");
            put(4, "开发厂商");
            put(5, "重要程度");
        }});
    }
}
