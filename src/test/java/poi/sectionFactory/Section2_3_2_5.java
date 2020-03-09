package poi.sectionFactory;

import poi.bo.DocumentSectionBO;

import java.util.HashMap;

/**
 * 2.3.2.5	终端/现场设备
 * @author ChenGuoHao
 * @date 2020/3/6 15:25
 */
public class Section2_3_2_5 extends AbstractSectionTable {
    Section2_3_2_5(DocumentSectionBO section) {
        super(section);
    }

    @Override
    void init() {
        tableList = getCellsByFor(1, table.getRows().size(), new HashMap<Integer, String>() {{
            put(1, "设备名称");
            put(2, "是否虚拟设备");
            put(3, "操作系统/控制软件及版本");
            put(4, "设备类别/用途");
            put(5, "重要程度");
        }});
    }
}
