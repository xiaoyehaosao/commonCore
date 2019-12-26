package com.xyhs.common.tools;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ljp
 * @apiNote
 * @date 15:17 2019/12/26
 **/
public abstract class BaseEntityWrapper<E, V> {

    public BaseEntityWrapper() {
    }

    /**
     * 对象转换
     * @param entity  要转换的实体类
     * @return 转换的结果实体类
     */
    abstract V entityVO(E entity);

    private List<V> listVO(List<E> list) {
        return list.stream().map(this::entityVO).collect(Collectors.toList());
    }

    public IPage<V> pageVO(IPage<E> pages) {
        List<V> records = this.listVO(pages.getRecords());
        IPage<V> pageVo = new Page<>(pages.getCurrent(), pages.getSize(), pages.getTotal());
        pageVo.setRecords(records);
        return pageVo;
    }
}
