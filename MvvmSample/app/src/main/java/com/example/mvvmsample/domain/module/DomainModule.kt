package com.example.mvvmsample.domain.module

import com.example.mvvmsample.data.module.RoomDataModule
import com.example.mvvmsample.data.persistence.EmailDao
import com.example.mvvmsample.data.repository.EmailRoomRepository
import com.example.mvvmsample.domain.repository.EmailRepository
import com.example.mvvmsample.domain.usecase.CheckEmailUseCase
import com.example.mvvmsample.domain.usecase.DeleteEmailUseCase
import com.example.mvvmsample.domain.usecase.GetEmailUseCase
import com.example.mvvmsample.domain.usecase.SaveEmailUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [RoomDataModule::class])
class DomainModule {

    @Provides
    fun provideSaveEmailUseCase(emailRepository: EmailRepository): SaveEmailUseCase =
            SaveEmailUseCase(emailRepository)

    @Provides
    fun provideGetEmailUseCase(emailRepository: EmailRepository): GetEmailUseCase =
            GetEmailUseCase(emailRepository)

    @Provides
    fun provideDeleteEmailUseCase(emailRepository: EmailRepository): DeleteEmailUseCase =
            DeleteEmailUseCase(emailRepository)

    @Provides
    fun provideCheckEmailUseCase(): CheckEmailUseCase =
            CheckEmailUseCase()

    @Provides
    fun provideEmailRoomRepository(emailDao: EmailDao): EmailRepository =
            EmailRoomRepository(emailDao)

}
