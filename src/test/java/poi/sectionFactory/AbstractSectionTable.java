package poi.sectionFactory;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import poi.bo.DocumentSectionBO;
import poi.util.ITableUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 处理表格的抽象类
 * @author ChenGuoHao
 * @date 2020/3/3 10:11
 */
@Slf4j
abstract public class AbstractSectionTable extends AbstractSection {
    protected XWPFTable table = null;
    protected List<Map<String, Object>> tableList = new ArrayList<>();

    AbstractSectionTable(DocumentSectionBO section) {
        super(section);
        if (section.getTables().size() > 1) {
            System.err.println(section.getTables().size());
        }
        if (section.getTables().size() > 0) {
            table = section.getTables().get(0);
        }
        init();
    }

    @Override
    final public String getText() {
        return null;
    }

    @Override
    final public List<Map<String, Object>> getTable() {
        return tableList;
    }

    @Override
    final public void print() {
        log.info("{}：{}", section.getTitle(), tableList);
    }


    /**
     * 处理 XWPFTable table对象
     * @param r 行
     * @param c 列
     * @return 返回指定单元格的内容
     */
    final protected String getCell(int r, int c) {
        try {
            return ITableUtil.getCell(table, r, c);
        } catch (NullPointerException e) {
            log.error("《{}》下 表格中不存在第{}行第{}列，错误所在行:{}", section.getTitle(), r, c, ITableUtil.indexOf(table));
            e.printStackTrace();
            return "";
        }
    }

    final protected List<Map<String, Object>> getCellsByFor(int startLine, int endLine, Map<Integer, String> cols) {
        List<Map<String, Object>> list = new ArrayList<>();
        for (int i = startLine; i < endLine; i++) {
            HashMap<String, Object> map = new HashMap<>();
            for (int cell : cols.keySet()) {
                map.put(cols.get(cell), getCell(i, cell));
            }
            list.add(map);
        }
        return list;
    }

    /**
     * 获取单元格中复选框选中的内容
     * @param r 行
     * @param c 列
     * @return 返回选中的内容
     */
    final protected String getCellCheckbox(int r, int c) {
        return ITableUtil.getCellCheckbox(table, r, c);
    }
}
