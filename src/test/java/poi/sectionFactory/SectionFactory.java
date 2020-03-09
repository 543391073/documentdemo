package poi.sectionFactory;

import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.ReflectUtil;
import poi.bo.DocumentSectionBO;

import java.util.HashMap;
import java.util.Map;

/**
 * iSectionMap 的 key 是word中的标题
 * 需要对应到相应的序号，但是序号不要精确到当前级，只需要到父级即可，这样可以解决同级标题之间顺序和数量增加的问题
 * @author ChenGuoHao
 * @date 2020/3/2 16:02
 */
public class SectionFactory {

    //每一个章节都有一个对应的处理类，用来解析表格
    private static Map<String, Class<? extends AbstractSection>> iSectionMap = new HashMap<>();

    static {
        init();
    }


    private static void init() {
        iSectionMap.put("网络安全等级测评基本信息表", AESection1.class);
        iSectionMap.put("声明", AESection2.class);
        iSectionMap.put("等级测评结论", AESection3.class);
        iSectionMap.put("等级测评结论扩展表（云计算安全）", AESection4.class);
        iSectionMap.put("等级测评结论扩展表（大数据安全）", AESection5.class);
        iSectionMap.put("总体评价", DefaultSectionParagraphHandler.class);
        iSectionMap.put("主要安全问题及整改建议", DefaultSectionParagraphHandler.class);
        iSectionMap.put("1\t测评目的", DefaultSectionParagraphHandler.class);
        iSectionMap.put("1\t测评依据", DefaultSectionParagraphHandler.class);
        iSectionMap.put("1\t测评过程", DefaultSectionParagraphHandler.class);
        iSectionMap.put("1\t报告分发范围", DefaultSectionParagraphHandler.class);
        iSectionMap.put("2.1\t定级结果", Section2_1_1.class);
        iSectionMap.put("2.1\t业务和采用的技术", DefaultSectionParagraphHandler.class);
        iSectionMap.put("2.1\t网络结构", DefaultSectionParagraphHandler.class);
        iSectionMap.put("2.2\t安全通用要求指标", Section2_2_1.class);
        iSectionMap.put("2.2\t安全扩展要求指标",Section2_2_2.class);
        iSectionMap.put("2.2\t其他安全要求指标", Section2_2_3.class);
        iSectionMap.put("2.2\t不适用安全要求指标",Section2_2_4.class);
        iSectionMap.put("2.3\t测评对象选择方法", DefaultSectionParagraphHandler.class);
        iSectionMap.put("2.3.2\t物理机房", Section2_3_2_1.class);
        iSectionMap.put("2.3.2\t网络设备", Section2_3_2_2.class);
        iSectionMap.put("2.3.2\t安全设备", Section2_3_2_3.class);
        iSectionMap.put("2.3.2\t服务器/存储设备", Section2_3_2_4.class);
        iSectionMap.put("2.3.2\t终端/现场设备", Section2_3_2_5.class);
        iSectionMap.put("2.3.2\t系统管理软件/平台", Section2_3_2_6.class);
        iSectionMap.put("2.3.2\t业务应用系统/平台", Section2_3_2_7.class);
        iSectionMap.put("2.3.2\t数据类别", Section2_3_2_8.class);
        iSectionMap.put("2.3.2\t安全相关人员", Section2_3_2_9.class);
        iSectionMap.put("2.3.2\t安全管理文档", Section2_3_2_10.class);
        iSectionMap.put("2.3.2\t密码产品", Section2_3_2_11.class);
        iSectionMap.put("3\t单项测评结果分析", DefaultSectionParagraphHandler.class);
        iSectionMap.put("3\t安全物理环境", DefaultSectionParagraphHandler.class);
        iSectionMap.put("3.2\t已有安全控制措施汇总分析", DefaultSectionParagraphHandler.class);
        iSectionMap.put("3.3\t主要安全问题汇总分析", DefaultSectionParagraphHandler.class);
        iSectionMap.put("3.4.1\t已有安全控制措施汇总分析", DefaultSectionParagraphHandler.class);
        iSectionMap.put("3.4.1\t主要安全问题汇总分析", DefaultSectionParagraphHandler.class);
        iSectionMap.put("3.4.2\t已有安全控制措施汇总分析", DefaultSectionParagraphHandler.class);
        iSectionMap.put("3.4.2\t主要安全问题汇总分析", DefaultSectionParagraphHandler.class);
        iSectionMap.put("3.4.3\t已有安全控制措施汇总分析", DefaultSectionParagraphHandler.class);
        iSectionMap.put("3.4.3\t主要安全问题汇总分析", DefaultSectionParagraphHandler.class);
        iSectionMap.put("3.4.4\t已有安全控制措施汇总分析", DefaultSectionParagraphHandler.class);
        iSectionMap.put("3.4.4\t主要安全问题汇总分析", DefaultSectionParagraphHandler.class);
        iSectionMap.put("3.5\t已有安全控制措施汇总分析", DefaultSectionParagraphHandler.class);
        iSectionMap.put("3.5\t主要安全问题汇总分析", DefaultSectionParagraphHandler.class);
        iSectionMap.put("3.6\t已有安全控制措施汇总分析", DefaultSectionParagraphHandler.class);
        iSectionMap.put("3.6\t主要安全问题汇总分析", DefaultSectionParagraphHandler.class);
        iSectionMap.put("3.7\t已有安全控制措施汇总分析", DefaultSectionParagraphHandler.class);
        iSectionMap.put("3.7\t主要安全问题汇总分析", DefaultSectionParagraphHandler.class);
        iSectionMap.put("3.8\t已有安全控制措施汇总分析", DefaultSectionParagraphHandler.class);
        iSectionMap.put("3.8\t主要安全问题汇总分析", DefaultSectionParagraphHandler.class);
        iSectionMap.put("3.9\t已有安全控制措施汇总分析", DefaultSectionParagraphHandler.class);
        iSectionMap.put("3.9\t主要安全问题汇总分析", DefaultSectionParagraphHandler.class);
        iSectionMap.put("3.10\t已有安全控制措施汇总分析", DefaultSectionParagraphHandler.class);
        iSectionMap.put("3.10\t主要安全问题汇总分析", DefaultSectionParagraphHandler.class);
        iSectionMap.put("3.11\t已有安全控制措施汇总分析", DefaultSectionParagraphHandler.class);
        //iSectionMap.put("3.12.1\t漏洞扫描汇总表", null);
        //iSectionMap.put("3.12.1\t漏洞扫描问题描述", null);
        //iSectionMap.put("3.12.2\t渗透测试过程说明", null);
        //iSectionMap.put("3.12.2\t渗透测试问题描述", null);
        //iSectionMap.put("3.13.1\t控制点符合情况汇总", null);
        //iSectionMap.put("3.13.2\t安全问题汇总", null);

    }





    /**
     * 构建解析器
     * @param wordSection
     * @return
     */
    public static AbstractSection builder(DocumentSectionBO wordSection) {

        String title = wordSection.getTitle();

        if (iSectionMap.containsKey(title)) {
            return ReflectUtil.newInstance(iSectionMap.get(title), wordSection);
        }
        return null;
    }
}
