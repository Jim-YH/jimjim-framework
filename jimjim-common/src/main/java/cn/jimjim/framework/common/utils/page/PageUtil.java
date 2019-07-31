package cn.jimjim.framework.common.utils.page;

import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @author qiaoxiaozhuo 2019/05/10
 */
public class PageUtil {

    public static <T> PageVO<T> calculate(IPage page) {
        PageVO<T> viewPageInfo = new PageVO<>();
        viewPageInfo.setData(page.getRecords());
        viewPageInfo.setPageNo(page.getCurrent());
        viewPageInfo.setPageSize(page.getSize());
        viewPageInfo.setTotal(page.getPages());
        viewPageInfo.setCount(page.getTotal());
        viewPageInfo.setHasNextPage(page.getPages() == page.getCurrent());
        return viewPageInfo;
    }


    public static <T> PageVO<T> calculate(IPage page, List<T> records) {
        PageVO<T> viewPageInfo = new PageVO<>();
        viewPageInfo.setData(records);
        viewPageInfo.setPageNo(page.getCurrent());
        viewPageInfo.setPageSize(page.getSize());
        viewPageInfo.setTotal(page.getPages());
        viewPageInfo.setCount(page.getTotal());
        viewPageInfo.setHasNextPage(page.getPages() == page.getCurrent());
        return viewPageInfo;
    }
}
