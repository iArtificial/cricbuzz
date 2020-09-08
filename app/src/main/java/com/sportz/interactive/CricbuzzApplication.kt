package com.sportz.interactive

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import com.sportz.interactive.data.YahooCricketApiService
import com.sportz.interactive.data.db.CricbuzzDatabase
import com.sportz.interactive.data.network.ConnectivityInterceptor
import com.sportz.interactive.data.network.ConnectivityInterceptorImpl
import com.sportz.interactive.data.network.MatchDataSource
import com.sportz.interactive.data.network.MatchDataSourceImpl
import com.sportz.interactive.data.repository.MatchRepository
import com.sportz.interactive.data.repository.MatchRepositoryImpl
import com.sportz.interactive.ui.match.MatchViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class CricbuzzApplication : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@CricbuzzApplication))

        bind() from singleton { CricbuzzDatabase(instance()) }
        bind() from singleton { instance<CricbuzzDatabase>().cricketDao() }
        bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
        bind() from singleton { YahooCricketApiService(instance()) }
        bind<MatchDataSource>() with singleton { MatchDataSourceImpl(instance()) }
        bind<MatchRepository>() with singleton { MatchRepositoryImpl(instance(), instance()) }
        bind() from provider { MatchViewModelFactory(instance()) }

    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }
}