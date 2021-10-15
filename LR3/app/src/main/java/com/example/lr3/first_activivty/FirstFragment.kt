package com.example.lr3.first_activivty

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.lr3.Order
import com.example.lr3.R
import com.example.lr3.databinding.FragmentFirstBinding
import com.example.lr3.second_activivty.SecondActivity
import java.lang.Exception

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null


    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button.setOnClickListener {
            val order = Order("none", "none", 0, "", "")

            try {
                order.hall = binding.spinnerHall.selectedItem.toString()
                order.type = binding.spinnerType.selectedItem.toString()
                order.guestCount = binding.pointsGsInput.text.toString().toInt()
                order.firstName = binding.textViewFirstName.text.toString()
                order.SecondName = binding.textViewSecondName.text.toString()
                val intent: Intent = Intent(SecondActivity)
                startActivity(intent)

            } catch (e: Exception) {
                //do nothing
            }

        }
    }

    fun doReady(view: View) {

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}