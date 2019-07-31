package cn.jimjim.framework.datasource.config.mybatis;

import cn.hutool.core.date.SystemClock;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

import java.time.*;
import java.util.Date;

public class MybatisMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {


        // version
        Object version = getFieldValByName("version", metaObject);
        if (version == null) {
            setFieldValByName("version", 1, metaObject);
        }

        // status
        Object status = getFieldValByName("status", metaObject);
        if (status == null) {
            setFieldValByName("status", 1, metaObject);
        }

        LocalDateTime now = LocalDateTime.now();
        // create_time
        setFieldValByName("createTime", now, metaObject);

        // created
//        String creator = authUser == null ? Thread.currentThread().getName() : authUser.getUsername();
//        creator = StrUtil.subPre(creator, 20);
//        setFieldValByName("created", creator, metaObject);

        // updated
//        setFieldValByName("updated", creator, metaObject);

        // update_time
        setFieldValByName("updateTime", now, metaObject);

    }

    @Override
    public void updateFill(MetaObject metaObject) {

//        LocalDateTime dateTime = LocalDateTime.ofInstant(LocalDateTime.now().toInstant(ZoneOffset.ofHours(8)), ZoneId.systemDefault());
        // update_time
        setFieldValByName("updateTime", LocalDateTime.now(), metaObject);

        // updated
//        AuthUser authUser = AuthHolder.authUser();
//        String updator = authUser == null ? Thread.currentThread().getName() : authUser.getUsername();
//        updator = StrUtil.subPre(updator, 20);
//        setFieldValByName("updated", updated, metaObject);
    }
}
