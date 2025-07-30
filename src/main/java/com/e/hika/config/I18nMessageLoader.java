package com.e.hika.config;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.e.hika.mapper.I18nMessageMapper;
import com.e.hika.pojo.I18nMessage;
import jakarta.annotation.Resource;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class I18nMessageLoader implements ApplicationRunner {

    @Resource
    private I18nMessageMapper i18nMessageMapper;

    public static final Map<String , Map<String,String>> i18nCache = new ConcurrentHashMap<>();

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<I18nMessage> i18nMessages = i18nMessageMapper.selectList(new LambdaQueryWrapper<I18nMessage>());

        i18nMessages.forEach(msg
                -> {
            i18nCache.computeIfAbsent(msg.getLocale(),k->new ConcurrentHashMap<>())
                    .put(msg.getCode(),msg.getMessage());
        });

        System.out.println("國際化消息加載完成，供： "+i18nCache.size());

    }
}
