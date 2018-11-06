package com.shushan.homework101.banner;


public interface HolderCreator<VH extends ViewHolder> {
    /**
     * 创建ViewHolder
     * @return
     */
    public VH createViewHolder();
}
