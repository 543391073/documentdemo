package poi.sectionFactory;

import poi.bo.DocumentSectionBO;

import java.util.HashMap;

/**
 * 2.3.2.4	服务器/存储设备
 * @author ChenGuoHao
 * @date 2020/3/6 15:25
 */
public class Section2_3_2_4 extends AbstractSectionTable {
    Section2_3_2_4(DocumentSectionBO section) {
        super(section);
    }

    @Override
    void init() {
        tableList = getCellsByFor(1, table.getRows().size(), new HashMap<Integer, String>() {{
            put(1, "设备名称");
            put(2, "所属业务应用系统/平台名称");
            put(3, "是否虚拟设备");
            put(4, "操作系统及版本");
            put(5, "数据库管理系统及版本");
            put(6, "中间件及版本");
            put(7, "重要程度");
        }});
    }
}
