package poi.sectionFactory;

import poi.bo.DocumentSectionBO;

import java.util.HashMap;

/**
 * @author ChenGuoHao
 * @date 2020/3/6 11:34
 */
public class Section2_2_4 extends AbstractSectionTable {
    Section2_2_4(DocumentSectionBO section) {
        super(section);
    }

    @Override
    void init() {
        tableList = getCellsByFor(1, table.getRows().size(), new HashMap<Integer, String>() {{
            put(0, "安全类");
            put(1, "安全控制点");
            put(2, "不适用项");
            put(3, "不适用对象");
            put(4, "原因说明");
        }});
    }
}
