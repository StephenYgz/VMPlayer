package com.itheima.vmplayer.utils;

import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.database.Cursor;
import android.widget.CursorAdapter;

/**
 * Created by wschun on 2016/12/23.
 */

public class MyQueryHandler extends AsyncQueryHandler {

    public MyQueryHandler(ContentResolver cr) {
        super(cr);
    }

    /**
     * 查询结束
     * @param token
     * @param cookie
     * @param cursor
     */
    @Override
    protected void onQueryComplete(int token, Object cookie, Cursor cursor) {

         if (token==0 && cookie instanceof CursorAdapter){
             CommUtils.printCursor(cursor);
             MyCursorAdapter myCursorAdapter= (MyCursorAdapter) cookie;
             myCursorAdapter.swapCursor(cursor);
         }

    }
}
