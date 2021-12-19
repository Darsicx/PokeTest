package com.android.testclip.data.local.room

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

class RoomMigrations {

    companion object {
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE Pokemon ADD status INTEGER NOT NULL DEFAULT 0")
            }
        }
    }
}