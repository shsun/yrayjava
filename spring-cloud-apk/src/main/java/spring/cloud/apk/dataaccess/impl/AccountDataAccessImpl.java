package spring.cloud.apk.dataaccess.impl;

import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Repository;
import spring.cloud.apk.config.GlobalCacheHelper;
import spring.cloud.apk.dataaccess.AccountDataAccess;
import spring.cloud.apk.dataaccess.dataobject.AccountDo;
import spring.cloud.apk.dataaccess.mapper.AccountDoMapper;
import spring.cloud.demo.cache.CacheService;

import java.util.Optional;

/**
 * Created by Harry on 2017/6/19.
 */
@Repository("userDataAccess")
@RefreshScope
public class AccountDataAccessImpl implements AccountDataAccess {

    @Autowired
    private CacheService cacheService;

    @Autowired
    private AccountDoMapper accountDoMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountDataAccessImpl.class);
    private static final String FIXED_ACCOUNT_CACHE_PREFIX = "spring#cloud#demo#apk#userId#";
    // 30min
    private static final int CACHE_EXPIRE_TIME = 30 * 60;


    @Override
    public int insert(AccountDo record) {
        return this.accountDoMapper.insert(record);
    }

    @Override
    public Optional<AccountDo> selectByPrimaryKey(String userId) {
        Optional<AccountDo> rst;
        if (Strings.isNullOrEmpty(userId)) {
            rst = Optional.empty();
        } else {
            String cacheKey = this.getCacheKeyByUserId(userId);
            AccountDo accountDo = null;
            try {
                accountDo = (AccountDo) this.cacheService.getObject(cacheKey);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (null != accountDo) {
                rst = Optional.of(accountDo);
            } else {
                accountDo = this.accountDoMapper.selectByPrimaryKey(userId);
                if (null != accountDo) {
                    try {
                        this.cacheService.putObject(cacheKey, accountDo, CACHE_EXPIRE_TIME);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                rst = Optional.ofNullable(accountDo);
            }
        }
        return rst;
    }

    @Override
    public int updateByPrimaryKeySelective(AccountDo record) {
        int size = this.accountDoMapper.updateByPrimaryKeySelective(record);
        if (size > 0) {
            this.deleteCacheByUserId(record.getUserId());
        }
        return size;
    }

    private String getCacheKeyByUserId(String userId) {
        return FIXED_ACCOUNT_CACHE_PREFIX + userId;
    }

    private void deleteCacheByUserId(String userId) {
        String cacheKey = this.getCacheKeyByUserId(userId);
        try {
            this.cacheService.deleteObjectByKey(cacheKey);
        } catch (Exception e) {
            e.printStackTrace();
            GlobalCacheHelper.putDeleteKeys(cacheKey);
        }
    }

}
