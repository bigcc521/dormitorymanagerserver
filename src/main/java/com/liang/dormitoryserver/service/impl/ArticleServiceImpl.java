package com.liang.dormitoryserver.service.impl;

import com.liang.dormitoryserver.entity.Article;
import com.liang.dormitoryserver.mapper.ArticleMapper;
import com.liang.dormitoryserver.service.ArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lq
 * @since 2021-01-14
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

}
