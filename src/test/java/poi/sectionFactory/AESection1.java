package poi.sectionFactory;

import lombok.extern.slf4j.Slf4j;
import poi.bo.DocumentSectionBO;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 网络安全等级测评基本信息表
 * @author ChenGuoHao
 * @date 2020/3/2 16:15
 */
@Slf4j
public class AESection1 extends AbstractSectionTable {
    AESection1(DocumentSectionBO section) {
        super(section);
    }

    @Override
    void init() {
        Map<String, Object> map = new LinkedHashMap<>();
        //被测对象
        map.put("被测对象名称",getCell(1,1));
        map.put("安全保护等级",getCell(1,3));
        map.put("备案证明编号",getCell(2,1));

        //被测单位
        map.put("被测单位名称",getCell(4,1));
        map.put("被测单位地址",getCell(5,1));
        map.put("被测单位邮政编码",getCell(5,3));
        map.put("被测单位联系人姓名",getCell(6,2));
        map.put("被测单位联系人职务/职称",getCell(6,4));
        map.put("被测单位联系人所属部门",getCell(7,2));
        map.put("被测单位联系人办公电话",getCell(7,4));
        map.put("被测单位联系人移动电话",getCell(8,2));
        map.put("被测单位联系人电子邮件",getCell(8,4));

        //测评单位
        map.put("测评单位名称",getCell(10,1));
        map.put("测评单位机构代码",getCell(10,3));
        map.put("测评单位地址",getCell(11,1));
        map.put("测评单位邮政编码",getCell(11,3));
        map.put("测评单位联系人姓名",getCell(12,2));
        map.put("测评单位联系人职务/职称",getCell(12,4));
        map.put("测评单位联系人所属部门",getCell(13,2));
        map.put("测评单位联系人办公电话",getCell(13,4));
        map.put("测评单位联系人移动电话",getCell(14,2));
        map.put("测评单位联系人电子邮件",getCell(14,4));
        map.put("测评单位编制人",getCell(15,2));
        map.put("测评单位编制日期",getCell(15,4));
        map.put("测评单位审核人",getCell(16,2));
        map.put("测评单位审核日期",getCell(16,4));
        map.put("批准人",getCell(17,2));
        map.put("批准日期",getCell(17,4));

        tableList.add(map);
    }



}
