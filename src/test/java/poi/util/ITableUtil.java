package poi.util;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPr;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * 针对测评报告文档解析的工具类
 * @author ChenGuoHao
 * @date 2020/2/28 13:10
 */
@Slf4j
public class ITableUtil {

    /**
     * 获取所有目录列表, 去掉前后空格
     * @param document 等级测评报告word
     * @return word中的标题
     */
    public static List<String> getTOC(XWPFDocument document) {
        int[] titleLevel = {0, 0, 0, 0};
        int[] appendix = {64, 0, 0, 0};
        List<XWPFParagraph> paragraphs = document.getParagraphs(); //文档中所有段落
        List<String> toc = new ArrayList<>(180); //目录所有标题(加序号)

        for (int i = 0; i < paragraphs.size(); i++) { //遍历所有元素
            if (ITableUtil.elementIsTitle(paragraphs.get(i))) { //是标题
                String style = paragraphs.get(i).getStyle();
                StringBuilder prefix = new StringBuilder();
                if (StrUtil.equalsAny(style, "1", "2", "3", "4", "5")) { //标题
                    if (style.equals("1")) { //一级标题
                        titleLevel[0] += 1;
                        titleLevel[1] = 0;
                        titleLevel[2] = 0;
                        titleLevel[3] = 0;
                    } else if (style.equals("2")) { //二级标题
                        titleLevel[1] += 1;
                        titleLevel[2] = 0;
                        titleLevel[3] = 0;
                    } else if (style.equals("3")) { //三级标题
                        titleLevel[2] += 1;
                        titleLevel[3] = 0;
                    } else if (style.equals("4")) { //四级标题
                        titleLevel[3] += 1;
                    }

                    if (titleLevel[0] > 0) {
                        prefix.append(titleLevel[0]);
                    }
                    if (titleLevel[1] > 0) {
                        prefix.append(".");
                        prefix.append(titleLevel[1]);
                    }
                    if (titleLevel[2] > 0) {
                        prefix.append(".");
                        prefix.append(titleLevel[2]);
                    }
                    if (titleLevel[3] > 0) {
                        prefix.append(".");
                        prefix.append(titleLevel[3]);
                    }
                } else if (StrUtil.equalsAny(style, "a0", "a1", "a2", "a3", "a4")) { //附录
                    if (style.equals("a0")) { //附录1
                        appendix[0] += 1;
                        appendix[1] = 0;
                        appendix[2] = 0;
                        appendix[3] = 0;
                    } else if (style.equals("a1")) { //附录2
                        appendix[1] += 1;
                        appendix[2] = 0;
                        appendix[3] = 0;
                    } else if (style.equals("a2")) { //附录3
                        appendix[2] += 1;
                        appendix[3] = 0;
                    } else if (style.equals("a3")) { //附录4
                        appendix[3] += 1;
                    } else if (style.equals("a4")) { //附录5
                        appendix[3] += 1;
                    }

                    if (appendix[0] > 0) {
                        prefix.append((char) appendix[0]);
                    }
                    if (appendix[1] > 0) {
                        prefix.append(".");
                        prefix.append(appendix[1]);
                    }
                    if (appendix[2] > 0) {
                        prefix.append(".");
                        prefix.append(appendix[2]);
                    }
                    if (appendix[3] > 0) {
                        prefix.append(".");
                        prefix.append(appendix[3]);
                    }
                }

                if(ITableUtil.elementIsTitle(paragraphs.get(i + 1))){ //判断是否有子标题，如果有则跳过循环
                    continue;
                }
                String title = paragraphs.get(i).getText().trim();
                toc.add(prefix.toString() + "\t" + title);
            }
        }
        return toc;
    }

    /**
     * 获取元素所在行
     * @param table
     * @return
     */
    public static int indexOf(XWPFTable table){
        return table.getBody().getBodyElements().indexOf(table);
    }

    /* ===================================表格相关处理=================================== */

    /**
     * 获取单元格中 复选框选中的内容
     * @param line 行
     * @param col  列
     * @return 返回当前表格 第row行第cell列中选中的内容  以 英文逗号","分割
     */
    public static String getCellCheckbox(XWPFTable table, int line, int col) {
        try {
            StringBuilder builder = new StringBuilder();

            //获取当前表格所有的段落
            List<XWPFParagraph> paragraphs = table.getRow(line).getTableCells().get(col).getParagraphs();
            boolean start = false; //是否开始拼接
            for (XWPFParagraph paragraph : paragraphs) { //遍历段落
                List<XWPFRun> runs = paragraph.getRuns();
                for (int i = 0; i < runs.size(); i++) { //遍历段落中的run (获取选中的复选框后面的有效数据)
                    if (start) {
                        if (ITableUtil.isCheckbox(runs.get(i))) {
                            builder.append(",");
                            //收集中遇到复选框
                            if (ITableUtil.isCheckbox(runs.get(i), false)) { //如果是未选中的复选框，则停止收集工作
                                start = false;
                            }
                        } else if (StrUtil.isNotBlank(runs.get(i).text())) {
                            builder.append(runs.get(i).text());
                        }
                    } else if (ITableUtil.isCheckbox(runs.get(i), true)) { //找到第一个选中的符号后设置开始工作
                        start = true;
                    }
                }
            }
            return StrUtil.removeSuffix(builder.toString(), ",");
        } catch (Exception e) {
            log.error("异常信息", e);
            return null;
        }
    }

