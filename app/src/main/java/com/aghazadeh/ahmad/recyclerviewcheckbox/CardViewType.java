package com.aghazadeh.ahmad.recyclerviewcheckbox;

import jp.satorufujiwara.binder.ViewType;
/**
 * Created by 890683 on 7/13/2016.
 */
public enum CardViewType implements ViewType {

    CardCehckBox ;

    @Override
    public int viewType() {
        return ordinal();
    }

}