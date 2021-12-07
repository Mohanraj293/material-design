package com.example.materialdesign

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class TextFields : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_text_fields, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val context = context as MainActivity
        val date = context.findViewById<Button>(R.id.pick_date_button)
        val time = context.findViewById<Button>(R.id.pick_time_button)
        val tab = context.findViewById<Button>(R.id.tabBtn)
        val dbLayout = context.findViewById<Button>(R.id.dbBtn)

       tab.setOnClickListener {
           val intent = Intent(view.context, TabLayout::class.java)
           startActivity(intent)
       }
        dbLayout.setOnClickListener {
            val intent = Intent(view.context, DbActivity::class.java)
            startActivity(intent)
        }

        date.setOnClickListener{
            val builder =     MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select date")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build()
            builder.show(parentFragmentManager,"")
        }
        time.setOnClickListener{
            val picker =
                MaterialTimePicker.Builder()
                    .setTimeFormat(TimeFormat.CLOCK_12H)
                    .setHour(12)
                    .setMinute(10)
                    .setTitleText("Select time")
                    .build()
            picker.show(parentFragmentManager,"")
        }

    }
}