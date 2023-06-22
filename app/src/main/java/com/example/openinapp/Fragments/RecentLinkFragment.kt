package com.example.openinapp.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.openinapp.Interfaces.LinkDataListener
import com.example.openinapp.MainActivity
import com.example.openinapp.R
import com.example.openinapp.LinkAdapter
import com.example.openinapp.Model.link

class RecentLinkFragment : Fragment() ,LinkDataListener{
    var linkDataList:ArrayList<link> = ArrayList<link>()
    lateinit var linkAdapter: LinkAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view:View=inflater.inflate(R.layout.fragment_recent_link, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recentLinkRV)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)

        linkAdapter= LinkAdapter(context, linkDataList)
        recyclerView.adapter = linkAdapter

        MainActivity.linkDataListenerRef1 =this
        return view
    }

    override fun onLinkFetch(list: ArrayList<link>) {
//        Toast.makeText(activity,"in frag size"+list.get(0).getUrl_id(),Toast.LENGTH_SHORT).show()
        for(i in list){
            linkDataList.add(i)
        }
        linkAdapter.notifyDataSetChanged()
    }

}