    /**
     * 获取指定单元格中内容
     * 如果当前单元格属于合并行，则获取合并行中的数据
     * @param line 行
     * @param col  列
     * @return 返回当前表格 第row行第cell列的数据(String)， 如果异常则返回null
     */
    public static String getCell(XWPFTable table, int line, int col) throws NullPointerException {

        CTTcPr tcPr = table.getRow(line).getCell(col).getCTTc().getTcPr();
        if (tcPr.isSetVMerge() && !tcPr.getVMerge().isSetVal()) { //是否属于合并行
            for (int i = line - 1; i >= 0; i--) {
                tcPr = table.getRow(i).getCell(col).getCTTc().getTcPr();
                if (tcPr.isSetVMerge() && tcPr.getVMerge().isSetVal()) {
                    return table.getRow(i).getCell(col).getText().trim();
                }
            }
        }/* else if (table.getRow(line).getTableCells().size() > col) {
            //    tcPr.isSetGridSpan()
            }*/
        return table.getRow(line).getCell(col).getText().trim();

    }

    /**
     * 获取表格数据  从第{startLine}到{endLine}行，的 [col] 列的数据
     * @param table     表格
     * @param startLine 开始循环行(下标)
     * @param endLine   结束行(下标)
     * @param cols      列(需要获取的数据的列,下标)
     * @return 返回一个集合
     */
    public static List<Map<String, Object>> getCellsByFor(XWPFTable table, int startLine, int endLine, Map<Integer,
            String> cols) throws NullPointerException{
        List<Map<String, Object>> list = new ArrayList<>();
        for (int i = startLine; i < endLine; i++) {
            HashMap<String, Object> map = new HashMap<>();
            for (int cell : cols.keySet()) {
                map.put(cols.get(cell), getCell(table, i, cell));
            }
            list.add(map);
        }
        return list;
    }




    /* ===================================段落相关处理=================================== */

    /**
     * 获取段落内容
     * @param paragraph
     */
    public static String getParagraphText(XWPFParagraph paragraph) {

        // 获取段落中所有内容
        List<XWPFRun> runs = paragraph.getRuns();
        if (runs.isEmpty()) return "";
        StringBuffer runText = new StringBuffer();
        for (XWPFRun run : runs) {
            String text = run.getText(run.getTextPosition());//run.text()
            // 如果字符为空，可能是附件一类的文件，比如图片之类的，需要另外解析,此处处理为图片
            if (text == null) {
                List<XWPFPicture> piclist = run.getEmbeddedPictures();
                File picDir = new File("D:\\work\\临时文件\\结构化数据\\image");
                if (!picDir.exists()) picDir.mkdirs();
                try {
                    for (XWPFPicture pic : piclist) {
                        String filePath = picDir.getPath() + "/" + UUID.randomUUID() + ".jpg";
                        byte[] picbyte = pic.getPictureData().getData();
                        // 将图片写入本地文件
                        FileOutputStream fos = null;
                        fos = new FileOutputStream(filePath);
                        fos.write(picbyte);
                        runText.append(filePath);
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                runText.append(text);
            }

        }
        return runText.toString();
    }

    /**
     * 获取段落内容
     * @param paragraphs 段落集合中拼接获取文本
     * @return 返回获取段落的内容
     */
    public static String getParagraphsText(List<XWPFParagraph> paragraphs) {
        StringBuilder sb = new StringBuilder();
        for (XWPFParagraph paragraph : paragraphs) {
            sb.append(getParagraphText(paragraph)).append("\n");
        }
        return sb.toString().trim();
    }



    /* ===================================其他元素判断=================================== */

    /**
     * 判断元素是否是段落
     * @param element
     * @return
     */
    public static boolean elementIsParagraph(IBodyElement element) {
        return element instanceof XWPFParagraph;
    }

    /**
     * 判断是否是标题
     * 通过 xwpfParagraph.getStyle() 判断当前段落的类型
     * 其中 style 为 ["1","2","3","4","5"]表示 标题 1-5
     * style 为 ["a0","a1","a2","a3","a4"]表示 附录 一至五级
     * style 为 ["ae"] 表示标题
     * @param element 元素
     * @return 如果是有效标题则返回 true 否则返回fasle
     */
    public static boolean elementIsTitle(IBodyElement element) {
        if (element instanceof XWPFParagraph) {
            //标题的样式
            XWPFParagraph xwpfParagraph = (XWPFParagraph) element;
            String[] documentTitleStyle = new String[]{"1", "2", "3", "4", "5", "ae", "a0", "a1", "a2", "a3", "a4"};
            return StrUtil.isNotBlank(xwpfParagraph.getText()) && StrUtil.equalsAny(xwpfParagraph.getStyle(), documentTitleStyle);
        }
        return false;
    }

    /**
     * 判断元素是否是表格
     * @param element
     * @return
     */
    public static boolean elementIsTable(IBodyElement element) {
        return element instanceof XWPFTable;
    }

    /**
     * 判断段落中的 run是否是复选框
     * * <w:sym w:char="F052" w:font="Wingdings 2"/>
     * * <w:sym w:char="F0A3" w:font="Wingdings 2"/>
     * @param run
     * @return
     */
    public static boolean isCheckbox(XWPFRun run) {
        return CollUtil.isNotEmpty(run.getCTR().getSymList());
    }

    /**
     * 判断复选框是否选中
     * @param run
     * @param b
     * @return
     */
    public static boolean isCheckbox(XWPFRun run, boolean b) {
        if (!isCheckbox(run)) {
            return false;
        }

        String xmlText = run.getCTR().getSymList().get(0).xmlText();
        if (b == true && StrUtil.contains(xmlText, "w:char=\"F052\"")) {
            return true;
        } else if (b == false && StrUtil.contains(xmlText, "w:char=\"F0A3\"")) {
            return true;
        } else {
            return false;
        }
    }


}
