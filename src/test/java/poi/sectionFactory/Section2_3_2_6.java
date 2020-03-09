package poi.sectionFactory;

import poi.bo.DocumentSectionBO;

import java.util.HashMap;

/**
 * 2.3.2.6	系统管理软件/平台
 * @author ChenGuoHao
 * @date 2020/3/6 15:25
 */
public class Section2_3_2_6 extends AbstractSectionTable {
    Section2_3_2_6(DocumentSectionBO section) {
        super(section);
    }

    @Override
    void init() {
        tableList = getCellsByFor(1, table.getRows().size(), new HashMap<Integer, String>() {{
            put(1, "系统管理软件/平台名称");
            put(2, "所在设备名称");
            put(3, "版本");
            put(4, "主要功能");
            put(5, "重要程度");
        }});
    }
}
