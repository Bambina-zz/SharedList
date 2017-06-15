package com.bambina.sharedlist.activity


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.bambina.sharedlist.R
import com.bambina.sharedlist.fragment.ErrandListFragment
import com.bambina.sharedlist.fragment.TaskListFragment
import com.bambina.sharedlist.model.Errand

class MainActivity : AppCompatActivity() {

    private val TAG_ERRAND_FRAGMENT : String = "errandFragment"
    private val TAG_TASK_FRAGMENT : String = "taskFragment"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Creating mock data.
        val errands : Array<Errand> = arrayOf(Errand(id = "0", name = "買い物", shared_hash = "3441433b-81df-4092-aa31-54863797afb4"),
                Errand(id = "1", name = "お使い", shared_hash = "42218140-9578-47ce-9442-6c0ba0ef59ac"),
                Errand(id = "2", name = "買い物", shared_hash = "3441433b-81df-4092-aa31-54863797afb4"))

        val t = supportFragmentManager.beginTransaction()
        val f = ErrandListFragment(errands, this)
        t.add(R.id.container, f, TAG_ERRAND_FRAGMENT)
        t.commit()
    }

    fun addTaskFragment(sharedHash : String){
        val f = TaskListFragment(sharedHash)

        supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, f, TAG_TASK_FRAGMENT)
                .addToBackStack(null)
                .commit()
    }
}
