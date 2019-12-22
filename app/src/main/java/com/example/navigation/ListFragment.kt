package com.example.navigation


import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.fragment_list.view.*
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

/**
 * A simple [Fragment] subclass.
 */

class ListFragment : Fragment(){
    companion object{
        var idlist=ArrayList<Int>()
        var namelist=ArrayList<String>()
        var durationlist=ArrayList<Int>()

        lateinit var md:Array<IntArray>
        lateinit var mt:Array<IntArray>

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var insertUrl = "http://192.168.43.206:8080/Myapi/delete.php"
        val v=inflater.inflate(R.layout.fragment_list, container, false)
        listapi().getplaces().enqueue(object: Callback<List<place>> {
            override fun onFailure(call: Call<List<place>>, t: Throwable) {
                Toast.makeText(context,t.message, Toast.LENGTH_LONG).show()

            }

            override fun onResponse(call: Call<List<place>>, response: Response<List<place>>) {
                val places=response.body()
                places?.let {
                    places(it)

                }
            }
        })
        val trip=v.trip
        trip.setOnClickListener{
            if(!ListFragment.idlist.contains(0))
            idlist.add(0,0)
            if(!ListFragment.durationlist.contains(0))
                durationlist.add(0,0)


        if(idlist.size>0) {
            var i = 0
            do {
                var id = idlist[i];
                Log.d("ID", "ID=" + id)
                Log.d("SIZE","SIZE="+ idlist.size)
                i++
            } while (i <idlist.size)
        }
        else{
            Log.d("ID","EMPTY")
        }
            if(namelist.size>0) {
                var i = 0
                do {
                    var name = namelist[i];
                    Log.d("name", "name=" + name)
                    Log.d("SIZE","SIZE="+ namelist.size)
                    i++
                } while (i < namelist.size)
            }
            else{
                Log.d("ID","EMPTY")
            }
            if(durationlist.size>0) {
                var i = 0
                do {
                    var dur = durationlist[i];
                    Log.d("duration", "duration=" + dur)
                    Log.d("SIZE","SIZE="+ durationlist.size)
                    i++
                } while (i < durationlist.size)
            }
            else{
                Log.d("ID","EMPTY")
            }

            //var x= intArrayOf(idlist.size+1)
            //var y= intArrayOf(idlist.size+1)
             md = Array(idlist.size + 1) { IntArray(idlist.size + 1) }
             mt = Array(idlist.size + 1) { IntArray(idlist.size + 1) }
            //md=arrayOf(x,y)
            //mt= arrayOf(x,y)
            var i = 0
            while (i < idlist.size) {
                var j = 0
                while (j < idlist.size) {
                    if (i === j) {
                        md[i][j] = 1
                        mt[i][j] = 1
                        //System.out.println("0");
                    } else {
                        var url =
                            "https://maps.googleapis.com/maps/api/distancematrix/json?units=metric&origins="
                        url =
                            url + AccountFragment.latitude[idlist[i]] + "%2C" + AccountFragment.longitude[idlist[i]] + "&destinations=" + AccountFragment.latitude[idlist[j]] + "%2C" + AccountFragment.longitude[idlist[j]]
                        url=url+"&key=YOUR KEY"
                        Log.d("URL","URL="+url)
                        val si = Integer.toString(i)
                        val sj = Integer.toString(j)
                        //var c=getActivity()
                        Toast.makeText(context,"Fetching distances...",Toast.LENGTH_LONG).show()
                        var geo = GeoTask()
                        var stringTemp = geo.execute(url).get()
                        val res = stringTemp.split(",").toTypedArray()
                        val min = Integer.parseInt(res[0]) / 60
                        val dist = Integer.parseInt(res[1]) / 1000
                        mt[i][j]=min
                        md[i][j]=dist


                        Log.d("Checking: ",stringTemp.toString())
                        Log.d("md[i][j]", "distancel: " + md[i][j])
                        Log.d("mt[i][j]", "Durationl: " + mt[i][j])
                    }
                        //Log.d("md[i][j]", "distancel: " + md[i][j])
                        //Log.d("mt[i][j]", "Durationl: " + mt[i][j])
                    j++


                }
                i++
            }



        }
        val f=v.fab
        f.setOnClickListener{

            var j = 0
            var index=0
            if (listAdapter.checkedplaces.size > 0) {
                do {
                    var checked = listAdapter.checkedplaces[j]
                    var sb = checked.name
                    var k=0
                    while(k< idlist.size){
                        if(checked.id== idlist[k])
                        {
                            index=k
                            break
                        }
                        k++
                    }
                    idlist.remove(checked.id)
                    namelist.remove(checked.name)
                    durationlist.removeAt(index)

                    val s = Sender(context, insertUrl, sb)
                    s.execute()
                    j++

                } while (j < listAdapter.checkedplaces.size)
            }
            else
            {
                Toast.makeText(context, "Please Check An Item First", Toast.LENGTH_SHORT).show()
            }
            var i=0
        }
        return v
    }
//********************************************************
    inner class GeoTask : AsyncTask<String, Void, String>() {

        override fun doInBackground(vararg params: String): String? {
            try {
                val url = URL(params[0])
                //val str_i = params[1]
                //val str_j = params[2]
                val con = url.openConnection() as HttpURLConnection
                con.requestMethod = "GET"
                con.connect()
                val statuscode = con.responseCode
                if (statuscode == HttpURLConnection.HTTP_OK) {
                    val br = BufferedReader(InputStreamReader(con.inputStream))
                    val sb = StringBuilder()
                    var line: String? = br.readLine()
                    while (line != null) {
                        sb.append(line)
                        line = br.readLine()
                    }
                    val json = sb.toString()
                    Log.d("JSON", json)
                    val root = JSONObject(json)
                    val array_rows = root.getJSONArray("rows")
                    Log.d("JSON", "array_rows:$array_rows")
                    val object_rows = array_rows.getJSONObject(0)
                    Log.d("JSON", "object_rows:$object_rows")
                    val array_elements = object_rows.getJSONArray("elements")
                    Log.d("JSON", "array_elements:$array_elements")
                    val object_elements = array_elements.getJSONObject(0)
                    Log.d("JSON", "object_elements:$object_elements")
                    val object_duration = object_elements.getJSONObject("duration")
                    val object_distance = object_elements.getJSONObject("distance")

                    Log.d("JSON", "object_duration:$object_duration")
                    return object_duration.getString("value") + "," + object_distance.getString("value")
                }
            } catch (e: MalformedURLException) {
                Log.d("error", "error1")
            } catch (e: IOException) {
                Log.d("error", "error2")
            } catch (e: JSONException) {
                Log.d("error", "error3")
            }


            return ""
        }

    }

//**********************************************************
    /*override fun setDouble(result: String) {
        val res = result.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val min = Integer.parseInt(res[0]) / 60
        val dist = Integer.parseInt(res[1]) / 1000
        val i = Integer.parseInt(res[2])
        val j = Integer.parseInt(res[3])
        //Log.d("Distance", "setDouble: "+md[i][j]);
        //System.out.println(min);
        //tv_result1.setText("Duration= " + (int) (min / 60) + " hr " + (int) (min % 60) + " mins");
        //tv_result2.setText("Distance= " + dist + " kilometers")

        md[i][j] = dist
        mt[i][j] = min

        Log.d("md[i][j]", "distancef: " + md[i][j] + "-i=" + i + "-j=" + j)
        Log.d("mt[i][j]", "Durationf: " + mt[i][j] + "-i=" + i + "-j=" + j)
    }*/
    private fun places(places:List<place>)
    {
        recyclerViewPlace.layoutManager= LinearLayoutManager(context)
        recyclerViewPlace.adapter=listAdapter(places)
    }
}
