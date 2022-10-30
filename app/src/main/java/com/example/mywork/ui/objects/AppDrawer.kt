package com.example.mywork.ui.objects

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.example.mywork.R
import com.example.mywork.ui.fragments.ContactsFragment
import com.example.mywork.ui.fragments.SettingsFragment
import com.example.mywork.utilits.APP_ACTIVITY
import com.example.mywork.utilits.USER
import com.example.mywork.utilits.replaceFragment
import com.mikepenz.materialdrawer.AccountHeader
import com.mikepenz.materialdrawer.AccountHeaderBuilder
import com.mikepenz.materialdrawer.Drawer
import com.mikepenz.materialdrawer.DrawerBuilder
import com.mikepenz.materialdrawer.model.DividerDrawerItem
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import com.mikepenz.materialdrawer.model.ProfileDrawerItem
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem
import com.mikepenz.materialdrawer.util.DrawerImageLoader
import java.text.FieldPosition

class AppDrawer (){

    private lateinit var result: Drawer
    private lateinit var headerResult: AccountHeader
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var currentProfileDrawerItem: ProfileDrawerItem

    fun create(){
        createHeader()
        createDrawer()
        drawerLayout = result.drawerLayout
    }

    fun disableDrawer(){
        result.actionBarDrawerToggle?.isDrawerIndicatorEnabled = false
        APP_ACTIVITY.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        //возвращаемся из фрагментов назад в главный фрагмент
        APP_ACTIVITY.toolbar.setNavigationOnClickListener{
            APP_ACTIVITY.supportFragmentManager.popBackStack()
        }
    }

    fun enableDrawer(){
        APP_ACTIVITY.supportActionBar?.setDisplayHomeAsUpEnabled(false)
        result.actionBarDrawerToggle?.isDrawerIndicatorEnabled = true
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
        APP_ACTIVITY.toolbar.setNavigationOnClickListener{
            result.openDrawer()
        }

    }

    private fun createDrawer() {
        result = DrawerBuilder()
            .withActivity(APP_ACTIVITY)
            .withToolbar(APP_ACTIVITY.toolbar)
            .withActionBarDrawerToggle(true)
            .withSelectedItem(-1)
            .withAccountHeader(headerResult)
            .addDrawerItems(
                PrimaryDrawerItem()
                    .withIdentifier(100)
                    .withIconTintingEnabled(true)
                    .withName("Create group")
                    .withSelectable(false)
                    .withIcon(R.drawable.ic_baseline_groups_24),
                PrimaryDrawerItem()
                    .withIdentifier(101)
                    .withIconTintingEnabled(true)
                    .withName("My tasks")
                    .withSelectable(false)
                    .withIcon(R.drawable.ic_baseline_work_outline_24),
                PrimaryDrawerItem()
                    .withIdentifier(102)
                    .withIconTintingEnabled(true)
                    .withName("Search worker")
                    .withSelectable(false)
                    .withIcon(R.drawable.ic_baseline_person_search_24),
                DividerDrawerItem(),
                PrimaryDrawerItem()
                    .withIdentifier(103)
                    .withIconTintingEnabled(true)
                    .withName("Settings")
                    .withSelectable(false)
                    .withIcon(R.drawable.ic_baseline_settings_24),
                PrimaryDrawerItem() //also have a position
                    .withIdentifier(104)
                    .withIconTintingEnabled(true)
                    .withName("Supports")
                    .withSelectable(false)
                    .withIcon(R.drawable.ic_baseline_support_agent_24),
            ).withOnDrawerItemClickListener(object :Drawer.OnDrawerItemClickListener{
                override fun onItemClick(
                    view: View?,
                    position: Int,
                    drawerItem: IDrawerItem<*>
                ): Boolean {
                    clickToItem(position)
                    return false
                }
            }).build()

    }

    private fun clickToItem(position: Int){
        when(position){
            3 -> APP_ACTIVITY.replaceFragment(ContactsFragment())
            5 -> APP_ACTIVITY.replaceFragment(SettingsFragment())
        }
    }

    private fun createHeader() {
        currentProfileDrawerItem = ProfileDrawerItem()
            .withName(USER.fullname)
            .withEmail(USER.phone)
            .withIdentifier(200)
        headerResult = AccountHeaderBuilder()
            .withActivity(APP_ACTIVITY)
            .withHeaderBackground(R.drawable.header)
            .addProfiles(
                currentProfileDrawerItem
            ).build()
    }

    fun updateHeader(){
        currentProfileDrawerItem
            .withName(USER.fullname)
            .withEmail(USER.phone)
            .withIdentifier(200)

        headerResult.updateProfile(currentProfileDrawerItem)
    }

    private fun initLoader(){
        DrawerImageLoader
    }

}