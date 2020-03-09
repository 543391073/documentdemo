package poi.sectionFactory;

import poi.bo.DocumentSectionBO;

/**
 * 声明
 * @author ChenGuoHao
 * @date 2020/3/2 16:36
 */
public class AESection2 extends AbstractSectionParagraph {
    AESection2(DocumentSectionBO section) {
        super(section);
    }

    @Override
    void init() {
        int size = section.getXWPFParagraphs().size();
        String companyName  = null;
        String date = null;
        if(size > 2){
            companyName = section.getXWPFParagraphs().get(size - 3).getText();
            date = section.getXWPFParagraphs().get(size - 2).getText();
        }
        text = String.format("%s; %s",companyName, date);
    }
}
