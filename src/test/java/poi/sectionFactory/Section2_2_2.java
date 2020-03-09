package poi.sectionFactory;

import poi.bo.DocumentSectionBO;

import java.util.HashMap;
import java.util.Map;

/**
 * 2.2.2	安全扩展要求指标
 * @author ChenGuoHao
 * @date 2020/3/6 11:31
 */
public class Section2_2_2 extends AbstractSectionTable{

    Section2_2_2(DocumentSectionBO section) {
        super(section);
    }

    @Override
    void init() {
        tableList = getCellsByFor(1, table.getRows().size(), new HashMap<Integer,String>(){{
            put(0, "扩展类型");
            put(1, "安全类");
            put(2, "安全控制点");
            put(3, "测评项数");
        }}) ;
    }
}
