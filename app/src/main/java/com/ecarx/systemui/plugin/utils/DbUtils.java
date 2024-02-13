package com.ecarx.systemui.plugin.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


/* loaded from: classes.dex */
public class DbUtils {
    private static String DB_NAME = "shortCut";
    private static String DbDirectiryPath = "/storage/emulated/0/Android/data/ecarx.launcher3/cache";
//    private static SQLiteDB helper;

    private DbUtils() {
    }

//    public static SQLiteDB getInstance(Context context) {
//        Log.e("DbUtils", "===========>SQLiteDB:" + context.getExternalCacheDir().getPath());
//        if (helper == null) {
//            synchronized (DbUtils.class) {
//                if (helper == null) {
//                    SQLiteDBConfig sQLiteDBConfig = new SQLiteDBConfig(context);
//                    sQLiteDBConfig.setDbName(DB_NAME);
//                    sQLiteDBConfig.setVersion(1);
//                    sQLiteDBConfig.setDbDirectoryPath(DbDirectiryPath);
//                    sQLiteDBConfig.setDbListener(new IDBListener() { // from class: com.ecarx.systemui.plugin.utils.DbUtils.1
//                        @Override // cn.ittiger.database.listener.IDBListener
//                        public void onDbCreateHandler(SQLiteDatabase sQLiteDatabase) {
//                        }
//
//                        @Override // cn.ittiger.database.listener.IDBListener
//                        public void onUpgradeHandler(SQLiteDatabase sQLiteDatabase, int i, int i2) {
//                        }
//                    });
//                    helper = SQLiteDBFactory.createSQLiteDB(sQLiteDBConfig);
//                }
//            }
//        }
//        return helper;
//    }
}
