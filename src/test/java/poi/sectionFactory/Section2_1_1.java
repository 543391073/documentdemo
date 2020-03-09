package poi.sectionFactory;

import lombok.extern.slf4j.Slf4j;
import poi.bo.DocumentSectionBO;

import java.util.HashMap;

/**
 * 2.1.1	定级结果
 * @author ChenGuoHao
 * @date 2020/3/3 15:01
 */
@Slf4j
public class Section2_1_1 extends AbstractSectionTable {
    Section2_1_1(DocumentSectionBO section) {
        super(section);
    }

    @Override
    void init() {
        tableList = getCellsByFor(1, table.getRows().size(), new HashMap<Integer, String>(){{
            put(0, "被测对象名称");
            put(1, "安全保护等级");
            put(2, "业务信息安全等级");
            put(3, "系统服务安全等级");
        }});
    }
}
