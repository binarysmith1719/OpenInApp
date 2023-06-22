package com.example.openinapp.API

import android.content.Context
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.openinapp.Interfaces.dataFetchSuccessful
import com.example.openinapp.Model.link
import com.jjoe64.graphview.series.DataPoint
import org.json.JSONArray
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class FetchData private constructor(){
    lateinit var interfaceRef:dataFetchSuccessful
    companion object {

        @Volatile
        private var instance: FetchData?=null

        fun getInstance(): FetchData {
            synchronized(this) {
                if (instance == null) {
                    instance = FetchData()

                }
                return instance as FetchData
            }

        }
    }


    val sdf = SimpleDateFormat("yyyy-MM-dd")

    fun getData(context:Context,reference:dataFetchSuccessful) {
        interfaceRef=reference

        val jsonObjectRequest = object : JsonObjectRequest(
            Request.Method.GET, "https://api.inopenapp.com/api/v1/dashboardNew", null,
            { response ->
                var apiResponse=ApiResponse();
                apiResponse.recentLinksList= ArrayList()
                apiResponse.topLinksList= ArrayList()
//                Toast.makeText(context,"Got Data",Toast.LENGTH_SHORT).show()
                //GOT THE JSON OBJECT
                apiResponse.topSource= response.getString("top_source")
                apiResponse.topLocation=response.getString("top_location")
                apiResponse.totalClicks=response.getInt("total_clicks")
                apiResponse.totalLinks=response.getInt("total_links")
                apiResponse.todaysClick=response.getInt("today_clicks")
                apiResponse.extIncome=response.getDouble("extra_income")
                apiResponse.appliedCampaign=response.getInt("applied_campaign")
                apiResponse.linksCreatedToday=response.getInt("links_created_today")
                apiResponse.strtTime=response.getString("startTime")

                val jsonObject=response.getJSONObject("data")
                val jsonArray=jsonObject.getJSONArray("recent_links")
                for(i in 0 until jsonArray.length())
                {
                    val obj=jsonArray.getJSONObject(i)
                    var url_id=obj.getInt("url_id")
                    var web_link=obj.getString("web_link")
                    var smart_link=obj.getString("smart_link")
                    var title=obj.getString("title")
                    var totalClicks=obj.getInt("total_clicks")
                    var original_image=obj.getString("original_image")
                    var thumbnail=obj.getString("thumbnail")
                    var times_ago=obj.getString("times_ago")
                    var created_at=obj.getString("created_at")
                    var domain_id=obj.getString("domain_id")
                    var url_prefix=obj.getString("url_prefix")
                    var url_suffix=obj.getString("url_suffix")
                    var app=obj.getString("app")
                    var linkObj = link(
                        url_id,
                        web_link,
                        smart_link,
                        title,
                        totalClicks,
                        original_image,
                        thumbnail,
                        times_ago,
                        created_at,
                        domain_id,
                        url_prefix,
                        url_suffix,
                        app
                    )
                    apiResponse.recentLinksList.add(linkObj)
                }

                val jsonArray2=jsonObject.getJSONArray("top_links")
                for(i in 0 until jsonArray2.length())
                {
                    val obj=jsonArray2.getJSONObject(i)
                    var url_id=obj.getInt("url_id")
                    var web_link=obj.getString("web_link")
                    var smart_link=obj.getString("smart_link")
                    var title=obj.getString("title")
                    var totalClicks=obj.getInt("total_clicks")
                    var original_image=obj.getString("original_image")
                    var thumbnail=obj.getString("thumbnail")
                    var times_ago=obj.getString("times_ago")
                    var created_at=obj.getString("created_at")
                    var domain_id=obj.getString("domain_id")
                    var url_prefix=obj.getString("url_prefix")
                    var url_suffix=obj.getString("url_suffix")
                    var app=obj.getString("app")
                    var linkObj = link(
                        url_id,
                        web_link,
                        smart_link,
                        title,
                        totalClicks,
                        original_image,
                        thumbnail,
                        times_ago,
                        created_at,
                        domain_id,
                        url_prefix,
                        url_suffix,
                        app
                    )
                    apiResponse.topLinksList.add(linkObj)
                }

                //HANDLING GRAPH
                val jsonObject_Graph= jsonObject.getJSONObject("overall_url_chart")
                var key = jsonObject_Graph.names() as JSONArray
                var maxx:Long=0
                apiResponse.dataPoints = arrayOfNulls<DataPoint>(key.length())

                for(i in 0 until key.length())
                {
                    var dateString = key.getString(i)
                    var value=jsonObject_Graph.getInt(dateString)

                    var date: Date?=null
                    try { date = sdf.parse(dateString)
                    } catch (e: ParseException) { e.printStackTrace() }
                    val d: Long = date!!.time
                    maxx=d
                    apiResponse.dataPoints[i]=DataPoint(d.toDouble(),value.toDouble())
                }
                apiResponse.maxim=maxx.toDouble()
                apiResponse.minim=maxx.toDouble()-2628000000.0
            interfaceRef.onSuccess(apiResponse)
            },
            { error ->

            }
        ) {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val headers = HashMap<String, String>()
                headers.put("Authorization","Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MjU5MjcsImlhdCI6MTY3NDU1MDQ1MH0.dCkW0ox8tbjJA2GgUx2UEwNlbTZ7Rr38PVFJevYcXFI");
                return headers
            }
        }

        val requestQueue = Volley.newRequestQueue(context)
        requestQueue.add(jsonObjectRequest)
    }

}