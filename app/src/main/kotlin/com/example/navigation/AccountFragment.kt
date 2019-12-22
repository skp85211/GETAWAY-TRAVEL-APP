package com.example.navigation


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_account.view.*

/**
 * A simple [Fragment] subclass.
 */
class AccountFragment : Fragment() {
    companion object{
        lateinit var latitude:Array<String>
        lateinit var longitude:Array<String>
        var begintime=0.0
        var end=0.0
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        /*latitude = arrayOf(
            "28.5562",
            "31.61998",
            "22.54894",
            "28.62654",
            "25.59384",
            "31.61579",
            "31.2307",
            "23.25093",
            "30.65213",
            "30.933121",
            "17.47986",
            "27.577101",
            "25.317644",
            "29.94569",
            "19.01641",
            "31.09848",
            "23.18417",
            "30.743309",
            "22.61071",
            "9.287625",
            "22.26004",
            "34.0837",
            "28.65862",
            "23.263",
            "17.3603",
            "15.5009",
            "15.5056",
            "9.9648",
            "13.012624",
            "28.6286",
            "31.1043",
            "24.696135",
            "25.3716",
            "26.7399",
            "26.00403",
            "13.009711",
            "25.6653332",
            "21.9833",
            "28.6557",
            "28.5881",
            "26.460005",
            "27.175014",
            "28.610001",
            "18.9283",
            "26.9210",
            "17.361431",
            "27.1743",
            "26.9255",
            "22.5449",
            "20.0264",
            "26.996471",
            "28.553558",
            "10.521062",
            "28.5244",
            "28.6229",
            "28.5933",
            "26.2389",
            "73.0243",
            "28.6143",
            "28.656158",
            "19.08956",
            "32.2978",
            "26.911661",
            "26.2182991",
            "15.334022",
            "18.963253",
            "23.4802",
            "",
            "",
            "28.55265",
            "28.68214",
            "28.63243",
            "18.94519",
            "18.95712",
            "18.9943",
            "",
            "",
            "15.569446",
            "15.5686",
            "15.2993",
            "18.4762",
            "15.5758",
            "",
            "13.6967",
            "",
            "",
            "",
            "12.3122",
            "12.8728",
            "12.972442",
            "12.88123",
            "31.95809",
            "28.55022",
            "28.63243",
            "28.6472",
            "28.55671",
            "28.63789",
            "28.71348",
            "28.465561",
            "28.58467",
            "28.68119",
            "28.61492",
            "28.723787",
            "28.535517",
            "28.6144",
            "28.5241",
            "28.58997",
            "28.701969",
            "28.6224",
            "28.55265"
        )*/

        latitude = arrayOf(
            "28.5562",
            "31.61998",
            "29.987153",
            "28.62654",
            "25.59384",
            "28.656006",
            "31.2307",
            "31.234682",
            "19.152398",
            "30.112868",
            "19.766307",
            "25.310873",
            "29.94569",
            "19.01641",
            "26.166520",
            "34.215878",
            "30.743309",
            "19.804912",
            "9.287625",
            "22.26004",
            "34.129150",
            "28.650714",
            "23.263",
            "17.3603",
            "15.5009",
            "15.5056",
            "9.9648",
            "13.012624",
            "28.6286",
            "31.1043",
            "24.695192",
            "25.3716",
            "26.7399",
            "26.004001",
            "13.009978",
            "25.6653332",
            "21.9833",
            "28.6557",
            "28.5881",
            "26.460005",
            "27.175014",
            "28.610001",
            "18.9283",
            "26.924041",
            "17.361431",
            "27.1743",
            "26.9255",
            "22.5449",
            "20.025968",
            "26.985639",
            "28.553558",
            "15.500900",
            "28.524483",
            "28.6229",
            "28.5933",
            "26.2389",
            "28.609698",
            "28.6143",
            "28.656158",
            "19.08956",
            "32.2978",
            "26.911661",
            "26.231388",
            "15.334022",
            "18.963253",
            "23.4802",
            "28.467928",
            "28.55022",
            "28.63243",
            "28.6472",
            "28.561443",
            "28.613901",
            "28.541232",
            "28.504801",
            "28.58467",
            "28.68119",
            "28.61492",
            "28.723787",
            "28.535517",
            "28.6144",
            "28.528649",
            "28.58997",
            "28.701969",
            "28.6224",
            "28.55265",
            "15.043889",
            "15.172457",
            "15.671155",
            "15.257225",
            "15.517515",
            "19.797392",
            "17.71445",
            "19.096976",
            "18.955459",
            "19.141054"
        )


        /*longitude = arrayOf(
            "77.1000",
            "74.876488",
            "88.27238",
            "77.20944",
            "85.21609",
            "31.61579",
            "76.5005",
            "77.42733",
            "76.39727",
            "75.887367",
            "78.53775",
            "77.694801",
            "82.973915",
            "78.164246",
            "72.83176",
            "97.34375",
            "77.41716",
            "79.493767",
            "88.3716",
            "79.312927",
            "70.790916",
            "74.7974",
            "77.23075",
            "77.3928",
            "78.4734",
            "73.9116",
            "73.915",
            "76.2428",
            "80.226933",
            "77.2068",
            "77.1758",
            "84.986954",
            "83.0252",
            "83.887",
            "85.082008",
            "76.102898",
            "78.4609323",
            "74.85",
            "77.2361",
            "77.2384",
            "74.629799",
            "78.042152",
            "77.232147",
            "72.83083",
            "75.8227",
            "78.474533",
            "78.0195",
            "75.8236",
            "88.3425",
            "75.1792",
            "75.876472",
            "77.259132",
            "76.218208",
            "77.1852",
            "77.2099",
            "77.2506",
            "73.0243",
            "77.245",
            "77.1998",
            "77.24102",
            "72.865616",
            "78.0119",
            "70.922928",
            "78.1828188",
            "76.468536",
            "72.931442",
            "77.7401",
            "",
            "",
            "77.201752",
            "77.19873",
            "77.21879",
            "72.82641",
            "72.81053",
            "72.8243",
            "",
            "",
            "73.742247",
            "73.7589",
            "74.124",
            "77.8939",
            "73.7668",
            "",
            "123.48605",
            "",
            "",
            "",
            "77.2741",
            "77.3353",
            "77.580643",
            "77.493134",
            "35.94581",
            "77.25889",
            "77.21879",
            "77.1267",
            "77.20979",
            "77.09461",
            "77.13527",
            "77.35201",
            "77.35201",
            "77.14311",
            "77.22173",
            "77.113548",
            "77.391029",
            "77.1977",
            "77.2181",
            "77.217644",
            "77.227272",
            "77.2217",
            "77.201752"
        )*/

        longitude = arrayOf(
            "77.1000",
            "74.876488",
            "75.078565",
            "77.20944",
            "85.21609",
            "77.232546",
            "76.5005",
            "76.505440",
            "77.318849",
            "78.303303",
            "74.477175",
            "83.010678",
            "78.164246",
            "72.83176",
            "91.705151",
            "75.504088",
            "79.493767",
            "85.817880",
            "79.312927",
            "70.790916",
            "74.842562",
            "77.233426",
            "77.3928",
            "78.4734",
            "73.9116",
            "73.915",
            "76.2428",
            "80.226933",
            "77.2068",
            "77.1758",
            "84.986899",
            "83.0252",
            "83.887",
            "85.081697",
            "76.100414",
            "78.4609323",
            "74.85",
            "77.2361",
            "77.2384",
            "74.629799",
            "78.042152",
            "77.232147",
            "72.83083",
            "75.826712",
            "78.474533",
            "78.0195",
            "75.8236",
            "88.3425",
            "75.177955",
            "75.851324",
            "77.259132",
            "73.911631",
            "77.185522",
            "77.2099",
            "77.2506",
            "73.0243",
            "77.243731",
            "77.1998",
            "77.24102",
            "72.865616",
            "78.0119",
            "70.922928",
            "78.169481",
            "76.468536",
            "72.931442",
            "77.7401",
            "77.068886",
            "77.25889",
            "77.21879",
            "77.1267",
            "77.243745",
            "77.208957",
            "77.155166",
            "77.096995",
            "77.35201",
            "77.14311",
            "77.22173",
            "77.113548",
            "77.391029",
            "77.1977",
            "77.219590",
            "77.217644",
            "77.227272",
            "77.2217",
            "77.201752",
            "73.985882",
            "73.942226",
            "73.709581",
            "73.918856",
            "73.762504",
            "85.834053",
            "83.323571",
            "72.826583",
            "72.811300",
            "72.803348"
        )

        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_account, container, false)
        val airport = v.airport
        val starttime=v.starttime
        var time= arrayOf(1.0,2.0,3.0,4.0,5.0,6.0,7.0,8.0,9.0,10.0,11.0,12.0,13.0,14.0,15.0,16.0,17.0,18.0,19.0,20.0,21.0,22.0,23.0,24.0)
        starttime.adapter=ArrayAdapter<Double>(this.requireContext(),android.R.layout.simple_list_item_1,time)
        starttime.onItemSelectedListener=object :AdapterView.OnItemSelectedListener
        {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                Toast.makeText(context,"Select Preffered Start Time..",Toast.LENGTH_LONG).show()
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                begintime=time.get(position)

                Log.d("STARTTIME","Start time="+ begintime)

            }

        }
        val endtime=v.endtime
        endtime.adapter=ArrayAdapter<Double>(this.requireContext(),android.R.layout.simple_list_item_1,time)
        endtime.onItemSelectedListener=object :AdapterView.OnItemSelectedListener
        {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                Toast.makeText(context,"Select Preffered End Time..",Toast.LENGTH_LONG).show()
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                end=time.get(position)
                Log.d("ENDTIME","END time="+ end)

            }

        }
        airport.setOnClickListener {
            Toast.makeText(context,"AIRPORT SAVED..",Toast.LENGTH_LONG).show()
            var radiogroup = v.radiogroup
            var radioid = radiogroup.checkedRadioButtonId
            if (radioid == v.delhi.id) {
                AccountFragment.latitude[0] = "28.5562";
                longitude[0] = "77.1000"
                if(!ListFragment.namelist.contains("DELHI AIRPORT"))
                    ListFragment.namelist.add(0,"DELHI AIRPORT")
            } else if (radioid == v.mumbai.id) {
                latitude[0] = "19.0896";
                longitude[0] = "72.8656"
                if(!ListFragment.namelist.contains("MUMBAI AIRPORT AIRPORT"))
                    ListFragment.namelist.add(0,"MUMBAI AIRPORT")

            } else if (radioid == v.bangalore.id) {
                latitude[0] = "13.1986";
                longitude[0] = "77.7066"
                if(!ListFragment.namelist.contains("BANGALORE AIRPORT"))
                    ListFragment.namelist.add(0,"BANGALORE AIRPORT")

            } else {
                latitude[0] = "22.6433"
                longitude[0] = "88.4394"
                if(!ListFragment.namelist.contains("KOLKATA AIRPORT"))
                    ListFragment.namelist.add(0,"KOLKATA AIRPORT")
            }
            Log.d("AIRPORT", "AIR LAT=" + latitude[0])
            Log.d("AIRPORT", "AIR LONG=" +longitude[0])
        }
        return v
    }



}
