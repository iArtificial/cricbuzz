package com.sportz.interactive.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sportz.interactive.data.db.dao.MatchDao
import com.sportz.interactive.data.db.entity.Inning
import com.sportz.interactive.data.db.entity.Team
import com.sportz.interactive.data.db.entity.typeconvertor.*
import com.sportz.interactive.data.network.response.Match


@Database(
    entities = [Inning::class, Match::class, Team::class],
    version = 1
)
@TypeConverters(
    TeamsMapTypeConverter::class,
    StringConverter::class,
    BatsmenConverter::class,
    BowlerConverter::class,
    FallofWicketConverter::class,
    PlayersMapTypeConverter::class,
    InningsConverter::class
)
abstract class CricbuzzDatabase : RoomDatabase() {
    abstract fun cricketDao(): MatchDao

    companion object {
        @Volatile
        private var instance: CricbuzzDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                CricbuzzDatabase::class.java, "cricbuzz.db"
            )
                .build()
    }
}