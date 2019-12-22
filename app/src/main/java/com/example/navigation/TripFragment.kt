package com.example.navigation


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_trip.*
import kotlinx.android.synthetic.main.fragment_trip.view.*
import java.text.DecimalFormat
import java.util.*







/**
 * A simple [Fragment] subclass.
 */
class TripFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v=inflater.inflate(R.layout.fragment_trip, container, false)
        val button=v.button
        val button2=v.buttontime
        var list= arrayListOf<test>()
        val dft = DecimalFormat("#.##")
        val dfd = DecimalFormat("#,###.##")
            button.setOnClickListener {
                if(ListFragment.idlist.size>0) {

                    var l = 0
                    while (l < ListFragment.mt.size - 1) {
                        var m = 0
                        while (m < ListFragment.mt.size - 1) {
                            if (l == m) {
                                m++
                                continue
                            } else {

                                var lat1 = 0.0
                                if (ListFragment.mt[l][m] > 720) {
                                    var d = 0.0
                                    var t = 0.0

                                    var long1 = 0.0
                                    var lat2 = 0.0
                                    var long2 = 0.0
                                    var r = 6373
                                    lat1 =
                                        AccountFragment.latitude[ListFragment.idlist[l]].toDouble()
                                    Log.d("latitude1", "Latitude1=" + lat1)

                                    lat2 =
                                        AccountFragment.latitude[ListFragment.idlist[m]].toDouble()
                                    Log.d("latitude2", "Latitude2=" + lat2)
                                    long1 =
                                        AccountFragment.longitude[ListFragment.idlist[l]].toDouble()
                                    Log.d("longitude1", "Longitude1=" + long1)
                                    long2 =
                                        AccountFragment.longitude[ListFragment.idlist[m]].toDouble()
                                    Log.d("longitude2", "Longitude2=" + long2)
                                    val dLat = Math.toRadians(lat2 - lat1)
                                    val dLon = Math.toRadians(long2 - long1)

                                    // convert to radians
                                    lat1 = Math.toRadians(lat1)
                                    lat2 = Math.toRadians(lat2)

                                    // apply formulae
                                    var a = Math.pow(Math.sin(dLat / 2), 2.0) + Math.pow(
                                        Math.sin(dLon / 2),
                                        2.0
                                    ) * Math.cos(lat1) * Math.cos(lat2)
                                    var rad = 6371.0
                                    var c = 2 * Math.asin(Math.sqrt(a))
                                    d = c * rad
                                    Log.d(
                                        "PREVIOUS DISTANCE",
                                        "PREVIOUS DISTANCE=" + ListFragment.md[l][m]
                                    )
                                    Log.d("UPDATED DISTANCE", "UPDATED DISTANCE=" + d)
                                    ListFragment.md[l][m] = d.toInt()
                                    t = (d / 804.0) * 60
                                    Log.d("PREVIOUS TIME", "PREVIOUS TIME=" + ListFragment.mt[l][m])
                                    ListFragment.mt[l][m] = t.toInt()

                                    Log.d("UPDATED TIME", "UPDATED TIME=" + t)
                                }
                                m++


                            }
                        }
                        l++
                    }

                    /*var f=0
                while(f<ListFragment.md.size-1)
                {
                    var g=0
                    while(g<ListFragment.md.size-1)
                    {
                        Log.d("UPDATED ARRAY","NEW"+ListFragment.md[f][g])
                        g++
                    }
                    f++
                }*/

                    val numberOfNodes = ListFragment.idlist.size
                    val seq = IntArray(ListFragment.idlist.size)
                    var index_seq = 0
                    val stack: Stack<Int>
                    stack = Stack<Int>()
                    /*int[] tsp=new int[3];
                                int[][] adjacencyMatrix=new int[3][3];
                                adjacencyMatrix[0][0]=0;
                                adjacencyMatrix[0][1]=464;
                                adjacencyMatrix[0][2]=100;
                                adjacencyMatrix[1][0]=492;
                                adjacencyMatrix[1][1]=0;
                                adjacencyMatrix[1][2]=120;
                                adjacencyMatrix[2][0]=100;
                                adjacencyMatrix[2][1]=120;
                                adjacencyMatrix[2][2]=0;
                                numberOfNodes = 3;*/
                    val visited = IntArray(numberOfNodes)
                    visited[0] = 1
                    stack.push(0)
                    var element: Int
                    var dst = 0
                    var i: Int
                    var min = Integer.MAX_VALUE
                    var minFlag = false
                    //System.out.print(0 + "\t");
                    seq[index_seq++] = 0

                    while (!stack.isEmpty()) {
                        element = stack.peek()
                        i = 0
                        min = Integer.MAX_VALUE
                        while (i < numberOfNodes) {
                            if (ListFragment.md[element][i] > 1 && visited[i] == 0) {
                                if (min > ListFragment.md[element][i]) {
                                    min = ListFragment.md[element][i]
                                    dst = i
                                    minFlag = true
                                }
                            }
                            i++
                        }
                        if (minFlag) {
                            visited[dst] = 1
                            stack.push(dst)
                            //System.out.print(dst + "\t");
                            seq[index_seq] = dst
                            index_seq++

                            minFlag = false
                            continue
                        }
                        stack.pop()
                    }
                    var TspSeq = ""
                    var a = 0
                    while (a < ListFragment.idlist.size) {
                        Log.d("Sequence_of_ID", "ID: " + ListFragment.idlist[seq[a]])
                        Log.d("Sequence_of_seqArray", "Seq_Array: " + seq[a])
                        TspSeq = TspSeq + (a + 1) + " " + ListFragment.namelist[seq[a]] + "\n"
                        a++
                    }
                    val day = IntArray(ListFragment.idlist.size)
                    val tt = IntArray(ListFragment.idlist.size)
                    val td = IntArray(ListFragment.idlist.size)
                    var d = 1;

                    var s = AccountFragment.begintime;
                    var x = 0
                    while (x < ListFragment.idlist.size) {
                        if (x < ListFragment.idlist.size - 1) {
                            var j = x + 1;
                            var a = 0;
                            var b = 0;
                            var k = 0
                            while (k < ListFragment.idlist.size) {
                                if (ListFragment.idlist[seq[x]] == ListFragment.idlist[k]) {
                                    a = k;
                                    System.out.println("a=" + a);
                                }
                                if (ListFragment.idlist[seq[j]] == ListFragment.idlist[k]) {
                                    b = k;
                                    System.out.println("b=" + b);
                                }
                                k++
                            }
                            tt[x] = ListFragment.mt[a][b];
                            td[x] = ListFragment.md[a][b];
                            System.out.println("----i---- = " + x);
                            if ((s + ListFragment.durationlist[x]) > AccountFragment.end) {
                                d++;
                                s = AccountFragment.begintime;
                                s = s + ListFragment.durationlist[x];
                                day[x] = d;
                                System.out.println("s = " + s);
                                System.out.println("d = " + d);
                            } else {
                                s = s + ListFragment.durationlist[x];
                                day[x] = d;
                                System.out.println("s = " + s);
                            }

                            d = d + ((s + (tt[x] / 60)) / 24).toInt();
                            System.out.println("d = " + d);
                            s = (s + (tt[x] / 60.0)) % 24.0;
                            System.out.println("s_travel = " + s);
                            if (s < AccountFragment.begintime) {
                                s = AccountFragment.begintime;

                            }
                            if (s >= AccountFragment.end) {
                                d++;
                                s = AccountFragment.begintime;
                            }

                        }

                        //last index
                        if (x == (ListFragment.idlist.size - 1)) {
                            if ((s + ListFragment.durationlist[x]) > AccountFragment.end) {
                                d++;
                                s = AccountFragment.begintime;
                                s = s + ListFragment.durationlist[x];
                                day[x] = d;
                                System.out.println("s = " + s);
                            } else {
                                s = s + ListFragment.durationlist[x];
                                day[x] = d;
                                System.out.println("s = " + s);
                            }
                        }
                        x++
                    }
                    for (i in 0 until ListFragment.idlist.size) {
                        if (i < ListFragment.idlist.size - 1) {
                            println("td" + i + "=" + td[i])
                            println("tt" + i + "=" + tt[i])
                        }
                        println("Day of " + i + " index = " + day[i])
                    }
                    System.out.println(day[ListFragment.idlist.size - 1])
                    println(2 < 2)
                    var str = ""
                    var dnew = 1
                    while (dnew <= day[ListFragment.idlist.size - 1]) {
                        str = "$str\n --DAY $dnew"
                        for (i in 0 until ListFragment.idlist.size) {
                            if (dnew == day[i]) {
                                if (i < ListFragment.idlist.size - 1) {
                                    str = str + "\n --Place = " + ListFragment.namelist[seq[i]]
                                    str =
                                        str + " --Suggested Time = " + ListFragment.durationlist[seq[i]] + "Hrs"
                                    str = str + " --Distance = " + dfd.format(td[i]) + "KM"
                                    str =
                                        str + " --Travel Time = " + dft.format(tt[i] / 60.0) + "Hrs"
                                    list.add(
                                        test(
                                            "" + ListFragment.namelist[seq[i]],
                                            "Duration=" + ListFragment.durationlist[seq[i]].toString() + " Hours",
                                            dfd.format(td[i]).toString() + " Km",
                                            dft.format(tt[i] / 60.0).toString() + " Hours",
                                            "Day " + dnew.toString()
                                        )
                                    )
                                } else {
                                    str = str + "\n --Place = " + ListFragment.namelist[seq[i]]
                                    str =
                                        str + " --Suggested Time = " + ListFragment.durationlist[seq[i]] + "Hrs"
                                    list.add(
                                        test(
                                            ListFragment.namelist[seq[i]],
                                            "Duration=" + ListFragment.durationlist[seq[i]].toString() + " Hours",
                                            "",
                                            "",
                                            "Day " + dnew.toString()
                                        )
                                    )
                                }
                            }
                        }
                        dnew++
                    }
                    println(str)
                    recyclerViewPlaces.layoutManager= LinearLayoutManager(context)
                    recyclerViewPlaces.adapter=tripAdapter(list)
                }
                else{
                    Toast.makeText(context,"Please add a place in the list",Toast.LENGTH_LONG).show()
                }





            }
        button2.setOnClickListener {
            if(ListFragment.idlist.size>0) {

                var l = 0
                while (l < ListFragment.mt.size - 1) {
                    var m = 0
                    while (m < ListFragment.mt.size - 1) {
                        if (l == m) {
                            m++
                            continue
                        } else {

                            var lat1 = 0.0
                            if (ListFragment.mt[l][m] > 720) {
                                var d = 0.0
                                var t = 0.0

                                var long1 = 0.0
                                var lat2 = 0.0
                                var long2 = 0.0
                                var r = 6373
                                lat1 =
                                    AccountFragment.latitude[ListFragment.idlist[l]].toDouble()
                                Log.d("latitude1", "Latitude1=" + lat1)

                                lat2 =
                                    AccountFragment.latitude[ListFragment.idlist[m]].toDouble()
                                Log.d("latitude2", "Latitude2=" + lat2)
                                long1 =
                                    AccountFragment.longitude[ListFragment.idlist[l]].toDouble()
                                Log.d("longitude1", "Longitude1=" + long1)
                                long2 =
                                    AccountFragment.longitude[ListFragment.idlist[m]].toDouble()
                                Log.d("longitude2", "Longitude2=" + long2)
                                val dLat = Math.toRadians(lat2 - lat1)
                                val dLon = Math.toRadians(long2 - long1)

                                // convert to radians
                                lat1 = Math.toRadians(lat1)
                                lat2 = Math.toRadians(lat2)

                                // apply formulae
                                var a = Math.pow(Math.sin(dLat / 2), 2.0) + Math.pow(
                                    Math.sin(dLon / 2),
                                    2.0
                                ) * Math.cos(lat1) * Math.cos(lat2)
                                var rad = 6371.0
                                var c = 2 * Math.asin(Math.sqrt(a))
                                d = c * rad
                                Log.d(
                                    "PREVIOUS DISTANCE",
                                    "PREVIOUS DISTANCE=" + ListFragment.md[l][m]
                                )
                                Log.d("UPDATED DISTANCE", "UPDATED DISTANCE=" + d)
                                ListFragment.md[l][m] = d.toInt()
                                t = (d / 804.0) * 60
                                Log.d("PREVIOUS TIME", "PREVIOUS TIME=" + ListFragment.mt[l][m])
                                ListFragment.mt[l][m] = t.toInt()

                                Log.d("UPDATED TIME", "UPDATED TIME=" + t)
                            }
                            m++


                        }
                    }
                    l++
                }

                /*var f=0
            while(f<ListFragment.md.size-1)
            {
                var g=0
                while(g<ListFragment.md.size-1)
                {
                    Log.d("UPDATED ARRAY","NEW"+ListFragment.md[f][g])
                    g++
                }
                f++
            }*/

                val numberOfNodes = ListFragment.idlist.size
                val seq = IntArray(ListFragment.idlist.size)
                var index_seq = 0
                val stack: Stack<Int>
                stack = Stack<Int>()
                /*int[] tsp=new int[3];
                            int[][] adjacencyMatrix=new int[3][3];
                            adjacencyMatrix[0][0]=0;
                            adjacencyMatrix[0][1]=464;
                            adjacencyMatrix[0][2]=100;
                            adjacencyMatrix[1][0]=492;
                            adjacencyMatrix[1][1]=0;
                            adjacencyMatrix[1][2]=120;
                            adjacencyMatrix[2][0]=100;
                            adjacencyMatrix[2][1]=120;
                            adjacencyMatrix[2][2]=0;
                            numberOfNodes = 3;*/
                val visited = IntArray(numberOfNodes)
                visited[0] = 1
                stack.push(0)
                var element: Int
                var dst = 0
                var i: Int
                var min = Integer.MAX_VALUE
                var minFlag = false
                //System.out.print(0 + "\t");
                seq[index_seq++] = 0

                while (!stack.isEmpty()) {
                    element = stack.peek()
                    i = 0
                    min = Integer.MAX_VALUE
                    while (i < numberOfNodes) {
                        if (ListFragment.mt[element][i] > 1 && visited[i] == 0) {
                            if (min > ListFragment.mt[element][i]) {
                                min = ListFragment.mt[element][i]
                                dst = i
                                minFlag = true
                            }
                        }
                        i++
                    }
                    if (minFlag) {
                        visited[dst] = 1
                        stack.push(dst)
                        //System.out.print(dst + "\t");
                        seq[index_seq] = dst
                        index_seq++

                        minFlag = false
                        continue
                    }
                    stack.pop()
                }
                var TspSeq = ""
                var a = 0
                while (a < ListFragment.idlist.size) {
                    Log.d("Sequence_of_ID", "ID: " + ListFragment.idlist[seq[a]])
                    Log.d("Sequence_of_seqArray", "Seq_Array: " + seq[a])
                    TspSeq = TspSeq + (a + 1) + " " + ListFragment.namelist[seq[a]] + "\n"
                    a++
                }
                val day = IntArray(ListFragment.idlist.size)
                val tt = IntArray(ListFragment.idlist.size)
                val td = IntArray(ListFragment.idlist.size)
                var d = 1;

                var s = AccountFragment.begintime;
                var x = 0
                while (x < ListFragment.idlist.size) {
                    if (x < ListFragment.idlist.size - 1) {
                        var j = x + 1;
                        var a = 0;
                        var b = 0;
                        var k = 0
                        while (k < ListFragment.idlist.size) {
                            if (ListFragment.idlist[seq[x]] == ListFragment.idlist[k]) {
                                a = k;
                                System.out.println("a=" + a);
                            }
                            if (ListFragment.idlist[seq[j]] == ListFragment.idlist[k]) {
                                b = k;
                                System.out.println("b=" + b);
                            }
                            k++
                        }
                        tt[x] = ListFragment.mt[a][b];
                        td[x] = ListFragment.md[a][b];
                        System.out.println("----i---- = " + x);
                        if ((s + ListFragment.durationlist[x]) > AccountFragment.end) {
                            d++;
                            s = AccountFragment.begintime;
                            s = s + ListFragment.durationlist[x];
                            day[x] = d;
                            System.out.println("s = " + s);
                            System.out.println("d = " + d);
                        } else {
                            s = s + ListFragment.durationlist[x];
                            day[x] = d;
                            System.out.println("s = " + s);
                        }

                        d = d + ((s + (tt[x] / 60)) / 24).toInt();
                        System.out.println("d = " + d);
                        s = (s + (tt[x] / 60.0)) % 24.0;
                        System.out.println("s_travel = " + s);
                        if (s < AccountFragment.begintime) {
                            s = AccountFragment.begintime;

                        }
                        if (s >= AccountFragment.end) {
                            d++;
                            s = AccountFragment.begintime;
                        }

                    }

                    //last index
                    if (x == (ListFragment.idlist.size - 1)) {
                        if ((s + ListFragment.durationlist[x]) > AccountFragment.end) {
                            d++;
                            s = AccountFragment.begintime;
                            s = s + ListFragment.durationlist[x];
                            day[x] = d;
                            System.out.println("s = " + s);
                        } else {
                            s = s + ListFragment.durationlist[x];
                            day[x] = d;
                            System.out.println("s = " + s);
                        }
                    }
                    x++
                }
                for (i in 0 until ListFragment.idlist.size) {
                    if (i < ListFragment.idlist.size - 1) {
                        println("td" + i + "=" + td[i])
                        println("tt" + i + "=" + tt[i])
                    }
                    println("Day of " + i + " index = " + day[i])
                }
                System.out.println(day[ListFragment.idlist.size - 1])
                println(2 < 2)
                var str = ""
                var dnew = 1
                while (dnew <= day[ListFragment.idlist.size - 1]) {
                    str = "$str\n --DAY $dnew"
                    for (i in 0 until ListFragment.idlist.size) {
                        if (dnew == day[i]) {
                            if (i < ListFragment.idlist.size - 1) {
                                str = str + "\n --Place = " + ListFragment.namelist[seq[i]]
                                str =
                                    str + " --Suggested Time = " + ListFragment.durationlist[seq[i]] + "Hrs"
                                str = str + " --Distance = " + dfd.format(td[i]) + "KM"
                                str =
                                    str + " --Travel Time = " + dft.format(tt[i] / 60.0) + "Hrs"
                                list.add(
                                    test(
                                        "" + ListFragment.namelist[seq[i]],
                                        "Duration=" + ListFragment.durationlist[seq[i]].toString() + " Hours",
                                        dfd.format(td[i]).toString() + " Km",
                                        dft.format(tt[i] / 60.0).toString() + " Hours",
                                        "Day " + dnew.toString()
                                    )
                                )
                            } else {
                                str = str + "\n --Place = " + ListFragment.namelist[seq[i]]
                                str =
                                    str + " --Suggested Time = " + ListFragment.durationlist[seq[i]] + "Hrs"
                                list.add(
                                    test(
                                        ListFragment.namelist[seq[i]],
                                        "Duration=" + ListFragment.durationlist[seq[i]].toString() + " Hours",
                                        "",
                                        "",
                                        "Day " + dnew.toString()
                                    )
                                )
                            }
                        }
                    }
                    dnew++
                }
                println(str)
                recyclerViewPlaces.layoutManager= LinearLayoutManager(context)
                recyclerViewPlaces.adapter=tripAdapter(list)
            }
            else{
                Toast.makeText(context,"Please add a place in the list",Toast.LENGTH_LONG).show()
            }





        }
        return v

        }
    }

