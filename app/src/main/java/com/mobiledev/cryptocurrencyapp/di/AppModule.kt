package com.mobiledev.cryptocurrencyapp.di

import com.mobiledev.cryptocurrencyapp.common.Constants
import com.mobiledev.cryptocurrencyapp.data.remote.CoinPaprikaApi
import com.mobiledev.cryptocurrencyapp.data.repository.CoinRepositoryImp
import com.mobiledev.cryptocurrencyapp.domain.repository.CoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePaprikaApi():CoinPaprikaApi{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoinPaprikaApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinRepository(api:CoinPaprikaApi) : CoinRepository{
        return CoinRepositoryImp(api)
    }

}