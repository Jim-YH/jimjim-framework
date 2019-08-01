package cn.jimjim.framework.common.entity;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;

import java.io.Serializable;

/**
 * @author jimijm
 */
public interface BaseDO extends Serializable {

    Long getId();

    BaseDO setId(Long id);

    default void randomId() {
        setId(IdWorker.getId());
    }

}
