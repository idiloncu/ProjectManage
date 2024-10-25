package com.example.projectmanage.activities

import android.net.Uri
import android.os.Bundle
import com.example.projectmanage.R
import com.example.projectmanage.models.Board

class CreateBoardActivity : BaseActivity() {
    private var mSelectedImageFileUri: Uri? = null
    private lateinit var mUserName: String
    private var mBoardImageURL: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_board)
        var toolbar_my_profile_activity = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar_my_profile_activity)


    }
    private fun createBoard(){
        val assignedUsersArrayList: ArrayList<String> = ArrayList()
        assignedUsersArrayList.add(getCurrentUserID())

        var board = Board(
            et_board_name.text.toString(),
            mBoardImageURL,
            mUserName,
            assignedUsersArrayList
        )
        FireStoreClass().createBoard(this,board)
    }
    private fun uploadBoardImage(){
        showProgressDialog(resources.getString(R.string.please_wait))
    }

    fun boardCreatedSuccessfully(){
        hideProgressDialog()
        finish()
    }

    private fun setupActionBar(){
        setSupportActionBar(toolbar_my_profile_activity)
        val actionBar = supportActionBar
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.baseline_arrow_back_ios_24)
            actionBar.title = resources.getString(R.string.my_profile)
        }
        toolbar_my_profile_activity.setNavigationOnClickListener { onBackPressed() }

    }
}