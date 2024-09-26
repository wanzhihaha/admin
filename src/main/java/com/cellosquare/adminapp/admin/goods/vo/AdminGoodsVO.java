package com.cellosquare.adminapp.admin.goods.vo;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.cellosquare.adminapp.common.upload.FileUploadVO;
import com.cellosquare.adminapp.common.vo.GeneralVO;

public class AdminGoodsVO extends GeneralVO {
	
	// 상품 시퀀스
    private String sqprdSeqNo;
	// 메타 시퀀스
    private String metaSeqNo;
	// 언어 코드
    private String langCd;
	// 상품 카테고리
    private String sqprdCtgry;
	// 상품 이름
    private String sqprdNm;
	// 상품 영문이름
    private String sqprdEngNm;
    // 상품 관리자
    private String sqprdAttnId;
	// 상품 navy
    private String sqprdNavy;
	// 서비스 시작일
    private String svcValidStatDate;
	// 서비스 종료일
	private String svcValidEndDate;
	// 상품 요약 정보
	private String sqprdSummaryInfo;
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
	// 상품 상세 정보
	private String sqprdDetlInfo;
	// 상품 고시 내용
	private String sqprdNotiContents;
	// POL
	private String depPortCd;
	// POD
	private String arrpPotCd;
	// Ship-to
	private String shipTo;
	// 상품 옵션1
	private String sqprdOpt1;
	// 상품 옵션2
	private String sqprdOpt2;
	// 상품 옵션3
	private String sqprdOpt3;
	// 상품 옵션4
	private String sqprdOpt4;
	// 상품 옵션5
	private String sqprdOpt5;
	// 관련 상품1
	private String reltdSqprd1;
	// 관련 상품2
	private String reltdSqprd2;
	// 관련 상품3
	private String reltdSqprd3;
	// View Type
	private String viewTypeImg;
	// View Type BG 옵션
	private String viewTypeImgBkgrColorOpt;
	// View Type BG 구분
	private String viewTypeImgBkgrColorCcd;
	// View Type BG 값
	private String viewTypeImgBkgrColorVal;
	// View Type - From
	private String viewTypeImgFrom;
	// View Type 구분
	private String viewTypeImgCcd;
	// View Type - To
	private String viewTypeImgTo;
	// View Type - Hash Tag
	private String viewTypeImgHashTag;
	// View Type Image 경로
	private String viewTypeImgPath;
	// View Type Image 원본파일명
	private String viewTypeImgOrgFileNm;
	// View Type Image 파일명
	private String viewTypeImgFileNm;
	// View Type Image 사이즈
	private String viewTypeImgSize;
	// View Type Image alt
	private String viewTypeImgAlt;
	// 사용유무
	private String useYn;
	// 조회수
	private String srchCnt;
	// 정렬순서
	private String ordb;
	
	// 사용 유무 명
	private String useYnNm;	
	private String sqprdCtgryNm;
	private String viewTypeImgNm;
	private String viewTypeImgBkgrColorOptNm;
	private String viewTypeImgBkgrColorCcdNm;
	private String viewTypeImgCcdNm;
	
	// input file
	private MultipartFile pcListOrginFile;
	private MultipartFile pcDetailOrginFile;
	private MultipartFile mobileListOrginFile;
	private MultipartFile mobileDetailOrginFile;	
	private MultipartFile viewTypeOrginFile;
	// 파일 삭제여부
	private String pcListFileDel;
	private String pcDetailFileDel;
	private String mobileListFileDel;
	private String mobileDetailFileDel;
	private String viewTypeFileDel;
	// imgView조건 구분자
	private String imgKinds;
	// 리스트 정렬 저장
	private String[] listOrdb;
	private String[] listSqprdSeqNo;
	
	// 관련상품 시퀀스 번호1
	private String relsqprdSeqNo1;
	// 관련상품 시퀀스 번호2
	private String relsqprdSeqNo2;
	// 관련상품 시퀀스 번호3
	private String relsqprdSeqNo3;
	// 첨부파일 리스트
	List<FileUploadVO> fileList;
	// 파일 삭제여부
	private String[] fileUploadDel;
	// 파일 선택여부
	private String[] fileUploadSelect;
	// popup 검색어
	private String goodsSearchValue;
	
