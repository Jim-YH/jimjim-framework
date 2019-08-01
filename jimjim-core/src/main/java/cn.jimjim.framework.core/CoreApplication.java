package cn.jimjim.framework.core;

import cn.jimjim.framework.core.config.RedisConfig;
import org.springframework.context.annotation.Import;


/**
 * @author jim
 * @date 2019-07-31
 */
@Import(RedisConfig.class)
public class CoreApplication {

}
