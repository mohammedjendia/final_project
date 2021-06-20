package com.example.jerusalem

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_jerusalem_main.*

class JerusalemMainFragment : Fragment() {
    var database :FirebaseFirestore? = null
        val url = "https://newsapi.org/v2/everything?q=%D8%A7%D9%84%D9%82%D8%AF%D8%B3&from=2021-06-09&to=2021-06-14&sortBy=popularity&apiKey=f81fc7ea05e3430daa56ba3e35daf7d8"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_jerusalem_main, container, false)

        database = Firebase.firestore

        val bundel = arguments
        var bbb :Int? =null
        if (bundel != null){
        bbb=    bundel.getInt("bbb",1)
        }

        Toast.makeText(activity,"$bbb",Toast.LENGTH_LONG).show()
        var c = ""
        if (bbb == 1){
            getJsonObject()
        }else if (bbb in 2..5){

            if (bbb == 2){
                c ="معالم مدينة القدس"

            }else if(bbb ==3){
                c= "تاريخ القدس"

            }else if(bbb == 4){
                c= "أحياء مدينة القدس"
            }else if(bbb == 5){
                c =  "مكانة القدس الدينية"
            }

        getImage(c)
            // get data from firebase

        }
        return root
    }

    private fun getImage(c: String) {
        //titleofsubjectt:String , DetailsofSubjectt : String ,imageforadd : String , categoryyiD :String
        ////    "مكانة القدس الدينية"  "أحياء مدينة القدس"   "معالم مدينة القدس"   "تاريخ القدس"
        val docs = mutableListOf<Jerusalem>()
        database!!.collection("report").whereEqualTo("categoryyiD",c).get().addOnSuccessListener {querySnapshot ->
            for(doc in querySnapshot){
                var t = doc.getString("titleofsubjectt")!!
                var d = doc.getString("DetailsofSubjectt")!!
                var i = doc.getString("imageforadd")!!
                docs.add(Jerusalem(doc.id,c,t,d,i))
            }

            val adapter = JerusalemAdapter(requireActivity(),docs)
            rvJerusalem.adapter = adapter
            rvJerusalem.layoutManager = LinearLayoutManager(requireActivity())

        }.addOnFailureListener { exception ->

        }

    }

    private fun getJsonObject(){

        val json = object: JsonObjectRequest(Method.GET,url,null,
            Response.Listener { response ->
                Log.e("jendia",response.toString())
                Log.e("jendia", "oooooooooooooo")

                val news = response.getJSONArray("articles")
                val data = ArrayList<Jerusalem>()

                for(i in 0 until news.length()) {
                    val newsJsonObject = news.getJSONObject(i)

                    val id = "$i"
                    val category = "1"
                    val title = newsJsonObject.getString("title")
                    val details = newsJsonObject.getString("description")
                    val image =  newsJsonObject.getString("urlToImage")

                    data.add(Jerusalem(id,category,title,details,image))
                }
                val adapter = JerusalemAdapter(requireActivity(),data)
                rvJerusalem.adapter = adapter
                rvJerusalem.layoutManager = LinearLayoutManager(requireActivity())
            },
            Response.ErrorListener { error ->
                Log.e("jendia", "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee")
                Log.e("jendia", error.message.toString())
            })  {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["User-Agent"] = "Mozilla/5.0"
                return headers
            }
        }

        mySingleton.getInstance()!!.addRequestQueue(json)
    }
    
}