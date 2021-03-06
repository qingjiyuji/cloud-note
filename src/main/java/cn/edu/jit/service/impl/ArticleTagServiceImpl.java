package cn.edu.jit.service.impl;

import cn.edu.jit.entry.Article;
import cn.edu.jit.entry.ArticleTagExample;
import cn.edu.jit.entry.ArticleTagKey;
import cn.edu.jit.entry.TagExample;
import cn.edu.jit.mapper.ArticleTagMapper;
import cn.edu.jit.service.ArticleTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author jitwxs
 * @date 2018/1/3 13:40
 */
@Service
public class ArticleTagServiceImpl implements ArticleTagService {

    @Autowired
    ArticleTagMapper articleTagMapper;

    @Override
    public ArticleTagKey getByArticleIdAndTagId(String articleId, String tagId) {
        ArticleTagExample articleTagExample = new ArticleTagExample();

        ArticleTagExample.Criteria criteria = articleTagExample.createCriteria();
        criteria.andArticleIdEqualTo(articleId);
        criteria.andTagIdEqualTo(tagId);

        List<ArticleTagKey> lists = articleTagMapper.selectByExample(articleTagExample);
        if(lists.size() == 0) {
            return null;
        } else {
            return lists.get(0);
        }
    }

    @Override
    public int save(ArticleTagKey articleTagKey) {
        return articleTagMapper.insertSelective(articleTagKey);
    }

    @Override
    public int remove(ArticleTagKey articleTagKey) {
        return articleTagMapper.deleteByPrimaryKey(articleTagKey);
    }

    @Override
    public int removeAllByArticleId(String articleId) {
        ArticleTagExample articleTagExample = new ArticleTagExample();

        ArticleTagExample.Criteria criteria = articleTagExample.createCriteria();
        criteria.andArticleIdEqualTo(articleId);

        return articleTagMapper.deleteByExample(articleTagExample);
    }

    @Override
    public List<ArticleTagKey> listByArticleId(String articleId) {
        ArticleTagExample articleTagExample = new ArticleTagExample();

        ArticleTagExample.Criteria criteria = articleTagExample.createCriteria();
        criteria.andArticleIdEqualTo(articleId);

        return articleTagMapper.selectByExample(articleTagExample);
    }
}
