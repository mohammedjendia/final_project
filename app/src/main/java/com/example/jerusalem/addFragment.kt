package com.example.jerusalem

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*
import java.io.ByteArrayOutputStream


class addFragment : Fragment() {
    var URI = ""
    var database :FirebaseFirestore?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        val root = inflater.inflate(R.layout.fragment_add, container, false)

        // --  --  --  --



         database = Firebase.firestore
        val storage = Firebase.storage
        val storageReference = storage.reference
        val childReference = storageReference.child("photos")


        root.imageForAdd.setOnClickListener {
            val i = Intent()
            i.action = Intent.ACTION_PICK
            i.type = "image/*"
            startActivityForResult(i, 100)
        }


        root.buttonAdd.setOnClickListener {

            Thread{
                val b = (root.imageForAdd.drawable as BitmapDrawable).bitmap
                val bb = ByteArrayOutputStream()
                b.compress(Bitmap.CompressFormat.PNG, 100, bb)
                val data = bb.toByteArray()

                val imageReference1 = childReference.child(System.currentTimeMillis().toString() + ".png")
                val uploadTask = imageReference1.putBytes(data)
                uploadTask.addOnFailureListener { exception ->
                    Log.e("jendia",exception.message.toString())
                }.addOnSuccessListener {

                    imageReference1.downloadUrl.addOnSuccessListener { uri ->
                        val title = root.titleOfSubject.text.toString()
                        val details = root.DetailsOfSubject.text.toString()

                        var c = ""
                        if (root.radioA7ya2.isChecked){
                            c ="أحياء مدينة القدس"
                        }else if (root.radioMa3alem.isChecked){
                            c ="معالم مدينة القدس"

                        }else if (root.radioMakana.isChecked){
                            c ="مكانة القدس الدينية"

                        }else if (root.radioTare5.isChecked){
                            c ="تاريخ القدس"
                        }

                        saveImage(title,details,uri.toString(),c)


                    }


                    Log.e("jendia","success to upload image")
         //           saveImage(root.)

                }}.start()


            }
        return root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == 100) {
            URI = data!!.data.toString()
            imageForAdd.setImageURI(data!!.data)
        }
    }
    fun saveImage(titleofsubjectt:String , DetailsofSubjectt : String ,imageforadd : String , categoryyiD :String ){
            val images= hashMapOf(
                "titleofsubjectt" to titleofsubjectt ,
                "DetailsofSubjectt" to DetailsofSubjectt ,
                "imageforadd" to imageforadd ,
                "categoryyiD" to categoryyiD
            )
    database!!.collection("report").add(images).addOnSuccessListener {documentReference ->

Toast.makeText(activity,"نجح في رفع الصورة",Toast.LENGTH_LONG).show()
}.addOnFailureListener { exception ->
        Toast.makeText(activity,"فشل في رفع الصورة",Toast.LENGTH_LONG).show()
}








        }
}