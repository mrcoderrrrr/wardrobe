package com.example.wardrobe.activity

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.provider.MediaStore
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.wardrobe.R
import com.example.wardrobe.adapter.ViewPagerAdapterBottom
import com.example.wardrobe.adapter.ViewPagerAdapterTop
import com.example.wardrobe.database.BottomImageDatabase
import com.example.wardrobe.database.FavComboDatabase
import com.example.wardrobe.database.TopImageDatabase
import com.example.wardrobe.databinding.ActivityMainBinding
import com.example.wardrobe.model.BottomImageModel
import com.example.wardrobe.model.FavComboModel
import com.example.wardrobe.model.TopImageModel


open class MainActivity : AppCompatActivity() {
    private lateinit var dataBinding: ActivityMainBinding
    private lateinit var imageTop: ArrayList<TopImageModel>
    private lateinit var imageBottom: ArrayList<BottomImageModel>
    private lateinit var favCombo:ArrayList<FavComboModel>
    private lateinit var dialog: Dialog
    private lateinit var pickImageTop: ActivityResultLauncher<Intent>
    private lateinit var pickImageBottom: ActivityResultLauncher<Intent>
    private lateinit var openCamera: ActivityResultLauncher<Intent>
    private var pickImageTopModel: TopImageModel? = null
    private var camImageTopModel: TopImageModel? = null
    private var pickImageBottomModel: BottomImageModel? = null
    private var camImageBottomModel: BottomImageModel? = null
    private var favComboModel: FavComboModel? = null
    private var resultTop: String? = null
    private var resultBottom: String? = null


    private var chechFav: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewPagerTop()
        viewPagerBottom()
        setClick()
    }


    //set Click
    private fun setClick() {
        //top
        pickImageTop()
        openCameraTop()
        //bottom
        pickImageBottom()
        openCameraBottom()
        //favourite
        chechCombo()
    }

    private fun chechCombo() {
        dataBinding.btnFav.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                chechFav = 1
                dialogFavCombo()
                Toast.makeText(this, "CheckedTrue$chechFav", Toast.LENGTH_LONG).show()
            } else {
                chechFav = 0
                Toast.makeText(this, "CheckedFalse$chechFav", Toast.LENGTH_LONG).show()
            }
        })
        //        if (dataBinding.btnFav.isChecked==true){
//            chechFav=1
//            Toast.makeText(this,"CheckedTrue"+chechFav,Toast.LENGTH_LONG).show()
//        }
//        else if (dataBinding.btnFav.isChecked==false){
//            chechFav=0
//            Toast.makeText(this,"CheckedFalse"+chechFav,Toast.LENGTH_LONG).show()
//        }
    }

    private fun savedFavCombo() {
        for (i in 0 until imageTop.size) {
            for (j in 0 until imageBottom.size)
                favComboModel =
                    FavComboModel(0, chechFav, imageTop[i].imagePath, imageBottom[j].imagePath)
            FavComboDatabase.getInstance(this)?.favComboDao()?.userInsert(favComboModel!!)
        }
    }


    //Pick Image
    private fun pickImageTop() {
        addImageTop()
        pickImageTop = registerForActivityResult(ActivityResultContracts.StartActivityForResult(),
            ActivityResultCallback {
                pickImageTopModel = TopImageModel(0, it?.data?.data.toString())
                TopImageDatabase.getInstance(this)?.topImageDao()?.userInsert(pickImageTopModel!!)
            })
    }

    //Add Top Image
    private fun addImageTop() {
        //Open Dialog box for top image
        dataBinding.btnAddTop.setOnClickListener {
            dialogBoxTop()
        }
    }

    //Open Camera
    private fun openCameraTop() {
        openCamera = registerForActivityResult(ActivityResultContracts.StartActivityForResult(),
            ActivityResultCallback {
                resultTop = it.data?.data.toString()
                camImageTopModel = TopImageModel(0, resultTop!!)
                TopImageDatabase.getInstance(this)?.topImageDao()?.userInsert(camImageTopModel!!)
            })
    }

    //pick image from bottom
    private fun pickImageBottom() {
        addImageBottom()
        pickImageBottom =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult(),
                ActivityResultCallback {
                    resultBottom = it?.data?.data.toString()
                    pickImageBottomModel = BottomImageModel(0, resultBottom!!)
                    BottomImageDatabase.getInstance(this)?.bottomImageDao()
                        ?.userInsert(pickImageBottomModel!!)
                })
    }

    //add Image from Bottom
    private fun addImageBottom() {
        dataBinding.btnAddBottom.setOnClickListener {
            dialogBoxBottom()
        }
    }


    private fun openCameraBottom() {
        openCamera = registerForActivityResult(ActivityResultContracts.StartActivityForResult(),
            ActivityResultCallback {
                camImageBottomModel = BottomImageModel(0, it?.data?.data.toString())
                BottomImageDatabase.getInstance(this)?.bottomImageDao()?.userInsert(
                    camImageBottomModel!!
                )
            })
    }

    //dialog box for combo
    private fun dialogFavCombo() {
        dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_fav_combo)
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCancelable(true)
        val yes: TextView = dialog.findViewById(R.id.tvYes)
        val no: TextView = dialog.findViewById(R.id.tvNo)

        yes.setOnClickListener {
            savedFavCombo()
            dialog.dismiss()
        }
        no.setOnClickListener {
            dataBinding.btnFav.isChecked=false
            //deleteCombo()
            dialog.dismiss()
        }
        dialog.show()
    }
