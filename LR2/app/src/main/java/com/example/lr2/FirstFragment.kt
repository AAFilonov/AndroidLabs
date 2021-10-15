package com.example.lr2

import android.content.res.Resources
import android.os.Bundle
import android.provider.Settings.Global.getString
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.lr2.databinding.FragmentFirstBinding
import java.io.Console
import java.util.function.BiFunction

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
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

        binding.buttonOk.setOnClickListener {

            var value = binding.editTextNumber.text.toString();
            var map: HashMap<Char, Int> = HashMap()
            for (letter in value) {
                map.merge(letter, 1, { prev: Int, new: Int -> prev + new })
            }
            val maxValue = map.values.maxOrNull()
            val maxCommonChar = map.keys.filter { k -> map[k] == maxValue }.get(0)

            binding.textViewOutput.text =
                String.format(resources.getString(R.string.output_number), maxCommonChar)
            //"Чаще всего в вашем числе  встречается цифра ${maxCommonChar}"*/

        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}