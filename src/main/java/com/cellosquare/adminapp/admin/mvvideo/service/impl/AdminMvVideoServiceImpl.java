package com.cellosquare.adminapp.admin.mvvideo.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.*;
import java.util.concurrent.ThreadPoolExecutor;

import cn.hutool.core.date.DateUtil;
import com.alibaba.ttl.TransmittableThreadLocal;
import com.cellosquare.adminapp.admin.apihistory.entity.ApiHistory;
import com.cellosquare.adminapp.admin.apihistory.service.impl.ApiHistoryServiceImpl;
import com.cellosquare.adminapp.admin.code.service.impl.ApiCodeServiceImpl;
import com.cellosquare.adminapp.admin.videoUploadHistory.entity.MvVideoUploadHistory;
import com.cellosquare.adminapp.admin.videoUploadHistory.service.impl.MvVideoUploadHistoryServiceImpl;
import com.cellosquare.adminapp.common.enums.ApihistoryEnum;
import com.cellosquare.adminapp.common.session.SessionManager;
import com.cellosquare.adminapp.common.util.FileUtil;
import com.cellosquare.adminapp.common.util.S3Utils;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import com.cellosquare.adminapp.admin.mvvideo.mapper.AdminMvVideoMapper;
import com.cellosquare.adminapp.admin.mvvideo.service.AdminMvVideoService;
import com.cellosquare.adminapp.admin.mvvideo.vo.AdminMvVideoVO;
import com.cellosquare.adminapp.admin.video.vo.AdminVideoVO;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;

@Service
public class AdminMvVideoServiceImpl implements AdminMvVideoService {

    private static TransmittableThreadLocal<File> longVideo = new TransmittableThreadLocal<>();
    private static TransmittableThreadLocal<String> longName = new TransmittableThreadLocal<>();
    private static TransmittableThreadLocal<File> shortVideo = new TransmittableThreadLocal<>();
    private static TransmittableThreadLocal<String> shortName = new TransmittableThreadLocal<>();
    private static TransmittableThreadLocal<String> loginName = new TransmittableThreadLocal<>();
    @Autowired
    private ApiHistoryServiceImpl apiHistoryService;
    @Autowired
    private AdminMvVideoMapper adminMvVideoMapper;
    @Resource
    @Qualifier(value = "admin_executor")
    private ThreadPoolTaskExecutor admin_executor;

    @Autowired
    private MvVideoUploadHistoryServiceImpl mvVideoUploadHistoryService;
    @Override
    public int getTotalCount(AdminMvVideoVO vo) {
        return adminMvVideoMapper.getTotalCount(vo);
    }

    @Override
    public List<AdminMvVideoVO> getList(AdminMvVideoVO vo) {
        return adminMvVideoMapper.getList(vo);
    }

    @Override
    public int doWrite(AdminMvVideoVO vo) throws Exception {
        return adminMvVideoMapper.doWrite(vo);
    }

    @Override
    public AdminMvVideoVO getDetail(AdminMvVideoVO vo) {
        return adminMvVideoMapper.getDetail(vo);
    }

    @Override
    public int doUpdate(AdminMvVideoVO vo) throws Exception {
        return adminMvVideoMapper.doUpdate(vo);
    }

    @Override
    public int doDelete(AdminMvVideoVO vo) throws Exception {
        return adminMvVideoMapper.doDelete(vo);
    }

    @Override
    public int doSortOrder(AdminMvVideoVO vo) throws Exception {
        return adminMvVideoMapper.doSortOrder(vo);
    }

    @Override
    public List<AdminVideoVO> getPopUpList(AdminMvVideoVO vo) {
        return adminMvVideoMapper.getPopUpList(vo);
    }

    @Override
    public int popupTotalCount(AdminMvVideoVO vo) {
        return adminMvVideoMapper.popupTotalCount(vo);
    }

    @Override
    public void saveVideo(MultipartHttpServletRequest muServletRequest, AdminMvVideoVO vo) {
        Iterator<String> fileNames = muServletRequest.getFileNames();
        while (fileNames.hasNext()) {
            String _fileName = fileNames.next();
            if (!_fileName.startsWith("video") && !_fileName.startsWith("shortVideo")) {
                continue;
            }
            MultipartFile file = muServletRequest.getFile(_fileName);
            String originalFilename = file.getOriginalFilename();
            File convert = FileUtil.convert(file);
            if (Objects.isNull(convert)) {
                continue;
            }
            if (_fileName.startsWith("video")) {
                longVideo.set(convert);
                longName.set(originalFilename);
            }
            if (_fileName.startsWith("shortVideo")) {
                shortVideo.set(convert);
                shortName.set(originalFilename);
            }
        }
        loginName.set(SessionManager.getAdminSessionForm().getAdminNm());
        admin_executor.execute(() -> {
            MvVideoUploadHistory mvVideoUploadHistory = new MvVideoUploadHistory();
            mvVideoUploadHistory.setVideoTittle(vo.getTitleNm());
            mvVideoUploadHistory.setUploadMan(loginName.get());
            mvVideoUploadHistory.setUploadDate(DateUtil.now());
            try {
                if (StringUtils.isNotEmpty(longName.get())){
                    String url = S3Utils.putObject(longName.get(), longVideo.get());
                    mvVideoUploadHistory.setVideoName(longName.get());
                    mvVideoUploadHistory.setVideoLink(url);
                    longVideo.get().delete();
                    longName.remove();
                    longVideo.remove();
                    vo.setSummaryInfo(url);
                }
                if (StringUtils.isNotEmpty(shortName.get())){
                    String url = S3Utils.putObject(shortName.get(), shortVideo.get());
                    mvVideoUploadHistory.setVideoLink(url);
                    mvVideoUploadHistory.setVideoName(shortName.get());
                    shortVideo.get().delete();
                    shortName.remove();
                    shortVideo.remove();
                    vo.setShortInfo(url);
                }
                this.doUpdate(vo);
                mvVideoUploadHistory.setUploadStatus("成功");
                mvVideoUploadHistoryService.save(mvVideoUploadHistory);
            } catch (Exception e) {
                StringWriter stringWriter = new StringWriter();
                PrintWriter printWriter = new PrintWriter(stringWriter);
                e.printStackTrace(printWriter);
                mvVideoUploadHistory.setFailInfo(stringWriter.toString());
                mvVideoUploadHistory.setUploadStatus("失败");
                mvVideoUploadHistoryService.save(mvVideoUploadHistory);
            }
            loginName.remove();
        });
    }


}