//delete fav combo
//    private fun deleteCombo() {
//        favCombo= ArrayList()
//        favCombo= FavComboDatabase.getInstance(this)?.favComboDao()?.userViewData() as ArrayList<FavComboModel>
//        for (i in 0 until favCombo.size) {
//                FavComboDatabase.getInstance(this)!!.favComboDao().userDeleteData(favCombo[i].id)
//        }
//    }

    //Dialog Box
    private fun dialogBoxTop() {
        dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_pick_image)
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCancelable(true)
        val pickImg: TextView = dialog.findViewById(R.id.tvPickImage)
        val openCam: TextView = dialog.findViewById(R.id.tvOpenCam)
        val cancel: TextView = dialog.findViewById(R.id.tvCancel)
        dialog.show()

        //PickImage from Gallery
        pickImg.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            pickImageTop.launch(intent)
            dialog.dismiss()
        }

        //open camera
        openCam.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            pickImageTop.launch(intent)
            dialog.dismiss()
        }

        //cancel dialog
        cancel.setOnClickListener {
            dialog.dismiss()
        }
    }

    //dialog box bottom
    private fun dialogBoxBottom() {
        dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_pick_image)
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCancelable(true)
        val pickImg: TextView = dialog.findViewById(R.id.tvPickImage)
        val openCam: TextView = dialog.findViewById(R.id.tvOpenCam)
        val cancel: TextView = dialog.findViewById(R.id.tvCancel)
        dialog.show()

        //PickImage from Gallery
        pickImg.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            pickImageBottom.launch(intent)
            dialog.dismiss()
        }

        //open camera
        openCam.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            pickImageTop.launch(intent)
            dialog.dismiss()
        }

        //cancel dialog
        cancel.setOnClickListener {
            dialog.dismiss()
        }
    }

    //Viewpager Top
    @SuppressLint("NotifyDataSetChanged")
    private fun viewPagerTop() {
        imageTop = ArrayList()
        imageTop = TopImageDatabase.getInstance(this)?.topImageDao()!!
            .userViewData() as ArrayList<TopImageModel>
        val viewPagerAdapterTop = ViewPagerAdapterTop(this, imageTop)
        dataBinding.vTop.adapter = viewPagerAdapterTop
        viewPagerAdapterTop.notifyDataSetChanged()
    }

    //ViewPager Bottom
    @SuppressLint("NotifyDataSetChanged")
    private fun viewPagerBottom() {
        imageBottom = ArrayList()
        imageBottom = BottomImageDatabase.getInstance(this)?.bottomImageDao()!!
            .userViewData() as ArrayList<BottomImageModel>
        val viewPagerAdapterBottom = ViewPagerAdapterBottom(this, imageBottom)
        dataBinding.vBottom.adapter = viewPagerAdapterBottom
        viewPagerAdapterBottom.notifyDataSetChanged()
    }
}