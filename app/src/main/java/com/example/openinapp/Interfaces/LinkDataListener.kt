package com.example.openinapp.Interfaces

import com.example.openinapp.Model.link

//THIS INTERFACE IS CALLED WHEN RECENT_LINKS OR TOP_LINKS DATA IS SUCCESSFULLY FETCHED
interface LinkDataListener {
    fun onLinkFetch(list:ArrayList<link>)
}