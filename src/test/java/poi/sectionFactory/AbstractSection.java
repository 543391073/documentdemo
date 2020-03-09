package poi.sectionFactory;

import lombok.extern.slf4j.Slf4j;
import poi.bo.DocumentSectionBO;

import java.util.List;
import java.util.Map;

/**
 * 章节处理抽象类
 * @author ChenGuoHao
 * @date 2020/3/2 16:01
 */
@Slf4j
abstract public class AbstractSection {
    DocumentSectionBO section = null;
    AbstractSection(DocumentSectionBO section) {
        this.section = section;
    }

    /**
     * 初始化
     */
    abstract void init();

    abstract public String getText();
    abstract public List<Map<String, Object>> getTable();

    abstract public void print();



}
