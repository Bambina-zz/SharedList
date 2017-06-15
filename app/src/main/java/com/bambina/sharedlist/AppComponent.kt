package com.bambina.sharedlist


import com.bambina.sharedlist.adapter.TaskRecyclerAdapter
import com.bambina.sharedlist.fragment.TaskListFragment
import dagger.Component
import javax.inject.Singleton
/**
 * Created by hirono-mayuko on 2017/06/05.
 */

@Component(modules = arrayOf( NetworkModule::class ))
@Singleton
interface AppComponent {
    fun api(): SharedListApi

    fun inject(holder : TaskRecyclerAdapter.ViewHolder)

    fun inject(fragment : TaskListFragment)
}
