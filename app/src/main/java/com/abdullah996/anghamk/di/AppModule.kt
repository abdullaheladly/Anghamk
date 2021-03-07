package com.abdullah996.anghamk.di

import android.app.Application
import android.content.Context
import com.abdullah996.anghamk.R
import com.abdullah996.anghamk.adabters.SwipeSongAdapter
import com.abdullah996.anghamk.exoplayer.MusicServiceConnection
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object AppModule {


    @Singleton
    @Provides
    fun provideMusicServiceConnection(
            @ApplicationContext context: Context
    )=MusicServiceConnection(context)


    @Singleton
    @Provides
    fun provideSwipeSongAdabter()=SwipeSongAdapter()

    @Singleton
    @Provides
    fun provideGlideInstance(
        @ApplicationContext context:Context,
    )=Glide.with(context).setDefaultRequestOptions(
        RequestOptions()
            .placeholder(R.drawable.ic_image)
            .error(R.drawable.ic_image)
            .diskCacheStrategy(DiskCacheStrategy.DATA)
    )
}