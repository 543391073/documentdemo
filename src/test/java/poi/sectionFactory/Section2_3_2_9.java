package poi.sectionFactory;

import poi.bo.DocumentSectionBO;

import java.util.HashMap;

/**
 * 2.3.2.9	安全相关人员
 * @author ChenGuoHao
 * @date 2020/3/6 15:25
 */
public class Section2_3_2_9 extends AbstractSectionTable {
    Section2_3_2_9(DocumentSectionBO section) {
        super(section);
    }

    @Override
    void init() {
        tableList = getCellsByFor(1, table.getRows().size(), new HashMap<Integer, String>() {{
            put(1, "姓名");
            put(2, "岗位/角色");
            put(3, "联系方式");
        }});
    }
}
