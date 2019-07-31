package cn.jimjim.framework.common.utils.page;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author qiaoxiaozhuo 2019/05/10
 */
@Data
public class PageVO<T>{

    private Long pageNo;

    private Long pageSize;

    private List<T> data;

    /**
     * 当前分页总页数
     *
     * @return 总页数
     */
    private Long total;

    /**
     * 当前满足条件总行数
     *
     * @return 总条数
     */
    private Long count;

    /**
     * 是否有下一页
     *
     * @return true/false
     */
    private Boolean hasNextPage;

}
