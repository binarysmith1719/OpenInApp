package com.example.openinapp.API

import com.example.openinapp.Model.link
import com.jjoe64.graphview.series.DataPoint

class ApiResponse {
    //For graph
    var dataPoints = arrayOfNulls<DataPoint>(0)
    var minim:Double?=0.0
    var maxim:Double?=0.0

    lateinit var recentLinksList:ArrayList<link>
    lateinit var topLinksList:ArrayList<link>
    var extIncome:Double?=0.0
    var totalLinks:Int?=0
    var totalClicks:Int?=0
    var todaysClick:Int?=0
    var appliedCampaign:Int?=0
    var linksCreatedToday:Int?=0
    lateinit var topSource:String
    lateinit var topLocation:String
    lateinit var strtTime:String

}