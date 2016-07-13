package com.aghazadeh.ahmad.recyclerviewcheckbox;

import jp.satorufujiwara.binder.Section;

/**
 * Created by 890683 on 7/13/2016.
 */
public enum TitleSection implements Section {
    SECTION_1;
    @Override
    public int position() {
        return ordinal();
    }
}