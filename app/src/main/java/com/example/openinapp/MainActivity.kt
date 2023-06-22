package com.example.openinapp

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.openinapp.API.ApiResponse
import com.example.openinapp.API.FetchData
import com.example.openinapp.Interfaces.LinkDataListener
import com.example.openinapp.Interfaces.dataFetchSuccessful
import com.google.android.material.tabs.TabLayout
import com.jjoe64.graphview.DefaultLabelFormatter
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.LineGraphSeries
import kotlinx.android.synthetic.main.activity_main.*
import java.text.Format
import java.text.SimpleDateFormat
import java.util.Calendar

class MainActivity : AppCompatActivity() , dataFetchSuccessful{
    lateinit var txt:TextView
    lateinit var progressBar:ProgressBar
    lateinit var graphView: GraphView
    lateinit var fetchDataInstance: FetchData;

    //THESE LISTENERS WILL BE USED WHEN NEW LINKS (RECENT/TOP) ARE AVAILABLE
    companion object{
        var linkDataListenerRef1:LinkDataListener?=null;
        var linkDataListenerRef2:LinkDataListener?=null;
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tabLayout = findViewById<TabLayout>(R.id.tabl)
        val viewPager = findViewById<ViewPager>(R.id.viewpagerl)

        viewPager.adapter= PagerFragmentAdapter(supportFragmentManager)
        tabLayout.setupWithViewPager(viewPager)


        progressBar=findViewById(R.id.progbar)
        progressBar.visibility= View.VISIBLE

        //GRAPH CHART-------------------------------------------------------------------------------
        graphView= findViewById<GraphView>(R.id.graph)
        graphView.title = "OverView"
        graphView.titleColor = R.color.black
        graphView.titleTextSize = 40f
        graphView.gridLabelRenderer.labelFormatter = object : DefaultLabelFormatter() {
            override fun formatLabel(value: Double, isValueX: Boolean): String {
                if (isValueX) {
                    val formatter: Format = SimpleDateFormat("dd/MM")
                    return formatter.format(value)
                }
                return super.formatLabel(value, isValueX)
            }
        }
        graphView.viewport.scrollToEnd()
        graphView.viewport.isXAxisBoundsManual = true //giving X axis bound
        graphView.viewport.isScalable = true // activate horizontal zooming and scrolling
        graphView.viewport.isScrollable = true // activate horizontal scrolling
        graphView.viewport.setScalableY(true) // activate horizontal and vertical zooming and scrolling
        graphView.viewport.setScrollableY(true)
        //GRAPH CHART-------------------------------------------------------------------------------

        getGreeting()

        fetchDataInstance= FetchData.getInstance()
        fetchDataInstance.getData(this,this)
    }

    //CALLED WHEN SUCCESSFULLY FETCHED THE DATA
    //HERE THE PROGRESS BAR IS SET TO INVISIBLE
    override fun onSuccess(apiResponse: ApiResponse) {
        progressBar.visibility= View.INVISIBLE
        linkDataListenerRef1!!.onLinkFetch(apiResponse.recentLinksList)
        linkDataListenerRef2!!.onLinkFetch(apiResponse.topLinksList)

        todaysClick.setText(apiResponse.todaysClick.toString())
        totalClicks.setText(apiResponse.totalClicks.toString())
        location.setText(apiResponse.topLocation)
        source.setText(apiResponse.topSource)
        income.setText(apiResponse.extIncome.toString()+" Rs")
        links.setText(apiResponse.totalLinks.toString())
        campaign.setText(apiResponse.appliedCampaign.toString())
        time.setText(apiResponse.strtTime)

        val series=LineGraphSeries(apiResponse.dataPoints)
        series.isDrawBackground=true
        graphView.viewport.setMinX(apiResponse.minim!!)
        graphView.viewport.setMaxX(apiResponse.maxim!!)
        graphView.addSeries(series)
    }

    fun getGreeting()
    {
        var cal=Calendar.getInstance()
        var hour=cal.get(Calendar.HOUR_OF_DAY)
        if(hour<12)
            greeting.setText("Good Morning")
        else if(hour>=12 && hour<16)
            greeting.setText("Good Afternoon")
        else
            greeting.setText("Good Evening")
    }
}