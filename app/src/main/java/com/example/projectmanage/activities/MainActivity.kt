package com.example.projectmanage.activities

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.SyncStateContract.Constants
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.GravityCompat
import com.bumptech.glide.Glide
import com.example.projectmanage.R
import com.example.projectmanage.databinding.ActivityMainBinding
import com.example.projectmanage.firebase.FireStoreClass
import com.example.projectmanage.models.User
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth

class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: ActivityMainBinding
    var drawer_layout = findViewById<androidx.drawerlayout.widget.DrawerLayout>(R.id.drawer_layout)
    var nav_user_image = findViewById<NavigationView>(R.id.nav_user_image)

    companion object{
        const val MY_PROFILE_REQUEST_CODE : Int = 11
    }
    private lateinit var mUserName: String
    private var mSelectedImageFileUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupActionBar()
        nav_user_image.setNavigationItemSelectedListener(this)
        FireStoreClass().loadUserData(this)
        binding.fabCreateBoard.setOnClickListener {
            startActivity(Intent(this, CreateBoardActivity::class.java))
        }
        if (intent.hasExtra(com.example.projectmanage.utils.Constants.NAME)){
            mUserName = intent.getStringExtra(com.example.projectmanage.utils.Constants.NAME)!!
        }
    }

    private fun setupActionBar() {
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar_main_activity)
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.title = "Action Bar"
            actionBar.setDisplayHomeAsUpEnabled(true)
        }
        toolbar.setNavigationIcon(R.drawable.baseline_menu_24)
        toolbar.setNavigationOnClickListener {
            toggleDrawer()
        }
    }

    private fun toggleDrawer() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            drawer_layout.openDrawer(GravityCompat.START)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            doubleBackToExit()
        }
    }
    fun updateNavigationUserDetails (user: User){
        mUserName = user.name
        Glide
            .with(this@MainActivity)
            .load(user.image)
            .centerCrop()
            .placeholder(R.drawable.ic_user_place_holder)
            .into(nav_user_image)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == MY_PROFILE_REQUEST_CODE){
            FireStoreClass().loadUserData(this)
        }else{
            Log.e("Cancelled","Cancelled")
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_my_profile -> {
                startActivityForResult(Intent(this, MyProfileActivity::class.java),MY_PROFILE_REQUEST_CODE)

            }

            R.id.nav_sign_out -> {
                FirebaseAuth.getInstance().signOut()
                val intent = Intent(this, IntroActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                Toast.makeText(this@MainActivity, "Sign Out", Toast.LENGTH_LONG).show()
                startActivity(intent)
                finish()
            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)

        return true
    }
}
