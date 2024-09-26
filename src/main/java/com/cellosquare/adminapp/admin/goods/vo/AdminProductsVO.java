package com.cellosquare.adminapp.admin.goods.vo;

import com.cellosquare.adminapp.common.upload.FileUploadVO;
import com.cellosquare.adminapp.common.vo.GeneralVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public class AdminProductsVO extends GeneralVO {

    // 상품 시퀀스
    private String productSeqNo;
    // 메타 시퀀스
    private String metaSeqNo;
    // 언어 코드
    private String langCd;
    // 상품 카테고리
    private String productCtgry;
    // 상품 이름
    private String productNm;
    // 서비스 시작일
    private String svcValidStatDate;
    // 서비스 종료일
    private String svcValidEndDate;
    // 상품 요약 정보
    private String productSummaryInfo;
    // PC LIST Image 경로
    private String pcListImgPath;
    // PC LIST Image 원본파일 명
    private String pcListImgOrgFileNm;
    // PC LIST Image 파일명
    private String pcListImgFileNm;
    // PC LIST Image 사이즈
    private String pcListImgSize;
    // PC LIST Image alt
    private String pcListImgAlt;
    // PC Detail Image 경로
    private String pcDetlImgPath;
    // PC Detail Image 원본파일 명
    private String pcDetlImgOrgFileNm;
    // PC Detail Image 파일 명
    private String pcDetlImgFileNm;
    // PC Detail Image 사이즈
    private String pcDetlImgSize;
    // PC Detail Image alt
    private String pcDetlImgAlt;
    // Mobile List Image 경로
    private String mobileListImgPath;
    // Mobile List Image 원본파일 명
    private String mobileListImgOrgFileNm;
    // Mobile List Image 파일 명
    private String mobileListImgFileNm;
    // Mobile List Image 사이즈
    private String mobileListImgSize;
    // Mobile List Image alt
    private String mobileListImgAlt;
    // Mobile Detail Image 경로
    private String mobileDetlImgPath;
    // Mobile Detail Image 경로
    private String mobileDetlImgOrgFileNm;
    // Mobile Detail Image 원본파일 명
    private String mobileDetlImgFileNm;
    // Mobile Detail Image 사이즈
    private String mobileDetlImgSize;
    // Mobile Detail Image alt
    private String mobileDetlImgAlt;
    // 상품 고시 내용
    private String productContents;
    // 사용유무
    private String useYn;
    // 조회수
    private String srchCnt;
    // 정렬순서
    private String ordb;
    private String useYnNm;
    private String productCtgryNm;

    // input file
    private MultipartFile pcListOrginFile;
    private MultipartFile pcDetailOrginFile;
    private MultipartFile mobileListOrginFile;
    private MultipartFile mobileDetailOrginFile;
    // imgView조건 구분자
    private String imgKinds;
    // 첨부파일 리스트
    List<FileUploadVO> fileList;
    // 파일 삭제여부
    private String pcListFileDel;
    private String pcDetailFileDel;
    private String mobileListFileDel;
    private String mobileDetailFileDel;
    // 리스트 정렬 저장
    private String[] listOrdb;
    private String[] listproductSeqNo;
    private String[] commonSelect2;
    private String ackQuestion;

    public String[] getCommonSelect2() {
        return commonSelect2;
    }

    public void setCommonSelect2(String[] commonSelect2) {
        this.commonSelect2 = commonSelect2;
    }

    public String getAckQuestion() {
        return ackQuestion;
    }

    public void setAckQuestion(String ackQuestion) {
        this.ackQuestion = ackQuestion;
    }

    public String[] getListOrdb() {
        return listOrdb;
    }

    public void setListOrdb(String[] listOrdb) {
        this.listOrdb = listOrdb;
    }

    public String[] getListproductSeqNo() {
        return listproductSeqNo;
    }

    public void setListproductSeqNo(String[] listproductSeqNo) {
        this.listproductSeqNo = listproductSeqNo;
    }

    public String getPcListFileDel() {
        return pcListFileDel;
    }

    public void setPcListFileDel(String pcListFileDel) {
        this.pcListFileDel = pcListFileDel;
    }

    public String getPcDetailFileDel() {
        return pcDetailFileDel;
    }

    public void setPcDetailFileDel(String pcDetailFileDel) {
        this.pcDetailFileDel = pcDetailFileDel;
    }

    public String getMobileListFileDel() {
        return mobileListFileDel;
    }

    public void setMobileListFileDel(String mobileListFileDel) {
        this.mobileListFileDel = mobileListFileDel;
    }

    public String getMobileDetailFileDel() {
        return mobileDetailFileDel;
    }

    public void setMobileDetailFileDel(String mobileDetailFileDel) {
        this.mobileDetailFileDel = mobileDetailFileDel;
    }

    public List<FileUploadVO> getFileList() {
        return fileList;
    }

    public void setFileList(List<FileUploadVO> fileList) {
        this.fileList = fileList;
    }

    public MultipartFile getPcListOrginFile() {
        return pcListOrginFile;
    }

    public void setPcListOrginFile(MultipartFile pcListOrginFile) {
        this.pcListOrginFile = pcListOrginFile;
    }

    public MultipartFile getPcDetailOrginFile() {
        return pcDetailOrginFile;
    }

    public void setPcDetailOrginFile(MultipartFile pcDetailOrginFile) {
        this.pcDetailOrginFile = pcDetailOrginFile;
    }

    public MultipartFile getMobileListOrginFile() {
        return mobileListOrginFile;
    }

    public void setMobileListOrginFile(MultipartFile mobileListOrginFile) {
        this.mobileListOrginFile = mobileListOrginFile;
    }

    public MultipartFile getMobileDetailOrginFile() {
        return mobileDetailOrginFile;
    }

    public void setMobileDetailOrginFile(MultipartFile mobileDetailOrginFile) {
        this.mobileDetailOrginFile = mobileDetailOrginFile;
    }

    public String getImgKinds() {
        return imgKinds;
    }

    public void setImgKinds(String imgKinds) {
        this.imgKinds = imgKinds;
    }

    public String getUseYnNm() {
        return useYnNm;
    }

    public void setUseYnNm(String useYnNm) {
        this.useYnNm = useYnNm;
    }

    public String getProductCtgryNm() {
        return productCtgryNm;
    }

    public void setProductCtgryNm(String productCtgryNm) {
        this.productCtgryNm = productCtgryNm;
    }

    public String getProductSeqNo() {
        return productSeqNo;
    }

    public void setProductSeqNo(String productSeqNo) {
        this.productSeqNo = productSeqNo;
    }

    @Override
    public String getMetaSeqNo() {
        return metaSeqNo;
    }

    @Override
    public void setMetaSeqNo(String metaSeqNo) {
        this.metaSeqNo = metaSeqNo;
    }

    public String getLangCd() {
        return langCd;
    }

    public void setLangCd(String langCd) {
        this.langCd = langCd;
    }

    public String getProductCtgry() {
        return productCtgry;
    }

    public void setProductCtgry(String productCtgry) {
        this.productCtgry = productCtgry;
    }

    public String getProductNm() {
        return productNm;
    }

    public void setProductNm(String productNm) {
        this.productNm = productNm;
    }

    public String getSvcValidStatDate() {
        return svcValidStatDate;
    }

    public void setSvcValidStatDate(String svcValidStatDate) {
        this.svcValidStatDate = svcValidStatDate;
    }

    public String getSvcValidEndDate() {
        return svcValidEndDate;
    }

    public void setSvcValidEndDate(String svcValidEndDate) {
        this.svcValidEndDate = svcValidEndDate;
    }

    public String getProductSummaryInfo() {
        return productSummaryInfo;
    }

    public void setProductSummaryInfo(String productSummaryInfo) {
        this.productSummaryInfo = productSummaryInfo;
    }

    public String getPcListImgPath() {
        return pcListImgPath;
    }

    public void setPcListImgPath(String pcListImgPath) {
        this.pcListImgPath = pcListImgPath;
    }

    public String getPcListImgOrgFileNm() {
        return pcListImgOrgFileNm;
    }

    public void setPcListImgOrgFileNm(String pcListImgOrgFileNm) {
        this.pcListImgOrgFileNm = pcListImgOrgFileNm;
    }

    public String getPcListImgFileNm() {
        return pcListImgFileNm;
    }

    public void setPcListImgFileNm(String pcListImgFileNm) {
        this.pcListImgFileNm = pcListImgFileNm;
    }

    public String getPcListImgSize() {
        return pcListImgSize;
    }

    public void setPcListImgSize(String pcListImgSize) {
        this.pcListImgSize = pcListImgSize;
    }

    public String getPcListImgAlt() {
        return pcListImgAlt;
    }

    public void setPcListImgAlt(String pcListImgAlt) {
        this.pcListImgAlt = pcListImgAlt;
    }

    public String getPcDetlImgPath() {
        return pcDetlImgPath;
    }

    public void setPcDetlImgPath(String pcDetlImgPath) {
        this.pcDetlImgPath = pcDetlImgPath;
    }

    public String getPcDetlImgOrgFileNm() {
        return pcDetlImgOrgFileNm;
    }

    public void setPcDetlImgOrgFileNm(String pcDetlImgOrgFileNm) {
        this.pcDetlImgOrgFileNm = pcDetlImgOrgFileNm;
    }

    public String getPcDetlImgFileNm() {
        return pcDetlImgFileNm;
    }

    public void setPcDetlImgFileNm(String pcDetlImgFileNm) {
        this.pcDetlImgFileNm = pcDetlImgFileNm;
    }

    public String getPcDetlImgSize() {
        return pcDetlImgSize;
    }

    public void setPcDetlImgSize(String pcDetlImgSize) {
        this.pcDetlImgSize = pcDetlImgSize;
    }

    public String getPcDetlImgAlt() {
        return pcDetlImgAlt;
    }

    public void setPcDetlImgAlt(String pcDetlImgAlt) {
        this.pcDetlImgAlt = pcDetlImgAlt;
    }

    public String getMobileListImgPath() {
        return mobileListImgPath;
    }

    public void setMobileListImgPath(String mobileListImgPath) {
        this.mobileListImgPath = mobileListImgPath;
    }

    public String getMobileListImgOrgFileNm() {
        return mobileListImgOrgFileNm;
    }

    public void setMobileListImgOrgFileNm(String mobileListImgOrgFileNm) {
        this.mobileListImgOrgFileNm = mobileListImgOrgFileNm;
    }

    public String getMobileListImgFileNm() {
        return mobileListImgFileNm;
    }

    public void setMobileListImgFileNm(String mobileListImgFileNm) {
        this.mobileListImgFileNm = mobileListImgFileNm;
    }

    public String getMobileListImgSize() {
        return mobileListImgSize;
    }

    public void setMobileListImgSize(String mobileListImgSize) {
        this.mobileListImgSize = mobileListImgSize;
    }

    public String getMobileListImgAlt() {
        return mobileListImgAlt;
    }

    public void setMobileListImgAlt(String mobileListImgAlt) {
        this.mobileListImgAlt = mobileListImgAlt;
    }

    public String getMobileDetlImgPath() {
        return mobileDetlImgPath;
    }

    public void setMobileDetlImgPath(String mobileDetlImgPath) {
        this.mobileDetlImgPath = mobileDetlImgPath;
    }

    public String getMobileDetlImgOrgFileNm() {
        return mobileDetlImgOrgFileNm;
    }

    public void setMobileDetlImgOrgFileNm(String mobileDetlImgOrgFileNm) {
        this.mobileDetlImgOrgFileNm = mobileDetlImgOrgFileNm;
    }

    public String getMobileDetlImgFileNm() {
        return mobileDetlImgFileNm;
    }

    public void setMobileDetlImgFileNm(String mobileDetlImgFileNm) {
        this.mobileDetlImgFileNm = mobileDetlImgFileNm;
    }

    public String getMobileDetlImgSize() {
        return mobileDetlImgSize;
    }

    public void setMobileDetlImgSize(String mobileDetlImgSize) {
        this.mobileDetlImgSize = mobileDetlImgSize;
    }

    public String getMobileDetlImgAlt() {
        return mobileDetlImgAlt;
    }

    public void setMobileDetlImgAlt(String mobileDetlImgAlt) {
        this.mobileDetlImgAlt = mobileDetlImgAlt;
    }

    public String getProductContents() {
        return productContents;
    }

    public void setProductContents(String productContents) {
        this.productContents = productContents;
    }

    @Override
    public String getUseYn() {
        return useYn;
    }

    @Override
    public void setUseYn(String useYn) {
        this.useYn = useYn;
    }

    public String getSrchCnt() {
        return srchCnt;
    }

    public void setSrchCnt(String srchCnt) {
        this.srchCnt = srchCnt;
    }

    public String getOrdb() {
        return ordb;
    }

    public void setOrdb(String ordb) {
        this.ordb = ordb;
    }
}
