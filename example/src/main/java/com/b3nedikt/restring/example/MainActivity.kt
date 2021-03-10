package com.b3nedikt.restring.example

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.ContentFrameLayout
import androidx.core.view.GravityCompat
import dev.b3nedikt.restring.Restring
import dev.b3nedikt.reword.Reword
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity() {

    private lateinit var toogle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        APP_LOCALES.forEach { locale ->
            Restring.putStrings(locale, SampleStringsGenerator.getStrings(locale))
        }

        val localeStrings = APP_LOCALES.map { it.language + " " + it.country }
        val adapter = ArrayAdapter(this,
                android.R.layout.simple_dropdown_item_1line, localeStrings)

        spinner.adapter = adapter

        toogle = ActionBarDrawerToggle(
                this, drawerLayout, topAppBar, R.string.open, R.string.close)

        topAppBar.setNavigationOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        navigationView.setNavigationItemSelectedListener { menuItem ->
            // Handle menu item selected
            menuItem.isChecked = true
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
    }

    override fun onResume() {
        super.onResume()

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Restring.locale = APP_LOCALES[position]

                val rootView = window.decorView.findViewById<ContentFrameLayout>(android.R.id.content)
                Reword.reword(rootView)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) = Unit
        }
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        toogle.syncState()
    }
}
