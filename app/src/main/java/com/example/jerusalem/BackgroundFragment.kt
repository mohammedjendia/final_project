package com.example.jerusalem

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_background.view.*


class BackgroundFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_background, container, false)
        val bundle = Bundle()
        val j = JerusalemMainFragment()
        //1 news
        // 2 ma3alem
        //3 History
        // 4  el e7ya2
        //5 el makana el deneya


        root.btnNews.setOnClickListener {
            bundle.putInt("bbb",1)
            j.arguments = bundle
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.container,j).addToBackStack("null").commit()
        }
        root.btnMaalem.setOnClickListener {
            bundle.putInt("bbb",2)
            j.arguments = bundle
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.container,j).addToBackStack("null").commit()
        }
        root.btnHistory.setOnClickListener {
            bundle.putInt("bbb",3)
            j.arguments = bundle
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.container,j).addToBackStack("null").commit()
        }
        root.btnstreets.setOnClickListener {
            bundle.putInt("bbb",4)
            j.arguments = bundle
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.container,j).addToBackStack("null").commit()
        }
        root.btnMakana.setOnClickListener {
            bundle.putInt("bbb",5)
            j.arguments = bundle
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.container,j).addToBackStack("null").commit()
        }
        root.btnAdd.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.container,addFragment()).addToBackStack("null").commit()

        }
        root.btnVideos.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.container,videos()).addToBackStack("null").commit()

        }


        return root
    }
}