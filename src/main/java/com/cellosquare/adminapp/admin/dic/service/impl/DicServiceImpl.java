package com.cellosquare.adminapp.admin.dic.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.file.FileReader;
import com.cellosquare.adminapp.admin.dic.entity.Dic;
import com.cellosquare.adminapp.admin.dic.mapper.DicMapper;
import com.cellosquare.adminapp.admin.dic.service.IDicService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.library.DicLibrary;
import org.ansj.splitWord.analysis.DicAnalysis;
import org.ansj.splitWord.analysis.IndexAnalysis;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.ansj.util.MyStaticValue;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author walker
 * @since 2023-04-115 21:47:12
 */
@Service
public class DicServiceImpl extends ServiceImpl<DicMapper, Dic> implements IDicService {
    private static final AtomicBoolean isFlush = new AtomicBoolean(false);

    @PostConstruct
    public static void loadMarketingDic(){
        FileReader fileReader = new FileReader("classpath:marketing_dic.txt");
        List<String> strings = fileReader.readLines();
        for (String string : strings) {
            DicLibrary.insert(DicLibrary.DEFAULT,string,"n",DicLibrary.DEFAULT_FREQ);
        }
    }

    /**
     * 刷新词库
     */
    public void flush() {
        List<Dic> list = lambdaQuery().list();
        if (CollectionUtil.isNotEmpty(list)) {
            for (Dic dic : list) {
                DicLibrary.insert(DicLibrary.DEFAULT,dic.getDicName(),"n",DicLibrary.DEFAULT_FREQ);
            }
        }
    }

    /**
     * 分词
     * @param text
     * @return
     */
    public List<String> parse(String text) {
        if (!isFlush.get()) {
            this.flush();
            isFlush.set(true);
        }
        MyStaticValue.isNameRecognition = false;
        Result parse = IndexAnalysis.parse(text);
        return parse.getTerms().stream().map(Term::getName).collect(Collectors.toList());
    }

}
