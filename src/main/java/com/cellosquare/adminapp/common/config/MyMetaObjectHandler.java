package com.cellosquare.adminapp.common.config;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.cellosquare.adminapp.common.session.AdminSessionForm;
import com.cellosquare.adminapp.common.session.SessionManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        AdminSessionForm sessionForm = SessionManager.getAdminSessionForm();
        this.strictInsertFill(metaObject, "createPersionId", () -> sessionForm.getAdminNm(), String.class);
        this.strictInsertFill(metaObject, "insPersonId", () -> sessionForm.getAdminNm(), String.class);
        this.strictInsertFill(metaObject, "createDate", () -> new Timestamp(System.currentTimeMillis()), Timestamp.class);
        this.strictInsertFill(metaObject, "insDtm", () -> new Timestamp(System.currentTimeMillis()), Timestamp.class);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        AdminSessionForm sessionForm = SessionManager.getAdminSessionForm();
        this.strictUpdateFill(metaObject, "updatePersionId", () -> sessionForm.getAdminNm(), String.class);
        this.strictUpdateFill(metaObject, "updPersonId", () -> sessionForm.getAdminNm(), String.class);
        this.strictUpdateFill(metaObject, "updateDate", () -> new Timestamp(System.currentTimeMillis()), Timestamp.class);
        this.strictUpdateFill(metaObject, "updDtm", () -> new Timestamp(System.currentTimeMillis()), Timestamp.class);
    }
}
