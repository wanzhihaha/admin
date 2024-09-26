package com.cellosquare.adminapp.admin.estimate.service.impl;

import com.cellosquare.adminapp.admin.estimate.conver.EstimateInfoExportVOConver;
import com.cellosquare.adminapp.admin.estimate.mapper.AdminEstimateMapper;
import com.cellosquare.adminapp.admin.estimate.service.AdminEstimateService;
import com.cellosquare.adminapp.admin.estimate.vo.AdminEstimateVO;
import com.cellosquare.adminapp.admin.estimate.vo.EstimateInfoExportVO;
import com.cellosquare.adminapp.common.enums.SinotechProdutctTypeListEnum;
import com.cellosquare.adminapp.common.util.EasyExcelUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 20220617 update
 * AdminEstimateServiceImpl
 *
 * @author juru.jia
 * @Date : 2022/06/17
 */
@Service
public class AdminEstimateServiceImpl implements AdminEstimateService {

    /**
     * excel download template path
     */
    private final String excelFilePath = "template/QuoteDBtable.xlsx";

    @Autowired
    private AdminEstimateMapper adminEstimateMapper;

    /**
     * quote history excel download
     *
     * @param vo
     * @param response
     * @return
     */
    @Override
    public void quoteExcelDownload(AdminEstimateVO vo, HttpServletResponse response) throws Exception {
        List<AdminEstimateVO> list = AdminEstimateVOListFormat(adminEstimateMapper.getList(vo));
        String fileName = vo.getStatDate() + "_" + vo.getEndDate() + "_询价历史";
        List<EstimateInfoExportVO> estimateInfoExportVOs = EstimateInfoExportVOConver.INSTANCT.getEstimateInfoExportVOs(list);
        EasyExcelUtils.writeExcel(response, estimateInfoExportVOs, fileName, "sheet", EstimateInfoExportVO.class);
    }

    /**
     * get quote history list count
     *
     * @param vo
     * @return
     */
    @Override
    public int getTotalCount(AdminEstimateVO vo) {
        return adminEstimateMapper.getTotalCount(vo);
    }

    /**
     * get quote history list by limit page
     *
     * @param vo
     * @return
     */
    @Override
    public List<AdminEstimateVO> getListByPage(AdminEstimateVO vo) {
        return AdminEstimateVOListFormat(adminEstimateMapper.getListByPage(vo));
    }

    /**
     * Format AdminEstimateVO List
     *
     * @param list
     * @return
     */
    private List<AdminEstimateVO> AdminEstimateVOListFormat(List<AdminEstimateVO> list) {

        int recordNo = 1;

        for (int i = 0; i < list.size(); i++) {
            AdminEstimateVO vo = list.get(i);

            if ("Y".equals(vo.getSearchRecordYn())) {

                if (i == 0 && vo.getQuoteCnt() > 1) {
                    for (int cnt = vo.getQuoteCnt() - 1; cnt >= 0; cnt--) {
                        if (list.size() > cnt && vo.getSqNo().equals(list.get(cnt).getSqNo())) {
                            recordNo = vo.getQuoteCnt() - cnt;
                            break;
                        }
                    }
                }

                vo.setQuotePrice(vo.getQuoteCurrency() + " " + vo.getQuoteFare());
                vo.setRecordNo(leftComplete(recordNo));

                recordNo++;

                if ((i + 1) < list.size() && !vo.getSqNo().equals(list.get(i + 1).getSqNo())) {
                    recordNo = 1;
                }
            } else {
                recordNo = 1;
            }
            expressToEstimateVO(vo);

            if (StringUtils.isNotEmpty(vo.getProductType())) {
                List<String> types = new ArrayList<>();
                Arrays.stream(vo.getProductType().split(",")).forEach(s -> {
                    try {
                        SinotechProdutctTypeListEnum enumByCode = SinotechProdutctTypeListEnum.getEnumByCode(s);
                        types.add(enumByCode.getCnValue());
                    } catch (Exception e) {

                    }
                });
                vo.setProductType(types.stream().collect(Collectors.joining(",")));
            }
        }
        return list;
    }

    private void expressToEstimateVO(AdminEstimateVO vo) {
        if ("EXP_SINOTECH".equals(vo.getSvcMedCtgryCode())) {
            vo.setSvcClassCd("-");
        }
        if ("EXP".equals(vo.getSvcMedCtgryCode())) {
            vo.setSvcClassCd("-");
            vo.setDeppNm(splitStrNm(vo.getDeppNm()));
            vo.setArrpNm(splitStrNm(vo.getArrpNm()));
        }
    }

    private String splitStrNm(String str) {
        return str;
//        if (StringUtils.isEmpty(str))
//            return "";
//        return str.substring(str.lastIndexOf("(") + 1, str.lastIndexOf(")"));
    }

    /**
     * left complete "0" to 3 size
     *
     * @param num
     * @return
     */
    private String leftComplete(int num) {
        String str = num + "";
        int length = 0;
        if (str.length() < 3) {
            length = 3 - str.length();
        }
        for (int i = 0; i < length; i++) {
            str = "0" + str;
        }
        return str;
    }

    /**
     * money format ex: 1000000 => 1,000,000
     *
     * @param total
     * @return
     */
    private String parseTotal(String total) {
        return DecimalFormat.getNumberInstance().format(Long.valueOf(total));
    }
}
