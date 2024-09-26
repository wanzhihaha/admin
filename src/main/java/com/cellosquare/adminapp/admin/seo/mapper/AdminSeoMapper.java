package com.cellosquare.adminapp.admin.seo.mapper;

import com.cellosquare.adminapp.admin.seo.vo.AdminSeoVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface AdminSeoMapper {

    public int doSeoWrite(AdminSeoVO vo);

    public AdminSeoVO getSeoSelect(AdminSeoVO vo);

    public int doSeoDelete(AdminSeoVO vo);

    public int doSeoUpdate(AdminSeoVO vo);

    @Select("select * from MK_META")
    List<AdminSeoVO> selectAll(String ktitle);

    @Update("update MK_META set meta_title_nm = #{ktitle}   where meta_seq_no  = #{meta_seq_no}")
    void updateTitle(@Param("ktitle") String ktitle, @Param("meta_seq_no")Integer meta_seq_no);
}
