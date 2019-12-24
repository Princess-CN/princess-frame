package huacloud.jwdsj.common.component.enums;

/**
 * 指标编码
 */
public enum ZbbmEnum {

    SLJS("SLJS"),//    受理件数-SLJS；
    TCKSJS("TCKSJS"),//    提出抗诉件数-TCKSJS；
    TCJCJYJS("TCJCJYJS"),//    提出检察建议件数-TCJCJYJS；
    TCZSJCJYJS("TCZSJCJYJS"),//    提出再审检察建议件数-TCZSJCJYJS；
    CNZSJCJYJS("CNZSJCJYJS"),//    采纳再审检察建议件数-CNZSJCJYJS；
    AJBLTS("AJBLTS"),//    案件办理天数-AJBLTS；
    SJJS("SJJS"),//    审结件数-SJJS；

    //    TCJCJY("TCJCJY"),//行政-提出检察建议
    FYCNJS("FYCNJS"),//行政-法院采纳件数
    ZCQSJS("ZCQSJS"),//行政-支持起诉件数
    FYGPJS("FYGPJS"),//行政-法院改判件数
    AJPMQK("AJPMQK"),//行政-案件排名情况

    LAJDJS("LAJDJS"),//普通刑事-立案监督件数
    LAJDRS("LAJDRS"),//普通刑事-立案监督人数
    TQJRZCJS("TQJRZCJS"),//普通刑事-提前介入侦查件数
    ZBRS("ZBRS"),//普通刑事-追捕人数

    JDFTJBQSRS("JDFTJBQSRS"),//未检-决定附条件不起诉人数
    FTJBQSHQSRS("FTJBQSHQSRS"),//未检-附条件不起诉后起诉人数
    FTJBQSKYQMHBQSRS("FTJBQSKYQMHBQSRS"),//未检-附条件不起诉考验期满后不起诉人数
    DWCNRZZQSRS("DWCNRZZQSRS"),//未检-对未成年人最终起诉人数

    JBSLJS("JBSLJS"),//控申-举报受理件数
    SCXFJS("SCXFJS"),//控申-其中首次信访件数
    SSSLJS("SSSLJS"),//控申-申诉受理件数经复查纠正原决定件数
    SCCLGLKGSSJBJS("SCCLGLKGSSJBJS"),//控申-审查处理各类控告申诉举报件数
    SSSCCLGLKGSSJBJS("SSSCCLGLKGSSJBJS"),//控申-申诉审查处理各类控告申诉举报件数
    LAFCXSSSAJJS("LAFCXSSSAJJS"),//控申-立案复查刑事申诉案件件数
    LAFCBFFYSXXSCPSSAJJS("LAFCBFFYSXXSCPSSAJJS"),//控申-立案复查不服法院生效刑事裁判申诉案件件数
    JFCTCKSJS("JFCTCKSJS"),//控申-经复查提出抗诉件数
    JFCTCZSJCJYJS("JFCTCZSJCJYJS"),//控申-经复查提出再审检察建议件数
    SLBFFYSXXSCPSSAJJS("SLBFFYSXXSCPSSAJJS"),//控申-受理不服法院生效刑事裁判申诉案件件数
    BFFYSXXSCPSSAJLAFCJS("BFFYSXXSCPSSAJLAFCJS"),//控申-不服法院生效刑事裁判申诉案件立案复查件数
    LAFCBFJCJGXSCLJDSSAJJS("LAFCBFJCJGXSCLJDSSAJJS"),//控申-立案复查不服检察机关刑事处理决定申诉案件件数
    JFCWCYJDJS("JFCWCYJDJS"),//控申-经复查维持原决定件数
    JFCGBYJDJS("JFCGBYJDJS"),//控申-经复查改变原决定件数
    JFCJZYJDJS("JFCJZYJDJS"),//控申-经复查纠正原决定件数
    SLBFJCJGCLJDSSAJJS("SLBFJCJGCLJDSSAJJS"),//控申-受理不服检察机关处理决定申诉案件件数
    BGYJDJS("BGYJDJS"),//控申-变更原决定件数
    JZYJDJS("JZYJDJS"),//控申-纠正原决定件数
    BLSSAJGCZGKSCJS("BLSSAJGCZGKSCJS"),//控申-办理申诉案件过程中公开审查件数
    QZBFJCJGCLJDGKSCJS("QZBFJCJGCLJDGKSCJS"),//控申-其中不服检察机关处理决定公开审查件数
    BFFYSXCPGKSCJS("BFFYSXCPGKSCJS"),//控申-不服法院生效裁判公开审查件数
//    TCKSJS("TCKSJS"),//控申-提出抗诉件数
    QZSCXFJS("QZSCXFJS"),//控申-其中首次信访件数
    KGSLJS("KGSLJS"),//控申-控告受理件数
//    SCCLGLKGSSKBKS("SCCLGLKGSSKBKS"),//控申-审查处理各类控告申诉举报件数
    KGSCCLGLKGSSKBKS("KGSCCLGLKGSSKBKS"),//控申-控告审查处理各类控告申诉举报件数
    SLXSPCSQJS("SLXSPCSQJS"),//控申-受理刑事赔偿申请件数
    XSPCLAJS("XSPCLAJS"),//控申-刑事赔偿立案件数
    JDGYPCJS("JDGYPCJS"),//控申-决定给予赔偿件数
    ZFPCJ("ZFPCJ"),//控申-支付赔偿金万元数
    FHCCZHRMB("FHCCZHRMB"),//控申-返还财产折合人民币万元数
    TCZSJCJYS("TCZSJCJYS"),//控申-对提出再审检察建议的案件法院已再审件数
    TCGJSFJZJS("TCGJSFJZJS"),//控申-提起国家司法救助件数
    SJJZRS("SJJZRS"),//控申-实际救助人数（含积存)
    FFJZJ("FFJZJ"),//控申-发放救助金万元数
    SLSFJZJS("SLSFJZJS"),//控申-受理司法救助件数
    QZJCJGZDTCSFJZJS("QZJCJGZDTCSFJZJS"),//控申-其中检察机关主动提起司法救助件数
    GPHFHZSZS("GPHFHZSZS"),//控申-改判和发回重审总数

    AJLX_PUXS("普通刑事案件"),
    AJLX_ZDXS("重大犯罪案件"),
    AJLX_ZWFZ("职务犯罪案件"),
    AJLX_JRFZ("经济犯罪案件"),
    SLSCDBJS("SLSCDBJS");  //受理审查逮捕件数

    private String value;

    private ZbbmEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
    }
