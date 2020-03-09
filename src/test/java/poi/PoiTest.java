package poi;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.junit.Test;
import poi.bo.DocumentSectionBO;
import poi.sectionFactory.AbstractSection;
import poi.sectionFactory.AbstractSectionParagraph;
import poi.sectionFactory.AbstractSectionTable;
import poi.sectionFactory.SectionFactory;
import poi.util.ITableUtil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author ChenGuoHao
 * @date 2020/2/27 10:37
 */
@Slf4j
public class PoiTest {

    @Test
    public void demo() {
        try {
            //InputStream is = new FileInputStream("D:/work/临时文件/结构化数据/等级测评报告模板2019版-V35-20190624-最终发布稿 - 定稿-副本.docx");
            InputStream is = new FileInputStream("D:/work/临时文件/结构化数据/深圳市阿卡索资讯股份有限公司阿卡索外教网系统安全等级测评报告（一式两份）.docx");
            //InputStream is = new FileInputStream("D:/Program Files (x86)/Tencent/WeChat Files/WeChat Files/fr_543391073/FileStorage/File/2020-02/a(1).docx");
            XWPFDocument document = new XWPFDocument(is);
            ArrayList<DocumentSectionBO> wordSections = extractDocument(document);
            AtomicReference<AbstractSection> builder = new AtomicReference();
            HashMap<String, String> map = new HashMap<>();
            wordSections.forEach(wordSection -> {
                builder.set(SectionFactory.builder(wordSection));
                if (builder.get() == null) {
                    log.warn("当前章节《{}》没有对应的解析器", wordSection.getTitle());
                    return;
                }
                if (builder.get() instanceof AbstractSectionTable) { //表格处理类
                    builder.get().print();
                }else if(builder.get() instanceof AbstractSectionParagraph){ //段落处理类
                    map.put(wordSection.getTitle(), builder.get().getText());
                }
            });
            //List<XWPFTable> tables = document.getTables();
            //Map<String, XWPFTable> map = new HashMap<>();
            //System.out.println("tables.size():" + tables.size());
            //
            //for (XWPFTable table : tables) {
            //    for (int i = document.getBodyElements().indexOf(table); i > 0; i--){
            //        if (ITableUtil.elementIsTitle(document.getBodyElements().get(i))) {
            //            XWPFParagraph paragraph = (XWPFParagraph)document.getBodyElements().get(i);
            //            map.put(paragraph.getText(),table);
            //            break;
            //        }
            //    }
            //}
            //System.out.println("map.size():" + map.size());


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 读取word中的数据并整理后返回对象
     * 按照标题分类
     * @param document XWPFDocument加载后的文档
     * @return 返回自定义个数据处理对象
     */
    private ArrayList<DocumentSectionBO> extractDocument(XWPFDocument document) {
        ArrayList<DocumentSectionBO> wordSections = new ArrayList<>();

        //获取word的所有标题以及标题序号，用于唯一编号
        List<String> tocList = ITableUtil.getTOC(document);

        String title = null;
        List<IBodyElement> bodyElements = document.getBodyElements();
        for (int i = 0; i < bodyElements.size(); i++) {

            //判断当前元素是否为标题
            if (ITableUtil.elementIsTitle(bodyElements.get(i))) {
                title = ((XWPFParagraph) bodyElements.get(i)).getText().trim(); //提取标题名称
                for (String t : tocList) {
                    if (title.equals(StrUtil.subAfter(t, "\t", true))) { //去掉前缀对比

                        //删除 \t前面的最后一个 .\d
                        //例: 2.1.1\t定级结果 -> 2.1\t定级结果
                        //列: 2.3.2.9\t安全相关人员 -> 2.3.2\t安全相关人员
                        title = ReUtil.delFirst("\\.\\d+(?=\\t)", t.trim());

                        tocList.remove(t); //移除
                        break;
                    }
                }

                List<XWPFParagraph> paragraphList = new ArrayList<>();
                List<XWPFTable> tableList = new ArrayList<>();

                for (int j = i + 1; j < bodyElements.size(); j++) {
                    IBodyElement element = bodyElements.get(j);
                    if (ITableUtil.elementIsTitle(element)) { //是标题
                        i = j - 1;
                        break;
                    } else if (ITableUtil.elementIsTable(element)) { //是表格
                        tableList.add((XWPFTable) element);
                    } else if (ITableUtil.elementIsParagraph(element)) { //是段落
                        paragraphList.add((XWPFParagraph) element);
                    }
                }

                //生成一个 文档章节对象
                if (CollUtil.isNotEmpty(tableList) || CollUtil.isNotEmpty(paragraphList)) {
                    wordSections.add(new DocumentSectionBO(title, tableList, paragraphList));
                }
            }
        }
        return wordSections;
    }


}
