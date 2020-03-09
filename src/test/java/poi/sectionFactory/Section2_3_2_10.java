package poi.sectionFactory;

import poi.bo.DocumentSectionBO;

import java.util.HashMap;

/**
 * 2.3.2.10	安全管理文档
 * @author ChenGuoHao
 * @date 2020/3/6 15:25
 */
public class Section2_3_2_10 extends AbstractSectionTable {
    Section2_3_2_10(DocumentSectionBO section) {
        super(section);
    }

    @Override
    void init() {
        tableList = getCellsByFor(1, table.getRows().size(), new HashMap<Integer, String>() {{
            put(1, "文档名称");
            put(2, "主要内容");
        }});
    }
}
