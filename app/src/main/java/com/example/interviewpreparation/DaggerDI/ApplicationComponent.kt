package com.example.interviewpreparation.DaggerDI

import com.example.interviewpreparation.MainActivity
import com.example.interviewpreparation.RoomDB.Fragment.AddFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface ApplicationComponent {

    fun inject(mainActivity: MainActivity)

    fun inject(mainActivity: AddFragment)



}