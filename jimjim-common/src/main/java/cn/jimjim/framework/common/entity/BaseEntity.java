package cn.jimjim.framework.common.entity;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;

import java.io.Serializable;

/**
 * @author jimijm
 */
public interface BaseEntity extends Serializable {

    Long getId();

    BaseEntity setId(Long id);

    default void randomId() {
        setId(IdWorker.getId());
    }

}