	List<AdminGoodsVO> goodsList;
	
	
	public String getViewTypeImg() {
		return viewTypeImg;
	}
	public void setViewTypeImg(String viewTypeImg) {
		this.viewTypeImg = viewTypeImg;
	}
	public String getSqprdSeqNo() {
		return sqprdSeqNo;
	}
	public void setSqprdSeqNo(String sqprdSeqNo) {
		this.sqprdSeqNo = sqprdSeqNo;
	}
	public String getMetaSeqNo() {
		return metaSeqNo;
	}
	public void setMetaSeqNo(String metaSeqNo) {
		this.metaSeqNo = metaSeqNo;
	}
	public String getLangCd() {
		return langCd;
	}
	public void setLangCd(String langCd) {
		this.langCd = langCd;
	}
	public String getSqprdCtgry() {
		return sqprdCtgry;
	}
	public void setSqprdCtgry(String sqprdCtgry) {
		this.sqprdCtgry = sqprdCtgry;
	}
	public String getSqprdNm() {
		return sqprdNm;
	}
	public void setSqprdNm(String sqprdNm) {
		this.sqprdNm = sqprdNm;
	}
	public String getSqprdEngNm() {
		return sqprdEngNm;
	}
	public void setSqprdEngNm(String sqprdEngNm) {
		this.sqprdEngNm = sqprdEngNm;
	}
	public String getSqprdAttnId() {
		return sqprdAttnId;
	}
	public void setSqprdAttnId(String sqprdAttnId) {
		this.sqprdAttnId = sqprdAttnId;
	}
	public String getSqprdNavy() {
		return sqprdNavy;
	}
	public void setSqprdNavy(String sqprdNavy) {
		this.sqprdNavy = sqprdNavy;
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
	public String getSqprdSummaryInfo() {
		return sqprdSummaryInfo;
	}
	public void setSqprdSummaryInfo(String sqprdSummaryInfo) {
		this.sqprdSummaryInfo = sqprdSummaryInfo;
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
	public String getSqprdDetlInfo() {
		return sqprdDetlInfo;
	}
	public void setSqprdDetlInfo(String sqprdDetlInfo) {
		this.sqprdDetlInfo = sqprdDetlInfo;
	}
	public String getSqprdNotiContents() {
		return sqprdNotiContents;
	}
	public void setSqprdNotiContents(String sqprdNotiContents) {
		this.sqprdNotiContents = sqprdNotiContents;
	}
	public String getDepPortCd() {
		return depPortCd;
	}
	public void setDepPortCd(String depPortCd) {
		this.depPortCd = depPortCd;
	}
	public String getArrpPotCd() {
		return arrpPotCd;
	}
	public void setArrpPotCd(String arrpPotCd) {
		this.arrpPotCd = arrpPotCd;
	}
	public String getShipTo() {
		return shipTo;
	}
	public void setShipTo(String shipTo) {
		this.shipTo = shipTo;
	}
	public String getSqprdOpt1() {
		return sqprdOpt1;
	}
	public void setSqprdOpt1(String sqprdOpt1) {
		this.sqprdOpt1 = sqprdOpt1;
	}
	public String getSqprdOpt2() {
		return sqprdOpt2;
	}
	public void setSqprdOpt2(String sqprdOpt2) {
		this.sqprdOpt2 = sqprdOpt2;
	}
	public String getSqprdOpt3() {
		return sqprdOpt3;
	}
	public void setSqprdOpt3(String sqprdOpt3) {
		this.sqprdOpt3 = sqprdOpt3;
	}
	public String getSqprdOpt4() {
		return sqprdOpt4;
	}
	public void setSqprdOpt4(String sqprdOpt4) {
		this.sqprdOpt4 = sqprdOpt4;
	}
	public String getSqprdOpt5() {
		return sqprdOpt5;
	}
	public void setSqprdOpt5(String sqprdOpt5) {
		this.sqprdOpt5 = sqprdOpt5;
	}
	public String getReltdSqprd1() {
		return reltdSqprd1;
	}
	public void setReltdSqprd1(String reltdSqprd1) {
		this.reltdSqprd1 = reltdSqprd1;
	}
	public String getReltdSqprd2() {
		return reltdSqprd2;
	}
	public void setReltdSqprd2(String reltdSqprd2) {
		this.reltdSqprd2 = reltdSqprd2;
	}
	public String getReltdSqprd3() {
		return reltdSqprd3;
	}
	public void setReltdSqprd3(String reltdSqprd3) {
		this.reltdSqprd3 = reltdSqprd3;
	}
	public String getViewTypeImgCcd() {
		return viewTypeImgCcd;
	}
	public void setViewTypeImgCcd(String viewTypeImgCcd) {
		this.viewTypeImgCcd = viewTypeImgCcd;
	}
	public String getViewTypeImgBkgrColorOpt() {
		return viewTypeImgBkgrColorOpt;
	}
	public void setViewTypeImgBkgrColorOpt(String viewTypeImgBkgrColorOpt) {
		this.viewTypeImgBkgrColorOpt = viewTypeImgBkgrColorOpt;
	}
	public String getViewTypeImgBkgrColorCcd() {
		return viewTypeImgBkgrColorCcd;
	}
	public void setViewTypeImgBkgrColorCcd(String viewTypeImgBkgrColorCcd) {
		this.viewTypeImgBkgrColorCcd = viewTypeImgBkgrColorCcd;
	}
	public String getViewTypeImgBkgrColorVal() {
		return viewTypeImgBkgrColorVal;
	}
	public void setViewTypeImgBkgrColorVal(String viewTypeImgBkgrColorVal) {
		this.viewTypeImgBkgrColorVal = viewTypeImgBkgrColorVal;
	}
	public String getViewTypeImgFrom() {
		return viewTypeImgFrom;
	}
	public void setViewTypeImgFrom(String viewTypeImgFrom) {
		this.viewTypeImgFrom = viewTypeImgFrom;
	}
	public String getViewTypeImgTo() {
		return viewTypeImgTo;
	}
	public void setViewTypeImgTo(String viewTypeImgTo) {
		this.viewTypeImgTo = viewTypeImgTo;
	}
	public String getViewTypeImgHashTag() {
		return viewTypeImgHashTag;
	}
	public void setViewTypeImgHashTag(String viewTypeImgHashTag) {
		this.viewTypeImgHashTag = viewTypeImgHashTag;
	}
	public String getViewTypeImgPath() {
		return viewTypeImgPath;
	}
	public void setViewTypeImgPath(String viewTypeImgPath) {
		this.viewTypeImgPath = viewTypeImgPath;
	}
	public String getViewTypeImgOrgFileNm() {
		return viewTypeImgOrgFileNm;
	}
	public void setViewTypeImgOrgFileNm(String viewTypeImgOrgFileNm) {
		this.viewTypeImgOrgFileNm = viewTypeImgOrgFileNm;
	}
	public String getViewTypeImgFileNm() {
		return viewTypeImgFileNm;
	}
	public void setViewTypeImgFileNm(String viewTypeImgFileNm) {
		this.viewTypeImgFileNm = viewTypeImgFileNm;
	}
	public String getViewTypeImgSize() {
		return viewTypeImgSize;
	}
	public void setViewTypeImgSize(String viewTypeImgSize) {
		this.viewTypeImgSize = viewTypeImgSize;
	}
	public String getViewTypeImgAlt() {
		return viewTypeImgAlt;
	}
	public void setViewTypeImgAlt(String viewTypeImgAlt) {
		this.viewTypeImgAlt = viewTypeImgAlt;
	}
	public String getUseYn() {
		return useYn;
	}
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
	public String getUseYnNm() {
		return useYnNm;
	}
	public void setUseYnNm(String useYnNm) {
		this.useYnNm = useYnNm;
	}
	public String getSqprdCtgryNm() {
		return sqprdCtgryNm;
	}
	public void setSqprdCtgryNm(String sqprdCtgryNm) {
		this.sqprdCtgryNm = sqprdCtgryNm;
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
	public String getImgKinds() {
		return imgKinds;
	}
	public void setImgKinds(String imgKinds) {
		this.imgKinds = imgKinds;
	}
	public String[] getListOrdb() {
		return listOrdb;
	}
	public void setListOrdb(String[] listOrdb) {
		this.listOrdb = listOrdb;
	}
	public String[] getListSqprdSeqNo() {
		return listSqprdSeqNo;
	}
	public void setListSqprdSeqNo(String[] listSqprdSeqNo) {
		this.listSqprdSeqNo = listSqprdSeqNo;
	}
	public String getRelsqprdSeqNo1() {
		return relsqprdSeqNo1;
	}
	public void setRelsqprdSeqNo1(String relsqprdSeqNo1) {
		this.relsqprdSeqNo1 = relsqprdSeqNo1;
	}
	public String getRelsqprdSeqNo2() {
		return relsqprdSeqNo2;
	}
	public void setRelsqprdSeqNo2(String relsqprdSeqNo2) {
		this.relsqprdSeqNo2 = relsqprdSeqNo2;
	}
	public String getRelsqprdSeqNo3() {
		return relsqprdSeqNo3;
	}
	public void setRelsqprdSeqNo3(String relsqprdSeqNo3) {
		this.relsqprdSeqNo3 = relsqprdSeqNo3;
	}
	public List<FileUploadVO> getFileList() {
		return fileList;
	}
	public void setFileList(List<FileUploadVO> fileList) {
		this.fileList = fileList;
	}
	public String[] getFileUploadDel() {
		return fileUploadDel;
	}
	public void setFileUploadDel(String[] fileUploadDel) {
		this.fileUploadDel = fileUploadDel;
	}
	public String[] getFileUploadSelect() {
		return fileUploadSelect;
	}
	public void setFileUploadSelect(String[] fileUploadSelect) {
		this.fileUploadSelect = fileUploadSelect;
	}
	public String getGoodsSearchValue() {
		return goodsSearchValue;
	}
	public void setGoodsSearchValue(String goodsSearchValue) {
		this.goodsSearchValue = goodsSearchValue;
	}
	public MultipartFile getViewTypeOrginFile() {
		return viewTypeOrginFile;
	}
	public void setViewTypeOrginFile(MultipartFile viewTypeOrginFile) {
		this.viewTypeOrginFile = viewTypeOrginFile;
	}
	public String getViewTypeFileDel() {
		return viewTypeFileDel;
	}
	public void setViewTypeFileDel(String viewTypeFileDel) {
		this.viewTypeFileDel = viewTypeFileDel;
	}
	public String getViewTypeImgNm() {
		return viewTypeImgNm;
	}
	public void setViewTypeImgNm(String viewTypeImgNm) {
		this.viewTypeImgNm = viewTypeImgNm;
	}
	public List<AdminGoodsVO> getGoodsList() {
		return goodsList;
	}
	public void setGoodsList(List<AdminGoodsVO> goodsList) {
		this.goodsList = goodsList;
	}
	public String getViewTypeImgBkgrColorOptNm() {
		return viewTypeImgBkgrColorOptNm;
	}
	public void setViewTypeImgBkgrColorOptNm(String viewTypeImgBkgrColorOptNm) {
		this.viewTypeImgBkgrColorOptNm = viewTypeImgBkgrColorOptNm;
	}
	public String getViewTypeImgBkgrColorCcdNm() {
		return viewTypeImgBkgrColorCcdNm;
	}
	public void setViewTypeImgBkgrColorCcdNm(String viewTypeImgBkgrColorCcdNm) {
		this.viewTypeImgBkgrColorCcdNm = viewTypeImgBkgrColorCcdNm;
	}
	public String getViewTypeImgCcdNm() {
		return viewTypeImgCcdNm;
	}
	public void setViewTypeImgCcdNm(String viewTypeImgCcdNm) {
		this.viewTypeImgCcdNm = viewTypeImgCcdNm;
	}
	
	
}
