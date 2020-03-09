package poi.sectionFactory;

import lombok.extern.slf4j.Slf4j;
import poi.bo.DocumentSectionBO;
import poi.util.ITableUtil;

import java.util.List;
import java.util.Map;

/**
 * 处理段落的抽象类
 * @author ChenGuoHao
 * @date 2020/3/2 18:02
 */
@Slf4j
abstract public class AbstractSectionParagraph extends AbstractSection {
    AbstractSectionParagraph(DocumentSectionBO section) {
        super(section);
        init();
    }

    //解析后的文本 init后初始化
    protected String text = null;

    /**
     * 打印输出
     */
    @Override
    final public void print() {
        log.info("{}：{}", section.getTitle(), getText());
    }

    @Override
    final public List<Map<String, Object>> getTable(){
        return null;
    }

    @Override
    public String getText() {
        return text;
    }

    /**
     * 获取段落中的所有内容
     * @return
     */
    final protected String getParagraphsText() {
        return ITableUtil.getParagraphsText(section.getXWPFParagraphs());
    }

}
