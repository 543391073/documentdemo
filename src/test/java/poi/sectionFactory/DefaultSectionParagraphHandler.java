package poi.sectionFactory;

import poi.bo.DocumentSectionBO;

/**
 * 默认段落处理器 (获取所有文本)
 * @author ChenGuoHao
 * @date 2020/3/4 15:43
 */
public class DefaultSectionParagraphHandler extends AbstractSectionParagraph{
    DefaultSectionParagraphHandler(DocumentSectionBO section) {
        super(section);
    }

    @Override
    void init() {
        text = getParagraphsText();
    }

}
