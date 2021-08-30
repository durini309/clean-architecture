package com.durini.cleanarch.di

import com.durini.cleanarch.business.interactors.GetHeroes
import com.durini.cleanarch.business.interactors.HeroInteractors
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import retrofit2.Retrofit

@Module
@InstallIn(ActivityRetainedComponent::class)
object HeroModule {

    @Provides
    fun provideHeroInteractors(
        retrofit: Retrofit
    ) : HeroInteractors {
        return HeroInteractors.build(retrofit)
    }

    @Provides
    fun provideGetHeroes(
        heroInteractors: HeroInteractors
    ) : GetHeroes {
        return heroInteractors.getHeroes
    }

}